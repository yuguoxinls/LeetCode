package LeetCodeByClass.DataStructure.Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
    /**
     * 递归
     * 1. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 2. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     * 示例 2：
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     * 示例 3：
     * 输入：root = []
     * 输出：true
     */
    public boolean isBalanced(TreeNode root) { // TODO: 2022/12/9
        return height(root) >= 0;
    }
    private int height(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 3. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
     * 这条路径可能穿过也可能不穿过根结点。
     * 示例 :
     * 给定二叉树
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     */
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {// TODO: 2022/12/9
        depth(root);
        return max;
    }
    private int depth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        max = Math.max(max, leftDepth + rightDepth); // 会遍历到每个节点，统计每个节点左右深度之和的最大值
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 4. 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * 示例 2：
     * 输入：root = [2,1,3]
     * 输出：[2,3,1]
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode right = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(right);
        return root;
    }

    /**
     * 5. 合并二叉树
     * 给你两棵二叉树： root1 和 root2 。
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
     * 你需要将这两棵树合并成一棵新二叉树。
     * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
     * 返回合并后的二叉树。
     * 注意: 合并过程必须从两个树的根节点开始。
     * 示例 1：
     * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
     * 输出：[3,4,5,5,4,null,7]
     * 示例 2：
     * 输入：root1 = [1], root2 = [1,2]
     * 输出：[2,2]
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;
    }

    /**
     * 6. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     * 解释：等于目标和的根节点到叶节点路径如上图所示。
     * 示例 2：
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：false
     * 解释：树中存在两条根节点到叶子节点的路径：
     * (1 --> 2): 和为 3
     * (1 --> 3): 和为 4
     * 不存在 sum = 5 的根节点到叶子节点的路径。
     * 示例 3：
     * 输入：root = [], targetSum = 0
     * 输出：false
     * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {  // TODO: 2022/12/13
        /**
         * hasPathSum会根据判断当前节点到其叶子节点的值 val 是否等于 targetSum
         * 问题规模可以缩小为：从当前节点的子节点到其叶子节点的值 是否等于 targetSum - val
         */
        // 方法一：递归
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        // 方法二：BFS
        /*if (root == null) return false;
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()){
            TreeNode tmpNode = queNode.poll();
            Integer tmpVal = queVal.poll();
            if (tmpNode.left == null && tmpNode.right == null){ // 说明是叶子节点
                if (tmpVal == targetSum) return true;
                continue;
            }
            if (tmpNode.left != null){
                queNode.offer(tmpNode.left);
                queVal.offer(tmpVal + tmpNode.left.val);
            }
            if (tmpNode.right != null){
                queNode.offer(tmpNode.right);
                queVal.offer(tmpVal + tmpNode.right.val);
            }
        }
        return false;*/
    }

    /**
     * 7. 路径总和 III
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 示例 1：
     * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
     * 输出：3
     * 解释：和等于 8 的路径有 3 条，如图所示。
     * 示例 2：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：3
     */
    // TODO: 2022/12/13 方法一：dfs 递归遍历以每一个节点为起始节点的所有可能路径，加起来
    /*public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = rootSum(root, (long)targetSum); // 以根节点开始的所有满足条件的路径数，注意为了防止减法溢出问题，此处转换成long类型
        ret += pathSum(root.left, targetSum); // 以根节点的左节点开始的所有满足条件的路径数
        ret += pathSum(root.right, targetSum); // 以根节点的右节点开始的所有满足条件的路径数
        return ret;
    }
    private int rootSum(TreeNode root, Long targetSum) {
        int ret = 0;
        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }
        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }*/
    // TODO: 2022/12/13 方法二：前缀和 https://leetcode.cn/problems/path-sum-iii/solutions/1021296/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

    /**
     * 8. 另一棵树的子树
     * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
     * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) { // TODO: 2022/12/15
        if (root == null) return false;
        return isSubtreeWithRoot(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSubtreeWithRoot(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;
        return isSubtreeWithRoot(root.left, subRoot.left) && isSubtreeWithRoot(root.right, subRoot.right);
    }

    /**
     * 9. 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    public boolean isSymmetric(TreeNode root) { // TODO: 2022/12/15 转换成判断两棵树是否对称
        if (root == null) return true;
        return check(root.left, root.right);
    }
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return check(left.left, right.right) && check(left.right, right.left);
    }

    /**
     * 10. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     */
    public int minDepth(TreeNode root) { // TODO: 2022/12/15 注意排除树退化成单链表的情况
        if (root == null) return 0;
        /*int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) return left + right + 1;
        return Math.min(left, right) + 1;*/
        // TODO: 2022/12/15 自己写的广度优先遍历居然更快
        int minDep = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) queue.offer(treeNode.left);
                if (treeNode.right != null) queue.offer(treeNode.right);
                if (treeNode.left == null && treeNode.right == null) return minDep;
            }
            minDep++;
        }
        return minDep;
    }

    /**
     * 11. 左叶子之和
     * 给定二叉树的根节点 root ，返回所有左叶子之和。
     */
    public int sumOfLeftLeaves(TreeNode root) {
        /*if (root == null || (root.left == null && root.right == null)) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                    if (treeNode.left.left == null && treeNode.left.right == null) sum += treeNode.left.val; // 注意这里只计算左叶子节点，不是所有的左节点都算在内
                }
                if (treeNode.right != null) queue.offer(treeNode.right);
            }
        }
        return sum;*/
        // TODO: 2022/12/15 递归的思路更直观
        if (root == null) return 0;
        if (isLeaf(root.left)) { // 如果根节点的左子树就是左叶子节点，直接返回它的值+右子树中的左叶子节点
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        // 如果左子树不是左叶子节点
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode node) {
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

}
