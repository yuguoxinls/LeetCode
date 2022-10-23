package Hot100;

import java.util.*;

public class Solution {
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */
    public int[] twoSum(int[] nums, int target) {
        /*int[] res = new int[2];
        boolean flag = false;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i]+nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return res;*/ // 暴力枚举，太费时间
        // todo 采用hash
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int newTarget = target - nums[i];
            if (map.containsKey(newTarget)) return new int[] {map.get(newTarget), i};
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     * 示例 1：
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * 输入：s = "(]"
     * 输出：false
     */
    public boolean isValid(String s) { // TODO: 2022/10/19
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{','}');
        map.put('[',']');
        map.put('?','?');
        Stack<Character> stack = new Stack<>();
        stack.push('?');

        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false; //字符串的第一个字符不是左括号，直接false

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) stack.push(c); // 当字符是左括号时，入栈
            else if (map.get(stack.pop()) != c) return false; // 字符不是左括号，且不是和栈顶元素构成对应关系的右括号，直接false；
        }
        return stack.size() == 1; // 执行到这里，如果是满足条件的字符串，此时栈内只有‘？’
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) { // TODO: 2022/10/19  居然双100%了
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode head = new ListNode(), tmp = head;
        while (list1 != null && list2 != null){
            if (list1.val >= list2.val){
                tmp.next = list2;
                tmp = tmp.next;
                list2 = list2.next;
            } else {
                tmp.next = list1;
                tmp = tmp.next;
                list1 = list1.next;
            }
        }
        if (list1 == null) tmp.next = list2;
        else tmp.next = list1;
        return head.next;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     */
    public int climbStairs(int n) { // 就是斐波那契数列变体，包括青蛙跳台阶
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        int a = 1, b = 2, sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

    /**
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     */
    public List<Integer> inorderTraversal(TreeNode root) { // 比官方题解居然还快
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        List<Integer> lefts = inorderTraversal(root.left);
        res.addAll(lefts);
        res.add(root.val);
        List<Integer> rights = inorderTraversal(root.right);
        res.addAll(rights);
        return res;
    }

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */
    public boolean isSymmetric(TreeNode root) { // TODO: 2022/10/19 2种方式
        // 1. 队列
        /*if (root == null || (root.left == null && root.right == null)) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()){
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();

            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            queue.add(left.left);
            queue.add(right.right);

            queue.add(left.right);
            queue.add(right.left);
        }
        return true;*/
        // 2. 递归 递归反而效率更高
        if (root == null) return true;
        return check(root.left, root.right); // 判断根节点的左右子树是否对称
    }
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true; // 先判断当前两个子树的根节点是否满足对称条件
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;

        return check(left.left, right.right) && check(left.right, right.left); // 再递归判断左左和右右 左右和右左是否满足条件

    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
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
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 示例 1：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public int maxProfit(int[] prices) { // TODO: 2022/10/20 看就完了
        /*int max = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max((prices[j] - prices[i]), max);
            }
        }
        return Math.max(max, 0);*/ // 自己写的暴力求解时间复杂度太高
        /**
         * 肯定要在历史价格最低的时候购入，在未来价格最高的时候卖出，这样利润就最大了
         * 因此可以定义两个变量，minCost和maxProfit
         */
        int minCost = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minCost) minCost = price; // 这一天比之前的价格要低，我就这一天买入
            else maxProfit = Math.max(maxProfit, price - minCost); // 这一天的价格比成本高，比较这一天卖出和之前所记录的最大利润
        }
        return maxProfit;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    public int singleNumber(int[] nums) { // TODO: 2022/10/20 位运算 太骚了！
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                Integer value = map.get(num);
                map.put(num, ++value);
            }else {
                map.put(num, 1);
            }
        }
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            Integer value = map.get(key);
            if (value == 1) return key;
        }
        return -1;*/ // hash能做，但是时间复杂度不满足要求
        /**
         * 使用位运算。对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
         *  任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
         *  任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
         *  异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
         */
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     */
    public boolean hasCycle(ListNode head) { // TODO: 2022/10/21 快慢指针，又学到了，思想就是如果走的快的指针能和走得慢的指针相遇，说明链表中存在环，也就是慢的被套圈了
        if (head == null || head.next == null) return false;
        /*List<ListNode> list = new ArrayList<>(); // 也可以用set来判断当前节点是否被访问过
        ListNode tmp = head;
        while (tmp != null){
            if (list.contains(tmp.next)) return true;
            list.add(tmp);
            tmp = tmp.next;
        }
        return false;*/
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) { // 快的最后是null，走到了末尾，也就说明没有圈
                return false;
            }
            slow = slow.next; // 慢的走一步，快的走两步
            fast = fast.next.next;
        }
        return true; // 退出循环的时候。slow == fast，相遇了，说明有圈
    }

    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tmpA = headA, tmpB = headB;
        /*while (tmpA != tmpB){
            if (tmpA == null) tmpA = headB;
            if (tmpB == null) tmpB = headA;
            if (tmpA == tmpB) break;
            tmpA = tmpA.next;
            tmpB = tmpB.next;
        }*/
        // TODO: 2022/10/21 while中的代码可以优化
        while (tmpA != tmpB){
            tmpA = tmpA != null ? tmpA.next : headB;
            tmpB = tmpB != null ? tmpB.next : headA;
        }
        return tmpA;
    }

    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1：
     * 输入：nums = [3,2,3]
     * 输出：3
     * 示例 2：
     * 输入：nums = [2,2,1,1,1,2,2]
     * 输出：2
     */
    public int majorityElement(int[] nums) {
//        Arrays.sort(nums);
        /*for (int i = 0; i < nums.length-1; i++) {
            for (int j = 0; j < nums.length-i-1; j++) {
                if (nums[j] > nums[j+1]){
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }
        return nums[nums.length/2];*/ // 是解法的一种，优化方面就在对数组的排序上
        // TODO: 2022/10/21 投票法
        /**
         * “同归于尽消杀法” ：
         *    由于多数超过50%, 比如100个数，那么多数至少51个，剩下少数是49个。
         *    第一个到来的士兵，直接插上自己阵营的旗帜占领这块高地，此时领主 winner 就是这个阵营的人，现存兵力 count = 1。
         *    如果新来的士兵和前一个士兵是同一阵营，则集合起来占领高地，领主不变，winner 依然是当前这个士兵所属阵营，现存兵力 count++；
         *    如果新来到的士兵不是同一阵营，则前方阵营派一个士兵和它同归于尽。 此时前方阵营兵力count --。（即使双方都死光，这块高地的旗帜 winner 依然不变，因为已经没有活着的士兵可以去换上自己的新旗帜）
         *    当下一个士兵到来，发现前方阵营已经没有兵力，新士兵就成了领主，winner 变成这个士兵所属阵营的旗帜，现存兵力 count ++。
         *  就这样各路军阀一直以这种以一敌一同归于尽的方式厮杀下去，直到少数阵营都死光，那么最后剩下的几个必然属于多数阵营，winner 就是多数阵营。
         */
        int winner = nums[0], count = 1; // 默认地盘是数组中的第一个人的，初始兵力数为1
        for (int i = 1; i < nums.length; i++) {
            if (winner == nums[i]) count++; // 来的人和我是一伙的，兵力数+1
            else if (count == 0) { // 我方兵力已经没了，这个地盘现在谁来就归谁
                winner = nums[i];
                count++;
            }else count--; // 来的人和我不是一伙的，派一个人和他同归于尽，兵力数-1
        }
        return winner;
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     * 输入：head = []
     * 输出：[]
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode cur = head, pre = null;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }

    /**
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(ListNode head) { // TODO: 2022/10/23 官方题解 3 种方法，感觉都不是很优雅
        if (head == null || head.next == null) return true;
        Stack<Integer> stack = new Stack<>();
        int len = 0;
        ListNode tmp = head;
        while (tmp != null){
            len++;
            stack.push(tmp.val);
            tmp = tmp.next;
        }
        int step = len/2;
        tmp = head;
        while (step > 0){
            step--;
            if (stack.pop() != tmp.val) return false;
            tmp = tmp.next;
        }
        return true;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 示例 1:
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 示例 2:
     * 输入: nums = [0]
     * 输出: [0]
     */
    public void moveZeroes(int[] nums) {
        // TODO: 2022/10/23 2 种方法，1.是记录有多少个 0 ，不交换，直接在数组最后填上0的个数
        // 下面展示方法2，双指针
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     * 输入：n = 5
     * 输出：[0,1,1,2,1,2]
     * 解释：
     *  0 --> 0
     *  1 --> 1
     *  2 --> 10
     *  3 --> 11
     *  4 --> 100
     *  5 --> 101
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            int res = 0, j = i;
            while(j != 0) { // TODO: 2022/10/23 二进制数中 “1” 的个数
                res += j & 1;
                j >>>= 1;
            }
            ans[i] = res;
        }
        return ans;
    }

    /**
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
     * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * 输出：[5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        /*if (nums == null || nums.length == 0) return new ArrayList<>();
        List<Integer> help = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            help.add(i);
        }
        for (int num : nums) {
            if (help.contains(num)) help.remove((Object) num);
        }
        return help;*/ // 会超时
        List<Integer> ans = new ArrayList<>(); // TODO: 2022/10/23
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) ans.add(i);
        }
        return ans;
    }

    /**
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     */
    public int hammingDistance(int x, int y) {
        int ans = x ^ y;
        if (ans == 0) return 0;
        int count = 0;
        while (ans != 0){
            count += ans & 1;
            ans >>>= 1;
        }
        return count;
    }
}
