package LeetCodeByClass.Algorithm.DynamicProgramming;

import java.util.Arrays;

public class Solution { // TODO: 2022/11/17 整个一个大todo

    // 矩阵路径问题
    /**
     * 1. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * 示例 1：
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     * 示例 2：
     * 输入：grid = [[1,2,3],[4,5,6]]
     * 输出：12
     */
    public int minPathSum(int[][] grid) {
        /**
         * 1. 子问题：设dp[i][j]表示到下标为 ij 的最小路径和，dp[m-1][n-1]即是我们要的结果
         * 2. 状态转移：题目要求 每次只能向下或者向右移动一步 ，因此对于dp[i][j]来说，它的前一步只能是从他的上边或者左边来的
         *  也即dp[i-1][j]或dp[i][j-1]，那么dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]，对于数组边界的讨论，只讨论上边界和左边界即可
         *  (1) 当dp[i][j]不是边界，dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
         *  (2) 当dp[i][j]是上边界，dp[i][j] = dp[i][j-1] + grid[i][j]
         *  (3) 当dp[i][j]是左边界，dp[i][j] = dp[i-1][j] + grid[i][j]
         *  (4) 当dp[i][j]既是左边界又是上边界，也就是左上角，dp[i][j] = grid[0][0]
         */
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                }else if (i == 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if (j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[row-1][col-1];
    }

    /**
     * 2. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角
     * 问总共有多少条不同的路径？
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     * 示例 2：
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     * 示例 3：
     * 输入：m = 7, n = 3
     * 输出：28
     * 示例 4：
     * 输入：m = 3, n = 3
     * 输出：6
     */
    public int uniquePaths(int m, int n) {
        /**
         * 1. 子问题：设dp[i][j]表示到下标为 ij 的不同路径数，dp[m-1][n-1]即是我们要的结果
         * 2. 状态转移：题目要求 每次只能向下或者向右移动一步 ，因此对于dp[i][j]来说，它的前一步只能是从他的上边或者左边来的
         *  也即dp[i-1][j]或dp[i][j-1]，那么到dp[i][j]的总的不同路径数就是从两个方向来的路径数之和，即 dp[i][j] = dp[i-1][j]+dp[i][j-1]
         *  对于数组边界的讨论，只讨论上边界和左边界即可
         *  (1) 当dp[i][j]不是边界，dp[i][j] = dp[i-1][j] + dp[i][j-1]
         *  (2) 当dp[i][j]是上边界，dp[i][j] = dp[i][j-1]
         *  (3) 当dp[i][j]是左边界，dp[i][j] = dp[i-1][j]
         *  (4) 当dp[i][j]既是左边界又是上边界，也就是左上角，dp[i][j] = grid[0][0]
         */
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }else if (i == 0) {
                    dp[i][j] = dp[i][j-1];
                }else if (j == 0) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];

    }

    // 数组区间问题
    /**
     * 1. 区域和检索 - 数组不可变
     * 给定一个整数数组  nums，处理以下类型的多个查询:
     * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
     * 实现 NumArray 类：
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
     * 示例 1：
     * 输入：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     */
    static class NumArray {
        /**
         * 使用前缀和
         * 计算 i 到 j 的和 等价于 (0到 j 的和) - (0 到 i-1 的和)
         * 也就是两个前缀和之差
         * 在初始化的时候，计算出每个下标对应的前缀和，那么最后只需要简单的做差即可
         */
        public int[] sums; // sums[i] 表示 0 到 i 的和

        public NumArray(int[] nums) {
            int tmp = 0;
            sums = new int[nums.length];
            for (int i = 0; i < sums.length; i++) {
                tmp += nums[i];
                sums[i] = tmp;
            }
        }

        public int sumRange(int left, int right) {
            return left == 0 ? sums[right] : sums[right] - sums[left-1];
        }
    }

    /**
     * 2. 等差数列划分
     * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
     * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
     * 子数组 是数组中的一个连续序列。
     * 示例 1：
     * 输入：nums = [1,2,3,4]
     * 输出：3
     * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
     * 示例 2：
     * 输入：nums = [1]
     * 输出：0
     */
    public int numberOfArithmeticSlices(int[] nums) {
        /**
         * 1. 子问题：dp[i]表示以nums[i]结尾的等差子数组的个数，则对dp数组进行累加就是最后结果
         * 2. 状态转移：
         *  (1) 如果 nums[i] - nums[i-1] == nums[i-1] - nums[i-2]，说明此时的nums[i]添加进来能构成等差数组，dp[i] = dp[i-1] + 1
         *  (2) 如果 nums[i] - nums[i-1] != nums[i-1] - nums[i-2]，说明此时的nums[i]添加进来不能构成等差数组，dp[i] = 0
         * dp[0] == dp[1] == 0
         */
        if (nums.length < 3) return 0;

        int[] dp = new int[nums.length];
        for (int i = 2; i < dp.length; i++) {
            if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                dp[i] = dp[i-1] + 1;
            }
        }
        int total = 0;
        for (int cnt : dp) {
            total += cnt;
        }
        return total;
    }






















}
