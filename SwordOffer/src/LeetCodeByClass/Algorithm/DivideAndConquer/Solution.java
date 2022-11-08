package LeetCodeByClass.Algorithm.DivideAndConquer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    /**
     * 分治：是一种思想，将规模较大的问题，拆分成规模较小但形式相同的子问题，子问题的解合并后是原问题的解
     */

    /**
     * 1. 为运算表达式设计优先级
     * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
     * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
     * 示例 1：
     * 输入：expression = "2-1-1"
     * 输出：[0,2]
     * 解释：
     * ((2-1)-1) = 0
     * (2-(1-1)) = 2
     * 示例 2：
     * 输入：expression = "2*3-4*5"
     * 输出：[-34,-14,-10,-10,10]
     * 解释：
     * (2*(3-(4*5))) = -34
     * ((2*3)-(4*5)) = -14
     * ((2*(3-4))*5) = -10
     * (2*((3-4)*5)) = -10
     * (((2*3)-4)*5) = 10
     */
    public List<Integer> diffWaysToCompute(String expression) { // TODO: 2022/11/8 这个递归真看不懂
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i); // 遍历表达式中的每一个字符
            if (c == '+' || c == '-' || c == '*') { // 如果当前字符是运算符
                List<Integer> left = diffWaysToCompute(expression.substring(0, i)); // 递归计算运算符左右两侧
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1)); // left和right中存的是数字结果集
                for (int l : left) {
                    for (int r : right) { // 双重for循环，组合遍历left和right中的元素
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (ways.size() == 0) { // 说明表达式中没有运算符
            ways.add(Integer.valueOf(expression));
        }
        return ways;
    }

    /**
     * 2. 不同的二叉搜索树 II https://leetcode.cn/problems/unique-binary-search-trees-ii/description/
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     */
    public List<TreeNode> generateTrees(int n) { // TODO: 2022/11/8 分治体现在哪了呢，递归吗
        if (n < 1) {
            return new LinkedList<>();
        }
        return generateSubtrees(1, n);
    }
    private List<TreeNode> generateSubtrees(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; ++i) { // 遍历所有能当根节点的节点i
            List<TreeNode> leftSubtrees = generateSubtrees(start, i - 1); // 因为是二叉搜索树，所以让比当前节点i小的节点生成节点i的左子树
            List<TreeNode> rightSubtrees = generateSubtrees(i + 1, end);  // 因为是二叉搜索树，所以让比当前节点i大的节点生成节点i的右子树
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) { // 双重for循环组合遍历
                    TreeNode root = new TreeNode(i); // 以当前节点i作为根节点
                    root.left = left; // 左子树中的节点作为根节点的左孩子
                    root.right = right; // 右子树中的节点作为根节点的右孩子
                    res.add(root); // 把这种可能添加到结果列表中
                }
            }
        }
        return res;
    }
}