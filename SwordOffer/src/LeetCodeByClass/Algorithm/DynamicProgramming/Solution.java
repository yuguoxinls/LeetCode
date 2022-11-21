package LeetCodeByClass.Algorithm.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalInt;

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


}
