package LeetCodeByClass.Algorithm.DynamicProgramming;

import java.util.Arrays;

public class Fibonacci {
    /**
     * 斐波那契数列问题
     */

    /**
     * 1. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * 示例 2：
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2, sum = a + b;
        for (int i = 3; i < n; i++) {
            a = b;
            b = sum;
            sum = a + b;
        }
        return sum;
    }

    /**
     * 2. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    public int rob(int[] nums) { // TODO: 2022/11/16 动态规划经典步骤
        /**
         * 动态规划问题标准 4 步
         * 1. 定义子问题
         *  假设数组长度为 n，令f(n)表示偷这 n 间房子最多能偷的钱数，那么子问题为
         *  f(k)，k <= n, 也就是偷 k 间房子最多能偷的钱数
         *  可见，k可以是0，1，2，...，n，因此这是子问题
         * 2. 写出子问题的递推关系
         *  也就是要找出 f(k) 和 f(k-1) 或 f(k-2) 等的递推关系，因此我们可以把f(k-1)也理解一下，有助于写出递推关系
         *  f(k) 偷 k 间房子最多能偷的钱数，f(k-1) 偷 k-1 间房子最多能偷的钱数，这里就差出来一间房子，第k间，设它的钱数为 num
         *  (1) 如果偷了第 k 间房子，那么第 k-1 间房子肯定不能偷，此时 f(k) = f(k-2) + num
         *  (2) 如果不偷第 k 间房子，那么第 k-1 间房子可以偷，此时 f(k) = f(k-1)
         * 要找到最多的钱数，只要获取上面两种情况的最大值即可，即：f(k) = max(f(k-2) + num, f(k-1))
         * 3. 确定 DP 数组的计算顺序
         * DP数组，又叫子问题数组，其实就是存储了子问题的所有解的数组，可以看到，f(k) 的值是依赖 f(k-1) 和 f(k-2)的，因此计算顺序自然是从左往右
         * 4. 空间优化（可选）
         * 空间优化是动态规划问题的进阶内容了。对于初学者来说，可以不掌握这部分内容。
         * 空间优化的基本原理是，很多时候我们并不需要始终持有全部的 DP 数组。
         * 对于小偷问题，我们发现，最后一步计算 f(n) 的时候，实际上只用到了 f(n−1) 和 f(n−2) 的结果。n−3 之前的子问题，实际上早就已经用不到了。
         * 那么，我们可以只用两个变量保存两个子问题的结果，就可以依次计算出所有的子问题。
         */
        /*if (nums.length == 0) return 0; // 确定初始值，没房子，当然屁都偷不到
        if (nums.length == 1) return nums[0]; // 就一间房子，当然最多能偷的就是这一间的钱*/

        // 子问题：
        // f(k) = 偷 [0..k) 这 k 间房能偷到的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }

        /*int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i-1] + dp[i-2]);
        }

        return dp[nums.length];*/
        // 优化
        int pre = 0, cur = 0; // pre 表示dp[i-2]， cur 表示 dp[i-1]
        for (int num : nums) {
            int sum = Math.max(cur, num+pre);
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    /**
     * 3. 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * 示例 1：
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2：
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 3：
     * 输入：nums = [1,2,3]
     * 输出：3
     */
    public int rob2(int[] nums) { // TODO: 2022/11/16 拆分成两个单排
        /**
         * 和上一题的主要区别在于这次的房屋是圆形排列，也就是第一间和最后一间不能同时偷
         * 因此可以拆成 2 个，
         *  偷第一间，不偷最后一间
         *  偷最后一间，不偷第一间
         * 取上述两个的最大值
         */
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(rob(Arrays.copyOfRange(nums, 0, nums.length-1)), rob(Arrays.copyOfRange(nums, 1, nums.length))); // copyOfRange 方法都是闭区间
    }























}
