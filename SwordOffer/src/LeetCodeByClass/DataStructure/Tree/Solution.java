package LeetCodeByClass.DataStructure.Tree;

import java.util.*;

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

    /**
     * 12. 最长同值路径
     * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
     * 两个节点之间的路径长度 由它们之间的边数表示。
     * 示例 1:
     * 输入：root = [5,4,5,1,1,5]
     * 输出：2
     * 示例 2:
     * 输入：root = [1,4,5,4,4,5]
     * 输出：2
     */
    // 最长同值长度，最后肯定是 '∧' 型长度 ， 用 max 记录
    int maxVal = 0;
    public int longestUnivaluePath(TreeNode root) { // TODO: 2022/12/19 看不懂哦
        dfs(root);
        return maxVal;
    }

    // 返回以当前 root节点 为根节点出发的 最长'/'型 或 '\' 同值长度
    public int dfs(TreeNode root){
        if(root == null)
            return 0;
        // 得到左子树的最长'/'型 或 '\' 同值长度
        int maxLeft = dfs(root.left);
        // 得到右子树的最长'/'型 或 '\' 同值长度
        int maxRight = dfs(root.right);

        // 现在是左子树有 一条 '/' 型路径，右子树有一条 '\' 型路径，我们要判断这个'/' + '\' 能不能连通当前根节点 形成最长 '∧' 型同值路径
        int curLeft = 0,curRight = 0;

        // 若根节点和左根值相同，那么 根节点 到 左根之间 形成通路，根节点的 左最大同值分支 '/' 可以加上根节点 形成 长度+1 的 '/'
        // 否则只是表示 从 当前根节点 的 左根节点出发 有路径，但是和当前根节点连接断开，故当前根节点的 左出发同值路径，'/' 为0
        if(root.left!=null && root.left.val==root.val)
            curLeft = maxLeft + 1;

        // 若根节点和右根值相同，那么 根节点 到 右根之间 形成通路，根节点的 右最大同值分支 '\' 可以加上根节点 形成 长度+1 的'\'
        // 否则只是表示 从 当前根节点 的 右根节点出发 有路径，但是和当前根节点连接断开，故当前根节点的 右出发同值路径，'\' 为0
        if(root.right!=null && root.right.val==root.val)
            curRight = maxRight + 1;

        // 连接根节点的新的 '/' 和'\' 再形成 新的大 '∧'，从而让 max 记录下了 整颗树中 任意节点能形成的 最大 '∧' 同值路径长度，也是题目要求的
        maxVal = Math.max(maxVal,curLeft+curRight);

        // 返回 左 '/'或 右'\'中分支的长度 最长的 一支长度(若根节点和左右子树都断开，那么显然返回的是 0 ，但递归时max已经记录下了最长'∧'的长度)
        return Math.max(curLeft, curRight);
    }

    /**
     * 13. 打家劫舍 III
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     * 示例 1:
     * 输入: root = [3,2,3,null,3,null,1]
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
     * 示例 2:
     * 输入: root = [3,4,5,1,3,null,1]
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
     */
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
    public int rob(TreeNode root) { // TODO: 2022/12/19 动态规划
        /**
         * 简化一下这个问题：一棵二叉树，树上的每个点都有对应的权值，每个点有两种状态（选中和不选中），问在不能同时选中有父子关系的点的情况下，能选中的点的最大权值和是多少。
         * 我们可以用 f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
         * g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；l 和 r 代表 o 的左右孩子。
         *  当 o 被选中时，o 的左右孩子都不能被选中，故 o 被选中情况下子树上被选中点的最大权值和为 l 和 r 不被选中的最大权值和相加，即 f(o)=g(l)+g(r)
         *  当 o 不被选中时，o 的左右孩子可以被选中，也可以不被选中。对于 o 的某个具体的孩子 x，它对 o 的贡献是 x 被选中和不被选中情况下权值和的较大值。故 g(o)=max{f(l),g(l)}+max{f(r),g(r)}
         * 至此，我们可以用哈希表来存 f 和 g 的函数值，用深度优先搜索的办法后序遍历这棵二叉树，我们就可以得到每一个节点的 f 和 g。根节点的 f 和 g 的最大值就是我们要找的答案。
         */
        dfsV2(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfsV2(TreeNode node) {
        if (node == null) {
            return;
        }
        dfsV2(node.left);
        dfsV2(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0)); // f(o)=g(l)+g(r)
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /**
     * 14. 二叉树中第二小的节点
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。
     * 给出这样的一个二叉树，你需要输出所有节点中的 第二小的值 。
     * 如果第二小的值不存在的话，输出 -1 。
     * 示例 1：
     * 输入：root = [2,2,5,null,null,5,7]
     * 输出：5
     * 解释：最小的值是 2 ，第二小的值是 5 。
     * 示例 2：
     * 输入：root = [2,2,2]
     * 输出：-1
     * 解释：最小的值是 2, 但是不存在第二小的值。
     */
    public int findSecondMinimumValue(TreeNode root) {
        /*if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int val = node.val;
                if (!list.contains(val)) list.add(val);
                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        list.sort((o1, o2) -> (o1-o2));
        if (list.size() == 1) return -1;
        return list.get(1);*/ // 自己写的BFS，太慢
        // TODO: 2022/12/19 利用改题目中树的特性
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1; // 返回 -1，说明没有最小值
        int leftVal = root.left.val;
        int rightVal = root.right.val;
        // 根节点是最小值，从等于根节点的某一个子树中找到最小值，就是第二小值
        if (leftVal == root.val) {
            leftVal = findSecondMinimumValue(root.left);
        }
        if (rightVal == root.val) {
            rightVal = findSecondMinimumValue(root.right);
        }
        // 如果是 -1，说明左右子树没有最小值，也就没有第二小值
        if (leftVal == -1) return rightVal;
        if (rightVal == -1) return leftVal;
        // 如果都不是 -1，返回左右子树最小值的最小值
        return Math.min(leftVal, rightVal);
    }

}
