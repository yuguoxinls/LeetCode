package SwordByClass.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    /**
     * 48. 最长不含重复字符的子字符串
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int start = -1, res = 0, len = chars.length;
        if (len <= 1) return len;
        for (int i = 0; i < len; i++) {
            char curKey = chars[i];
            /*if (!map.containsKey(curKey)) {
                map.put(curKey, i);
                res = i-start;
            }
            else {
                Integer preIndex = map.get(curKey);
                res = Math.max(res, i-1-start);
                start = Math.max(preIndex, i);
                map.put(curKey, i);
            }*/ // 这点逻辑死活写不对了
            // TODO: 2023/1/16 k神 双指针/滑动窗口更直观
            if (map.containsKey(curKey)) {
                // 如果当前字符在之前出现过，得到之前的索引，并更新start值，注意更新的时候不是直接取之前的索引值，而是取较大的值，不懂为什么
                Integer preIndex = map.get(curKey);
                start = Math.max(preIndex, start);
            }
            map.put(curKey, i);
            res = Math.max(res, i-start); // 动态维护最后结果
        }
        return res;
    }

    /**
     * 49. 丑数
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     * 示例:
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     */
    public int nthUglyNumber(int n) {
        // TODO: 2023/1/16 dp[i-1]表示第 i 个丑数
        // dp[i] 肯定是由前面的某个丑数*2，*3，*5得到的，重点就是记录当前这个丑数是前面哪个丑数乘几得到的

        // 初始化dp数组
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0; // a，b，c分别对应2，3，5，初始都指向数组的首元素
        // 状态转移
        for (int i = 1; i < dp.length; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n-1];
    }

    /**
     * 60. n个骰子的点数
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     * 示例 1:
     * 输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     * 示例 2:
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     */
    public double[] dicesProbability(int n) {
        // TODO: 2023/1/16
        // 1 个骰子 点数和的范围[1,6]，有 6-1+1=6 种不同的取值
        // n 个骰子 。。。。。。[n,6n], 有 6n-n+1= 5n+1 。。。
        double[] res = new double[n*5+1]; // 因此结果集的长度为 5n+1
        // dp[i][j]表示使用了 i 个骰子，此时的点数和为 j 的概率为dp[i][j]
        // 骰子的范围：1～n，点数和的范围：n～6n
        // dp 2个维度分别为n+1, 6n+1, 使用从 1 开始的下标
        double[][] dp = new double[n+1][n*6+1];
        // 初始化
        for(int i = 1;i <= 6;i++){
            // 只有一个骰子时，点数和只有6种可能，即1，2，3，4，5，6
            // 每个点数和的概率相同，1/6
            dp[1][i] = 1.0/6;
        }
        // 已知 i-1 个骰子的情况，添加一个骰子，这个骰子只可能是1～6的一种
        for(int i = 2;i <= n;i++){
            for(int j = i;j <= i*6;j++){
                for(int k = 1;k <= 6;k++){
                    if(j-k > 0) dp[i][j] += dp[i-1][j-k]/6;
                    else break;
                }
            }
        }
        // 最后的结果集，n个骰子，点数和从n开始到6n
        for(int i = 0;i <= 5*n;i++){
            res[i] = dp[n][n+i];
        }
        return res;
    }

    /**
     * 66. 构建乘积数组
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
     * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
     * 不能使用除法
     * 示例:
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     */
    public int[] constructArr(int[] a) {
        // TODO: 2023/1/16 难点在于不能使用除法
        // 可以发现，结果之中的元素B[i] = i 左边元素的乘积 * i 右边元素的乘积
        // 因此，整体思路为：
        // 1. 第一轮循环得到当前元素左边所有元素的乘积，存储到结果集中
        // 2. 第二轮循环。。。。。。右。。。。。。。。，和之前的数相乘后存到结果集中
        int len = a.length;
        if (len == 0) return new int[0];
        int[] res = new int[len];
        res[0] = 1;
        // 左边
        for (int i = 1; i < res.length; i++) {
            // 由于第一个元素左边没有元素，因此从第二个元素开始
            res[i] = res[i-1] * a[i-1];
        }
        // 右边
        int tmp = 1; // 用于累乘右边元素
        for (int i = len-2; i >= 0; i--) {
            // 同样，由于最后一个元素右边没有元素，因此从倒数第二个元素开始
            tmp *= a[i+1];
            res[i] *= tmp;
        }
        return res;
    }
}
