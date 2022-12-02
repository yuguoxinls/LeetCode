package LeetCodeByClass.Algorithm.DynamicProgramming;

import java.util.*;

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

    // 分割整数问题
    /**
     * 1. 整数拆分
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * 返回 你可以获得的最大乘积 。
     * 示例 1:
     * 输入: n = 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * 示例 2:
     * 输入: n = 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     */
    public int integerBreak(int n) {
        /*if (n==2 || n==3) return n-1;
        int a = n/3;
        int b = n%3;
        if (b == 0){
            return (int) Math.pow(3, a);
        }else if (b == 1){
            return (int) (Math.pow(3, a-1) * 4);
        }else {
            return (int) (Math.pow(3, a-1)*6);
        }*/ // 这是用到了数学，下面使用动态规划，当然用数学规律更快
        /**
         * 1. 子问题：dp[i]表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积，则dp[n]即是最后结果
         * 2. 状态转移：将 i 拆分成 j 和 i-j，此时有两种情况：
         *  (1) i-j 不再继续拆，此时dp[i] = j * (i-j)
         *  (2) i-j 继续拆，此时dp[i] = j * dp[i-j]
         * 因此，对于固定的 j ，dp[i] = max(j * (i-j), j * dp[i-j])
         * 而 j 的取值范围是1~i，因此要遍历
         */
        // 题目要求正整数，因此dp[0]=dp[1]=0
        int[] dp = new int[n+1];
        dp[0]=dp[1]=0;
        for (int i = 2; i < dp.length; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i-j), j * dp[i-j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    /**
     * 2. 完全平方数 (本题之前已经在 BFS 模块做过)
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     * 示例 1：
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * 示例 2：
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     */
    public int numSquares(int n) {
        /**
         * 1. 子问题：dp[i]表示将 i 拆分成完全平方数之和的最少数量，则dp[n]即是答案
         *  对于 i 来说，相加等于 i 的完全平方数一定在 [1, 根号 i]之间，可以枚举区间内的每个数 j
         *  对于 j，那么下面要找的就是 i - j^2，此时的子问题出现了
         * 2. 状态转移：dp[i] = 1 + min(dp[i-j^2]) j from 1 to 根号 i
         */
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, dp[i - j * j]);
            }
            dp[i] = minn + 1;
        }
        return dp[n];
    }

    /**
     * 3. 解码方法
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
     * "AAJF" ，将消息分组为 (1 1 10 6)
     * "KJF" ，将消息分组为 (11 10 6)
     * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * 题目数据保证答案肯定是一个 32 位 的整数。
     * 示例 1：
     * 输入：s = "12"
     * 输出：2
     * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2：
     * 输入：s = "226"
     * 输出：3
     * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     * 示例 3：
     * 输入：s = "0"
     * 输出：0
     * 解释：没有字符映射到以 0 开头的数字。
     * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
     * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
     */
    public int numDecodings(String s) {
        /**
         * 1. 子问题：设dp[i]表示到字符串的第 i 个字符的解码总数，则dp[s.length()]就是答案
         *  针对第 i 个字符，它的解码方式有以下两种可能
         *  (1) 只能自己单独解码，也就是一位数，则它可以映射为 A ~ I 中的其中一个字符，但是这种情况要求这个字符不能是 0，此时dp[i] = dp[i-1]
         *  (2) 只能和第 i-1 个字符绑定解码，也就是两位数，则它可以映射为 J ~ Z 中的其中一个字符，
         *      同样，由于是两位数，此时的 第 i-1 个字符不能是 0，并且这两个字符拼到一起的数字要小于 26, 此时dp[i] = dp[i-2]
         *  (3) 既能自己单独解码，也能够和前一个字符绑定解码
         */
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            if (s.charAt(i-1) != '0'){
                dp[i] += dp[i-1];
            }
            if (i > 1 && s.charAt(i-2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)){
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }

    // 最长递增子序列
    /**
     * 1. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     */
    public int lengthOfLIS(int[] nums) {
        /**
         * 1. 子问题：设 dp[i]表示以 nums[i]结尾的最长递增子序列的长度，则 max(dp)即是答案
         * 2. 状态转移：
         *  (1) 首先，dp[0]表示以 nums第一个元素结尾的最长递增子序列的长度，肯定是 1
         *  (2) 因为不是连续子数组，因此不能简单判断，要在每次判断时遍历[0,i)
         *  (3) 如果 nums[i] > nums[j], 此时满足题目要求，dp[i] = dp[j] + 1
         *  (4) 如果 nums[i] <= nums[j], 不满足要求直接跳过
         *  (5) dp[i] == max(dp[j]) j in [0,i)
         */
        /*int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;*/ // 时间复杂度太高，可用二分查找降低时间复杂度
        int[] tails = new int[nums.length]; // tails[i] 存储长度为 i + 1 的最长递增子序列的最后一个元素
        int len = 0;
        for(int num : nums) {
            int index = binarySearch(tails, len, num); // binarySearch返回num在tails数组中的索引位置
            tails[index] = num; // 把num放到对应位置
            if(len == index) len++;
        }
        return len;
    }
    private int binarySearch(int[] arr, int right, int target){
        int left = 0;
        while (left < right){
            int mid = left + (right - left)/2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * 2. 最长数对链
     * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
     * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
     * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
     * 示例：
     * 输入：[[1,2], [2,3], [3,4]]
     * 输出：2
     * 解释：最长的数对链是 [1,2] -> [3,4]
     */
    public int findLongestChain(int[][] pairs) { // TODO: 2022/11/21 自己对照上边的第一题写出来的，也能过，但是时间太长，应该也能用类似二分查找的方式优化
        /**
         * 1. 子问题：dp[i]表示以第 i 个数对为结尾的最长数对链的长度，i 从0开始
         * 2. 状态转移：对于第 i 个数对，同样没有要求是原数组中的连续数对，因此要遍历[0,i)
         *  (1) 对于第 j 个数对，pairs[j]，如果pairs[j][1] < pairs[i][0]，满足要求，此时的最长数对链的长度 dp[j]+1
         *  (2) 对于第 j 个数对，pairs[j]，如果pairs[j][1] >= pairs[i][0]，不满足要求，直接跳过
         */
        Arrays.sort(pairs, (o1, o2) -> {return o1[1] - o2[1];});
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    /**
     * 3. 摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
     * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     * 示例 1：
     * 输入：nums = [1,7,4,9,2,5]
     * 输出：6
     * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
     * 示例 2：
     * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
     * 输出：7
     * 解释：这个序列包含几个长度为 7 摆动序列。
     * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
     * 示例 3：
     * 输入：nums = [1,2,3,4,5,6,7,8,9]
     * 输出：2
     */
    public int wiggleMaxLength(int[] nums) {
        /**
         * 1. 子问题：dp[i]表示以nums[i]结尾的最长摆动子序列的长度，max(dp)即是答案
         * 2. 状态转移：同样没要求连续，也要遍历[0,i)
         * (1) 如果 (nums[j]-nums[j-1]) * (nums[j-1]-nums[j-2]) < 0, 说明差值异号，满足摆动序列要求
         * (2) 否则直接跳过
         * 由于仅有一个元素或者两个不等元素也视作摆动序列，因此dp[0]=1, dp[1] = 1  (满足条件的情况下)
         */
        /*if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        dp[1] = 2;
        int res = 0;
        for (int i = 2; i < dp.length; i++) {
            for (int j = 2; j < i; j++) {
                if ((nums[j]-nums[j-1]) * (nums[j-1]-nums[j-2]) < 0) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;*/ // TODO: 2022/11/21 自己写的，有bug
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    // 最长公共子序列
    /**
     * 1. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     * 示例 1：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     * 示例 2：
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
     * 示例 3：
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0 。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * 经典的二维dp问题
         * 符号定义：text[0:i] 表示 text 的长度为 i 的前缀
         * 1. 子问题：令dp[i][j]表示 text1[0:i], text2[0:j]的最长公共子序列的长度
         * 2. 状态转移：
         * (1) 边界情况：i == 0，text1[0:0]表示长度为 0 的前缀，也就是空串，当然是任何串的子串，因此dp[0][j]=0
         *              j == 0, 同理, dp[i][0]=0
         * (2) i > 0, j > 0:
         *  如果text1[i-1] == text2[j-1]，说明是公共字符，把它加进来，公共子串的长度增加 1，即 dp[i][j] = dp[i-1][j-1] + 1
         *  如果text1[i-1] != text2[j-1], 此时应该考虑
         *    (1)text1[0:i]和text2[0:j-1]的最长公共子序列
         *    (2)text2[0:i-1]和text2[0:j]的最长公共子序列
         *  应该取二者的最大值，即：dp[i][j] = max(dp[i-1][j], dp[i][j-1])
         */
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[text1.length()][text2.length()];
    }

    // 0-1背包问题
    /**
     * 1. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 示例 1：
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     * 示例 2：
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     */
    public boolean canPartition(int[] nums) {
        int sum = computeArraySum(nums);
        if (sum % 2 != 0) { // 两个相等的数相加的和必然是偶数
            return false;
        }
        int w = sum / 2; // 获得其中一个子集的和，看作背包的容量
        boolean[] dp = new boolean[w + 1];
        dp[0] = true;
        for (int num : nums) {                 // 0-1 背包一个物品只能用一次
            for (int i = w; i >= num; i--) {   // 从后往前，先计算 dp[i] 再计算 dp[i-num]
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[w];
    }
    private int computeArraySum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * 2. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * 示例 1：
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 示例 2：
     * 输入：nums = [1], target = 1
     * 输出：1
     */
    public int findTargetSumWays(int[] nums, int target) {
        /**
         * 该问题可以转换为 Subset Sum 问题，从而使用 0-1 背包的方法来求解。
         * 可以将这组数看成两部分，P 和 N，其中 P 使用正号，N 使用负号，有以下推导：
         *  sum(P) - sum(N) = target => sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N) => 2 * sum(P) = target + sum(nums)
         *  => sum(P) = (target + sum(nums))/2
         *  也就是说，找到一个子集，该子集的和等于(target + sum(nums))/2，就说明存在解
         */
        int sum = computeArraySum(nums);
        if (sum < target || (sum+target) % 2 == 1) return 0;
        int w = (sum+target) / 2;
        if (w < 0) return 0; // TODO: 2022/11/22 这是答案上没想到的特殊用例
        int[] dp = new int[w + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = w; i >= num; i--) {
                dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[w];
    }

    /**
     * 3. 一和零
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     * 示例 1：
     * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     * 输出：4
     * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
     * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
     * 示例 2：
     * 输入：strs = ["10", "0", "1"], m = 1, n = 1
     * 输出：2
     * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
     */
    public int findMaxForm(String[] strs, int m, int n) {
        /**
         * 这道题和经典的背包问题非常相似，但是和经典的背包问题只有一种容量不同，这道题有两种容量，即选取的字符串子集中的 0 和 1 的数量上限。
         * 经典的背包问题可以使用二维动态规划求解，两个维度分别是物品和容量。这道题有两种容量，因此需要使用三维动态规划求解，三个维度分别是字符串、0 的容量和 1 的容量。
         * 定义三维数组dp，其中 dp[i][j][k]表示在前 i 个字符串中，使用 j个 0和 k个 1 的情况下最多可以得到的字符串数量。假设数组 str 的长度为 l，则最终答案为 dp[l][m][n]。
         * 当没有任何字符串可以使用时，可以得到的字符串数量只能是 0，因此动态规划的边界条件是：当 i=0时，对任意 0≤j≤m和 0≤k≤n，都有 dp[i][j][k]=0
         * 当 1≤i≤l时，对于 strs中的第 i个字符串（计数从 1 开始），首先遍历该字符串得到其中的 0 和 1 的数量，分别记为 zeros 和 ones，然后对于 0≤j≤m 和 0≤k≤n，计算 dp[i][j][k]的值。
         * 当 0 和 1 的容量分别是 j 和 k 时，考虑以下两种情况：
         *      如果 j<zeros 或 k<ones，则不能选第 i 个字符串，此时有 dp[i][j][k]=dp[i−1][j][k]；
         *      如果 j≥zeros 且 k≥ones，则
         *              如果不选第 i 个字符串，有 dp[i][j][k]=dp[i−1][j][k]，
         *              如果选第 i 个字符串，有 dp[i][j][k]=dp[i−1][j−zeros][k−ones]+1，
         *      dp[i][j][k]的值应取上面两项中的最大值。
         */
        /*int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];*/
        // TODO: 2022/11/23 空间优化
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {    // 每个字符串只能用一次
            int ones = 0, zeros = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    /**
     * 4. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * 示例 1：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * 示例 2：
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * 示例 3：
     * 输入：coins = [1], amount = 0
     * 输出：0
     */
    public int coinChange(int[] coins, int amount) {
        /**
         * 因为硬币可以重复使用，因此这是一个完全背包问题。完全背包只需要将 0-1 背包的逆序遍历 dp 数组改为正序遍历即可。
         */
        if (amount == 0 || coins == null) return 0;
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) { //将逆序遍历改为正序遍历
                if (i == coin) {
                    dp[i] = 1;
                } else if (dp[i] == 0 && dp[i - coin] != 0) {
                    dp[i] = dp[i - coin] + 1;

                } else if (dp[i - coin] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    /**
     * 5. 零钱兑换 II  https://leetcode.cn/problems/coin-change-ii/
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     * 题目数据保证结果符合 32 位带符号整数。
     * 示例 1：
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * 示例 2：
     * 输入：amount = 3, coins = [2]
     * 输出：0
     * 解释：只用面额 2 的硬币不能凑成总金额 3 。
     * 示例 3：
     * 输入：amount = 10, coins = [10]
     * 输出：1
     */
    public int change(int amount, int[] coins) {
        /**
         * 完全背包问题，使用 dp 记录可达成目标的组合数目。
         */
        if (coins == null) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 6. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     *      注意，你可以重复使用字典中的单词。
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        /**
         * 可以重复使用，因此是完全背包问题
         * 求解 顺序的完全背包问题 时，对物品的迭代应该放在最里层，对背包的迭代放在外层，只有这样才能让物品按一定顺序放入背包中。
         */
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {   // 对物品的迭代应该放在最里层
                int len = word.length();
                if (len <= i && word.equals(s.substring(i - len, i))) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }
        return dp[n];
    }

    /**
     * 7. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     * 题目数据保证答案符合 32 位整数范围。
     * 示例 1：
     * 输入：nums = [1,2,3], target = 4
     * 输出：7
     * 解释：
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * 请注意，顺序不同的序列被视作不同的组合。
     * 示例 2：
     * 输入：nums = [9], target = 3
     * 输出：0
     */
    public int combinationSum4(int[] nums, int target) {
        /**
         * 同样是涉及顺序的完全背包问题
         */
        /*if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] maximum = new int[target + 1];
        maximum[0] = 1;
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {
                maximum[i] += maximum[i - nums[j]];
            }
        }
        return maximum[target];*/
        int[] dp = new int[target + 1]; // TODO: 2022/11/24 官方题解，更快
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    // 股票交易
    /**
     * 1. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * 示例 2:
     * 输入: prices = [1]
     * 输出: 0
     */
    public int maxProfit(int[] prices) {
        /**
         * 1. 子问题：dp[i] 表示到第 i 天结束之后累计的最大利润，为了和数组对应，i从零开始，即dp[0]表示第 1 天累计的最大利润
         * 2. 状态转移：对于第 i 天，考虑以下 3 个状态
         * (1) 当前持有股票，对应的累计最大利润记为 dp[i][0]
         * (2) 当前不持有股票，且处于冷冻期，对应的累计最大利润记为 dp[i][1] **处于冷冻期，表示第 i 天结束之后处于冷冻期，其实是下一天无法操作，说人话也就是今天把股票卖了**
         * (3) 当前不持有股票，不处于冷冻期，对应的累计最大利润记为 dp[i][2] **说明今天没卖**
         * 考虑状态转移：
         * (1) 如果当前持有股票，这个股票的来源有 2 个可能：
         *      1. 前一天就已经持有了，今天什么也没干，此时的最大利润 dp[i][0] == dp[i-1][0]
         *      2. 当天才买的，而且前一天对应状态 (3)，如果对应状态(1)，此时就有2张股票了，不允许；对应状态(2)，今天就是冷冻期，不能买入股票，不符合假设；
         *          此时的最大利润为前一天对应的最大利润+买股票花的钱 dp[i][0] == dp[i-1][2] - prices[i]
         *      此时的最大利润 dp[i][0] = max(dp[i-1][0], dp[i-1][2] - prices[i])
         * (2) 如果当前不持有股票，且处于冷冻期，说明当前把股票卖了，而且股票是之前买入的，此时的最大利润为前一天对应的利润 + 今天卖股票的钱 dp[i][1] = dp[i-1][0] + prices[i]
         * (3) 如果当前不持有股票，不处于冷冻期，说明今天没卖股票，而当前不持有股票，说明也没买，也就是没进行任何操作
         *      1. 如果前一天处于冷冻期，dp[i][2] = dp[i-1][1]
         *      2. 如果前一天不处于冷冻期，dp[i][2] = dp[i-1][2]
         *    此时的最大利润 dp[i][2] = max(dp[i-1][1], dp[i-1][2])
         * 综上，dp[i] = max(dp[i][0], dp[i][1], dp[i][2]), ** 如果最后一天结束之后仍然持有股票，是没有意义的，因此最后可以简化 ** dp[i] = max(dp[i][1], dp[i][2])
         * 3. 设 prices.length = n，则dp[n-1]即是答案
         * 4. 初始化，对于第一天，也就是dp[0], dp[0][0] 表示当前持有股票对应的最大利润，显然此时利润是负的，也就是花的买股票的钱；另外两个不持有股票，也就没花钱，利润为 0
         */
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }
        return Math.max(dp[n-1][1], dp[n-1][2]);
    }

    /**
     * 2. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * 示例 1：
     * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出：8
     * 解释：能够达到的最大利润:
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
     * 示例 2：
     * 输入：prices = [1,3,7,5,10,3], fee = 3
     * 输出：6
     */
    public int maxProfit(int[] prices, int fee) {
        /**
         * 1. 子问题：dp[i]表示第 i 天结束后的最大利润
         * 2. 状态转移：对于第 i 天，考虑以下 2 个状态
         *  1. 第 i 天结束后手里没有股票 dp[i][0]
         *     1. 当天把股票卖了，此时 第 i 天结束后的最大利润：之前的利润+卖股票的钱-手续费：dp[i][0] = dp[i-1][1]+prices[i]-fee
         *     2. 之前就把股票卖了，而且一直没买，此时 第 i 天结束后的最大利润：dp[i][0] = dp[i-1][0]
         *  dp[i][0] = max(dp[i-1][1]+prices[i]-fee, dp[i-1][0])
         *  2. 第 i 天结束后手里有股票 dp[i][1]
         *     1. 当天买的，此时 第 i 天结束后的最大利润：之前的利润-买股票的钱：dp[i][1] = dp[i-1][0]-prices[i]
         *     2. 之前就有，一直没卖: dp[i][1] = dp[i-1][1]
         *  dp[i][1] = max(dp[i-1][0]-prices[i], dp[i-1][1])
         * 3. 初始化：第一天，也就是 i == 0，dp[0][0] = 0, dp[0][1] = -prices[0]
         * 4. dp[i] = max(dp[i][0], dp[i][1])，最后的结果为：dp[n-1]
         */
        /*if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i-1][1]+prices[i]-fee, dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0]-prices[i], dp[i-1][1]);
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);*/
        // 方法二：贪心，和最初版本的买股票的差别在于此次有手续费，可以把手续费算到成本里，这样就可以使用贪心了
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy; // TODO: 2022/11/25 这也不太一样
                buy = prices[i];
            }
        }
        return profit;
    }

    /**
     * 3.两个字符串的删除操作
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     * 每步 可以删除任意一个字符串中的一个字符。
     * 示例 1：
     * 输入: word1 = "sea", word2 = "eat"
     * 输出: 2
     * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
     * 示例  2:
     * 输入：word1 = "leetcode", word2 = "etco"
     * 输出：4
     */
    public int minDistance(String word1, String word2) {
        /**
         * 和 longestCommonSubsequence 差不多
         * 符号定义：word[0:i] 表示 word 的长度为 i 的前缀
         * 1. 子问题：令dp[i][j]表示使 word1[0:i]和word2[0:j]的相同的最少删除操作次数
         * 2. 状态转移：
         * (1) 边界情况：i == 0，word1[0:0]表示长度为 0 的前缀，也就是空串，空串想变成和其他串相同，只需其他串把自己的字符全删除即可，因此 dp[0][j] = j
         *             j == 0, 同理, dp[i][0] = i
         * (2) i > 0, j > 0:
         *    如果word1[i-1] == word2[j-1]，说明是公共字符，也就不需要操作，即 dp[i][j] = dp[i-1][j-1]
         *    如果word1[i-1] != word2[j-1], 此时应该考虑
         *     (1)word1[0:i]和word2[0:j-1]相同的最少删除操作次数+ 删除word2[j-1]的1次操作，dp[i][j] = dp[i][j-1]+1
         *     (2)word2[0:i-1]和word2[0:j]相同的最少删除操作次数+ 删除word1[i-1]的1次操作，dp[i][j] = dp[i-1][j]+1
         *         应该取二者的最小值，即：dp[i][j] = min(dp[i][j-1]+1, dp[i-1][j]+1)=min(dp[i][j-1], dp[i-1][j])+1
         */
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }// 上面都是初始化
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 4. 只有两个键的键盘
     * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
     * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
     * Paste（粘贴）：粘贴 上一次 复制的字符。
     * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
     * 示例 1：
     * 输入：3
     * 输出：3
     * 解释：
     * 最初, 只有一个字符 'A'。
     * 第 1 步, 使用 Copy All 操作。
     * 第 2 步, 使用 Paste 操作来获得 'AA'。
     * 第 3 步, 使用 Paste 操作来获得 'AAA'。
     * 示例 2：
     * 输入：n = 1
     * 输出：0
     */
    public int minSteps(int n) {  // TODO: 2022/12/2 是真看不懂
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                if (i % j == 0) {
                    f[i] = Math.min(f[i], f[j] + i / j);
                    f[i] = Math.min(f[i], f[i / j] + j);
                }
            }
        }
        return f[n];
    }

















}
