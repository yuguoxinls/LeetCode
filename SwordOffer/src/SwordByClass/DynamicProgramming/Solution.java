package SwordByClass.DynamicProgramming;

public class Solution {
    /**
     * 10. 斐波那契数列
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public int fib(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = (a+b) % 1000000007;
            a = b % 1000000007;
            b = sum;
        }
        return b;
    }

    /**
     * 10.2 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 示例 2：
     * 输入：n = 7
     * 输出：21
     * 示例 3：
     * 输入：n = 0
     * 输出：1
     */
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n <= 2) return n;
        int a = 1, b = 2, sum;
        for (int i = 3; i <= n; i++) {
            sum = (a+b) % 1000000007;
            a = b % 1000000007;
            b = sum;
        }
        return b;
    }

    /**
     * 42. 连续子数组的最大和
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     * 示例1:
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    public int maxSubArray(int[] nums) {
        // dp[i]表示以数组中第 i 个元素结尾的所有子数组的和的最大值，最后遍历dp数组，找到最大值即可
        // dp[i-1]表示以数组第 i-1 个元素结尾的所有子数组的和的最大值
        // TODO: 2023/1/13 状态转移方程的构建
        //  1. dp[i-1] > 0, dp[i] = dp[i-1]+nums[i] 前面 i-1 是正的，能对我产生正影响，就加上
        //  2. dp[i-1] <=0, dp[i] = nums[i] 前面 i-1 是负的，加上后还没我自己大，就不加了
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i-1] > 0) dp[i] = dp[i-1]+nums[i];
            else dp[i] = nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 47. 礼物的最大价值
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     * 示例 1:
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     */
    public int maxValue(int[][] grid) {
        // dp[i][j]表示 i,j 处的最大礼物价值，dp[m-1][n-1]即是答案
        // 对于 i,j 处的礼物，只能是从它的左边来，或者是上边来的
        // 如果在上边界，那么只能从左边来；如果在左边界，只能从上边来
        // 1. dp[i][j] = dp[i][j-1] + grid[i][j]
        // 2. dp[i][j] = dp[i-1][j] + grid[i][j]
        // dp[i][j] = max(1,2)
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // TODO: 2023/1/13 先初始化第一行和第一列，再算其他的，因为其他的要用到第一行和第一列
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.max(dp[i][j-1] + grid[i][j], dp[i-1][j] + grid[i][j]);
            }
        }
        return dp[m-1][n-1];
    }
}
