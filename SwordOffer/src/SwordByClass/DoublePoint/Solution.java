package SwordByClass.DoublePoint;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 57. 和为s的两个数字
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。
     * 如果有多对数字的和等于s，则输出任意一对即可。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[2,7] 或者 [7,2]
     * 示例 2：
     * 输入：nums = [10,26,30,31,47,60], target = 40
     * 输出：[10,30] 或者 [30,10]
     */
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left<right){
            if (nums[left]+nums[right] < target) left++;
            else if (nums[left]+nums[right] > target) right--;
            else return new int[] {nums[left], nums[right]};
        }
        return new int[0];
    }

    /**
     * 57.2 和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * 示例 1：
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     * 示例 2：
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     */
    public int[][] findContinuousSequence(int target) {
        int left = 1, right = 2;
        int sum = left + right;;
        List<int[]> ans = new ArrayList<>();
        while (left < right){
            if (sum < target) {
                right++;
                sum += right;
            }else if(sum > target){
                sum -= left;
                left++;
            }else {
                int num = left;
                int[] sub = new int[right-left+1];
                for (int i = 0; i < sub.length; i++) {
                    sub[i] = num++;
                }
                ans.add(sub);
                sum -= left;
                left++;
            }
        }
        return ans.toArray(new int[0][]);
    }

    /**
     * 58. 翻转单词顺序
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder ans = new StringBuilder();
        int left = s.length() - 1, right = left;
        while (left >= 0){
            while (left >= 0 && s.charAt(left) != ' ') left--;
            ans.append(s.substring(left+1, right+1)).append(' ');
            while (left >= 0 && s.charAt(left) == ' ') left--;
            right = left;
        }
        return ans.toString().trim();
    }

    /**
     * 58.2 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * 示例 2：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     */
    public String reverseLeftWords(String s, int n) {
//        return s.substring(n) + s.substring(0,n);
        StringBuilder ans = new StringBuilder();
        for (int i = n; i < s.length(); i++){
            ans.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++){
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }
}
