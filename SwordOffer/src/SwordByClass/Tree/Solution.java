package SwordByClass.Tree;

import java.util.HashMap;

public class Solution {
    /**
     * 7. 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 示例 1:
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     * 示例 2:
     * Input: preorder = [-1], inorder = [-1]
     * Output: [-1]
     */
    // TODO: 2023/1/6 仔细看一下
    HashMap<Integer, Integer> map = new HashMap<>();//标记中序遍历
    int[] preorder;//保留的先序遍历，方便递归时依据索引查看先序遍历的值
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        //将中序遍历的值及索引放在map中，方便递归时获取左子树与右子树的数量及其根的索引
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        //三个索引分别为
        //当前根的的索引
        //递归树的左边界，即数组左边界
        //递归树的右边界，即数组右边界
        return recur(0,0,inorder.length-1);
    }
    TreeNode recur(int preRoot, int inLeft, int inRight){
        if(inLeft > inRight) return null;// 相等的话就是自己
        TreeNode root = new TreeNode(preorder[preRoot]);//获取root节点
        int idx = map.get(preorder[preRoot]);//获取在中序遍历中根节点所在索引，以方便获取左子树的数量
        //左子树的根的索引为先序中的根节点+1
        //递归左子树的左边界为原来的中序in_left
        //递归左子树的右边界为中序中的根节点索引-1
        root.left = recur(preRoot+1, inLeft, idx-1);
        //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
        //递归右子树的左边界为中序中当前根节点+1
        //递归右子树的右边界为中序中原来右子树的边界
        root.right = recur(preRoot + (idx - inLeft) + 1, idx+1, inRight);
        return root;
    }

    /**
     * 26. 树的子结构
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * 例如:
     * 给定的树 A:
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 B：
     *    4
     *   /
     *  1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     * 示例 1：
     * 输入：A = [1,2,3], B = [3,1]
     * 输出：false
     * 示例 2：
     * 输入：A = [3,4,5,1,2], B = [4,1]
     * 输出：true
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // TODO: 2023/1/6
        // B是A的子结构，那么A的任意一个节点都有可能是B的根节点，因此进行下面2个步骤：
        // 1. 遍历A的每一个节点 -> isSubStructure()
        // 2. 针对遍历到的每个节点，判断以该节点为根节点的子树是否包含树B -> isContain()

        // 2个树都不能是null，并且若B是A的子结构，必然满足以下3个条件之一：
        // 1. 以 节点 A 为根节点的子树 包含树 B
        // 2. 树B是树A左子树的子结构
        // 3. 。。。。右子树的子结构    2，3两步相当于在对A做先序遍历
        return (A != null && B != null) && (isContain(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
    private boolean isContain(TreeNode A, TreeNode B) { // 判断以A为根节点的树是否包含树B
        if (B == null) return true; // B为空，说明树 B 已匹配完成（越过叶子节点），因此返回true
        if (A == null || A.val != B.val) return false; // 值不相等，肯定返回false
        return isContain(A.left, B.left) && isContain(A.right, B.right); // 要包含，左右子树也都要满足包含条件
    }

    /**
     * 27. 二叉树的镜像
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     * 例如输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 镜像输出：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode right = root.right;
        root.right = mirrorTree(root.left);
        root.left = mirrorTree(right);
        return root;
    }

    /**
     * 28. 对称的二叉树
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

}
