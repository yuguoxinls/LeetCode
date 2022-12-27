package LeetCodeByClass.DataStructure.ArrayAndMatrix;

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

}
