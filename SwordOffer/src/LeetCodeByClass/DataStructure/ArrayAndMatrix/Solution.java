package LeetCodeByClass.DataStructure.ArrayAndMatrix;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 1. 移动零
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
        // TODO: 2022/12/27 把不是零的数往前移，最后补零
        int idx = 0; // 用于指向移动目的地 的指针
        for (int num : nums) {
            if (num != 0) { // 当前的数不是零，就把当前的数移到目的地
                nums[idx++] = num;
            }
        }
        while (idx < nums.length) { // 只要目的地指针没出界，剩下的就都是零
            nums[idx++] = 0;
        }
    }

    /**
     * 2. 重塑矩阵
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
     * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     * 示例 1：
     * 输入：mat = [[1,2],[3,4]], r = 1, c = 4
     * 输出：[[1,2,3,4]]
     * 示例 2：
     * 输入：mat = [[1,2],[3,4]], r = 2, c = 4
     * 输出：[[1,2],[3,4]]
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if (m*n != r*c) return mat;
        /*List<Integer> tmp = new ArrayList<>();
        for (int[] row : mat) {
            for (int data : row) {
                tmp.add(data);
            }
        }
        int[][] ans = new int[r][c];
        int index = 0;
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                ans[i][j] = tmp.get(index++);
            }
        }
        return ans;*/ // 显然，非常慢
        // TODO: 2022/12/27 用 index/col.length index%col.length 获取mat中的元素
        int[][] reshapedNums = new int[r][c];
        int index = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                reshapedNums[i][j] = mat[index/n][index%n];
                index++;
            }
        }
        return reshapedNums;
    }

    /**
     * 3. 最大连续 1 的个数
     * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
     * 示例 1：
     * 输入：nums = [1,1,0,1,1,1]
     * 输出：3
     * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     * 示例 2:
     * 输入：nums = [1,0,1,1,0,1]
     * 输出：2
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, max = 0;
        for (int num : nums) {
            if (num == 1) count++;
            else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        return max;
    }

    /**
     * 4. 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * 示例 1：
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
     * 输出：true
     * 示例 2：
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
     * 输出：false
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int i = row - 1, j = 0;
        while (i >= 0 && j < col){
            int curNum = matrix[i][j];
            if (target > curNum) {
                j++;
            }else if (target < curNum){
                i--;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 5. 有序矩阵中第 K 小的元素
     * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
     * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
     * 示例 1：
     * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
     * 输出：13
     * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
     * 示例 2：
     * 输入：matrix = [[-5]], k = 1
     * 输出：-5
     */
    public int kthSmallest(int[][] matrix, int k) {
        // TODO: 2022/12/28 二分查找
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1]; // 得到矩阵中最大和最小的2个数
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(matrix, mid, k, n)) { // check用来判断猜测的这个数 mid 和 最终的答案之间的大小关系，进而来移动left和right指针
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean check(int[][] matrix, int mid, int k, int n) {
        // 给定一个数mid，矩阵中小于等于mid的数和大于mid的数，以右上-左下对角线为界，分成两个部分
        // 所有小于等于mid的数都在左上部分
        // 统计小于等于mid的数有几个，假设有num个
        // 如果num<k，说明猜测的mid过小，导致有很少的数比mid小，达不到k个数，此时该把左指针向右移动
        // 反之，mid则过大，此时该把右指针向左移动
        int i = n - 1;
        int j = 0; // matrix[i][j]初始在矩阵左下角
        int num = 0;
        while (i >= 0 && j < n) { // i,j不越界
            if (matrix[i][j] <= mid) { // 如果当前的数比mid小，那么以这个数为界，向上所有的数都比mid小，因此num = num+(i+1)
                num += i + 1;
                j++; // 同时向右移动
            } else { // 当前数比mid大，应该往小的方向，也就是向上移动
                i--;
            }
        }
        return num >= k;
    }

    /**
     * 6. 错误的集合
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     * 示例 1：
     * 输入：nums = [1,2,2,4]
     * 输出：[2,3]
     * 示例 2：
     * 输入：nums = [1,1]
     * 输出：[1,2]
     */
    public int[] findErrorNums(int[] nums) {
        int length = nums.length;
        int[] tmp = new int[length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[nums[i]-1] += 1;
        }
        int[] ans = new int[2];
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == 2) ans[0] = i+1;
            else if (tmp[i] == 0) ans[1] = i+1;
            if (ans[0] != 0 && ans[1] != 0) break;
        }
        return ans;
        // TODO: 2022/12/28 用hash表
    }

    /**
     * 7. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     * 示例 1：
     * 输入：nums = [1,3,4,2,2]
     * 输出：2
     * 示例 2：
     * 输入：nums = [3,1,3,4,2]
     * 输出：3
     */
    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            if (val != 1) return key;
        }
        return 0; // 太耗时，不可取
        // TODO: 2022/12/28 二分
    }

    /**
     * 8. 数组的度
     * 给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     * 示例 1：
     * 输入：nums = [1,2,2,3,1]
     * 输出：2
     * 解释：
     * 输入数组的度是 2 ，因为元素 1 和 2 的出现频数最大，均为 2 。
     * 连续子数组里面拥有相同度的有如下所示：
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组 [2, 2] 的长度为 2 ，所以返回 2 。
     * 示例 2：
     * 输入：nums = [1,2,2,3,1,4,2]
     * 输出：6
     * 解释：
     * 数组的度是 3 ，因为元素 2 重复出现 3 次。
     * 所以 [2,2,3,1,4,2] 是最短子数组，因此返回 6 。
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;
    }

}
