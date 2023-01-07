package SwordByClass.Tree;

import java.util.*;

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

    /**
     * 32. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回：
     * [3,9,20,15,7]
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                ans.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    /**
     * 32.2 从上到下打印二叉树 II
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> subAns = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subAns.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ans.add(subAns);
        }
        return ans;
    }

    /**
     * 32.3 从上到下打印二叉树 III
     * 请实现一个函数按照之字形顺序打印二叉树，
     * 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int flag = 1;
        while (!queue.isEmpty()){
            /*int size = queue.size();
            List<Integer> subAns = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subAns.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (flag % 2 == 0){
                Deque<Integer> stack = new LinkedList<>();
                int len = subAns.size();
                for (int i = 0; i < len; i++) {
                    stack.push(subAns.remove(0));
                }
                while (!stack.isEmpty()){
                    subAns.add(stack.pop());
                }
            }
            ans.add(subAns);
            flag++;*/
            // TODO: 2023/1/7 采用双端队列改造，奇数层偶数层分别从队列两端添加，就不需要后边再用栈倒一下了
            // 相比于上边的方法，时间上没有提升，空间上有些提升
            int size = queue.size();
            LinkedList<Integer> subAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (ans.size() % 2 == 0) { // 说明前面已经遍历完偶数层了，当前在奇数层，顺序是正常的
                    subAns.addLast(node.val);
                }else {
                    subAns.addFirst(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ans.add(subAns);
        }
        return ans;
    }

    /**
     * 33. 二叉搜索树的后序遍历序列
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
     * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     * 参考以下这颗二叉搜索树：
     *      5
     *     / \
     *    2   6
     *   / \
     *  1   3
     * 示例 1：
     * 输入: [1,6,3,2,5]
     * 输出: false
     * 示例 2：
     * 输入: [1,3,2,6,5]
     * 输出: true
     */
    int end; // 倒序遍历的指针，用于指向根节点
    public boolean verifyPostorder(int[] postorder) {
        // TODO: 2023/1/7 可以按照给定的数组，按照后序遍历的方式构建一颗二叉搜索树，构建成功后，数组为空，说明给定数组是合法的
        // 而在实现的时候，不需要真正的构建一颗树，只需要判断是否符合BST结构，符合则移除列表的最后一个元素，不符合直接返回即可。
        // 这样实现的话，不符合规则提前返回，相当于剪枝了。
        // 此外，构造顺序是是[根->右->左]，这个点挺关键的，因为数组是后续遍历序列[左->右->根]，而我们是倒序遍历列表的。
        if (postorder == null || postorder.length == 1) return true;
        end = postorder.length - 1; // 初始化为整个树的根节点
        build(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return end < 0; // 如果构建成功的话，应该从后往前把整个数组扫了一遍，最后小于0
    }
    private void build(int[] postorder, int min, int max) {
        if (end < 0) return; // 小于0 说明已经扫描完毕数组了
        int root = postorder[end]; // 获取最后一个节点，当作根节点
        if (root >= max || root <= min) return; // 根节点的值应该在左右子树之间，因为是二叉搜索树
        end--; // 到此处说明 min < root < max，end向前移动一个
        // 前面说过，构造顺序是是[根->右->左]，因为数组是后续遍历序列[左->右->根]，而我们是倒序遍历列表的
        build(postorder, root, max); // 递归遍历右子树
        build(postorder, min, root); // 递归遍历左子树
    }


}
