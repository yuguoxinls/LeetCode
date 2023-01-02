package SwordByClass.ArrayAndMatrix;

import java.util.*;

public class Solution {
    /**
     * 3. 数组中重复的数字
     * 找出数组中重复的数字。
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     */
    public int findRepeatNumber(int[] nums) {
        /*Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)){
                set.add(num);
            }else {
                return num;
            }
        }
        return -1;*/
        // TODO: 2023/1/1 上面的方法没有充分利用到题目的条件，0～n-1的n个数，其中有重复，说明数和索引是对不上的，可以利用这一特点
        // 第一次遇到数字 x 时，将其交换至索引 x 处
        // 第二次遇到数字 x 时，一定有 nums[x]=x ，此时即可得到一组重复数字
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) { // 如果当前的这个数和索引相等，说明在正确的位置，那么看下一个数
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) { // 第二次遇到数字 x 时，一定有 nums[x]=x ，此时即可得到一组重复数字
                return nums[i];
            }
            // 第一次遇到数字 x 时，将其交换至索引 x 处
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

    /**
     * 4. 二维数组中的查找
     * 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length){
            if (matrix[i][j] < target){
                j++;
            }else if (matrix[i][j] > target){
                i--;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 5. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     */
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        StringBuilder res = new StringBuilder();
        for (char c : chars) {
            if (c == ' '){
                res.append("%20");
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }

    /**
     * 29. 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
        int[] ans = new int[(bottom+1)*(right+1)];
        // TODO: 2023/1/2
        int index = 0;
        while (true){
            for (int i = left; i <= right; i++) {
                ans[index++] = matrix[top][i];
            }
            if (++top > bottom) break;

            for (int i = top; i <= bottom; i++) {
                ans[index++] = matrix[i][right];
            }
            if (--right < left) break;

            for (int i = right; i >= left; i--) {
                ans[index++] = matrix[bottom][i];
            }
            if (--bottom < top) break;

            for (int i = bottom; i >= top; i--) {
                ans[index++] = matrix[i][left];
            }
            if (++left > right) break;
        }
        return ans;
    }

    /**
     * 50. 第一个只出现一次的字符
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     * 示例 1:
     * 输入：s = "abaccdeff"
     * 输出：'b'
     * 示例 2:
     * 输入：s = ""
     * 输出：' '
     */
    public char firstUniqChar(String s) {
        // TODO: 2023/1/2 使用hash表
        // 1. 普通hash表
        /*Map<Character, Boolean> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            map.put(ch, !map.containsKey(ch));
        }
        for (char ch : chars) {
            if (map.get(ch)) return ch;
        }
        return ' ';*/
        // 2. 有序hash表，表中键值对顺序是按照插入顺序排列的
        Map<Character, Boolean> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            map.put(ch, !map.containsKey(ch));
        }
        for (Map.Entry<Character, Boolean> m : map.entrySet()) {
            if (m.getValue()) return m.getKey();
        }
        return ' ';
    }






}
