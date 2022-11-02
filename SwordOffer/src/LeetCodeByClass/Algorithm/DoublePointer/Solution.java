package LeetCodeByClass.Algorithm.DoublePointer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * 1. 有序数组的 Two Sum
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
     * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * 示例 1：
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     * 示例 2：
     * 输入：numbers = [2,3,4], target = 6
     * 输出：[1,3]
     * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
     * 示例 3：
     * 输入：numbers = [-1,0], target = -1
     * 输出：[1,2]
     * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     */
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] < target) l++;
            else if (numbers[l] + numbers[r] > target) r--;
            else return new int[]{l + 1, r + 1}; // TODO: 2022/10/31 看清题目，下标从 1 开始，真tm坏
        }
        return new int[]{-1, -1};
    }

    /**
     * 2. 平方数之和
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
     * 示例 1：
     * 输入：c = 5
     * 输出：true
     * 解释：1 * 1 + 2 * 2 = 5
     * 示例 2：
     * 输入：c = 3
     * 输出：false
     */
    public boolean judgeSquareSum(int c) {
        long l = 0, r = (long) Math.sqrt(c); // TODO: 2022/10/31 不能使用int 会报错，注意r的初始值
        while (l <= r) {
            long sum = l * l + r * r;
            if (sum > c) r--;
            else if (sum < c) l++;
            else return true;
        }
        return false;
    }

    /**
     * 3. 反转字符串中的元音字符
     * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
     * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
     * 示例 1：
     * 输入：s = "hello"
     * 输出："holle"
     * 示例 2：
     * 输入：s = "leetcode"
     * 输出："leotcede"
     */
    public String reverseVowels(String s) {
        StringBuilder str = new StringBuilder(s);
        int l = 0, r = str.length() - 1;
        while (l < r) {
            while (l < r && !isVowel(str.charAt(l))) l++;
            while (l < r && !isVowel(str.charAt(r))) r--;
            char tmp = str.charAt(l);
            str.setCharAt(l, str.charAt(r));
            str.setCharAt(r, tmp);
            l++;
            r--;
        }
        return str.toString();
    }
    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    /**
     * 4. 验证回文串 II
     * 给你一个字符串 s，最多 可以从中删除一个字符。
     * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：s = "aba"
     * 输出：true
     * 示例 2：
     * 输入：s = "abca"
     * 输出：true
     * 解释：你可以删除字符 'c' 。
     * 示例 3：
     * 输入：s = "abc"
     * 输出：false
     */
    public boolean validPalindrome(String s) { // TODO: 2022/10/31 变体回文串
        StringBuilder str = new StringBuilder(s);
        int l = 0, r = str.length() - 1;
        while (l <= r){
            if (str.charAt(l) == str.charAt(r)){
                l++;
                r--;
            }else {
                return validPalindrome(str, l, r-1) || validPalindrome(str, l+1, r);
            }
        }
        return true;
    }
    private boolean validPalindrome(StringBuilder str, int l, int r) {
        while (l <= r){
            if (str.charAt(l) != str.charAt(r)) return false;
            else {
                l++;
                r--;
            }
        }
        return true;
    }

    /**
     * 5. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
     * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     * 示例 1：
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
     * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
     * 示例 2：
     * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
     * 输出：[1]
     * 解释：需要合并 [1] 和 [] 。
     * 合并结果是 [1] 。
     * 示例 3：
     * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
     * 输出：[1]
     * 解释：需要合并的数组是 [] 和 [1] 。
     * 合并结果是 [1] 。
     * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 方法一：双指针
        /*int[] newNum1 = Arrays.copyOfRange(nums1, 0, m);
        int i = 0, j = 0, index = 0;
        while (i < newNum1.length && j < nums2.length){
            if (newNum1[i] <= nums2[j]){
                nums1[index++] = newNum1[i++];
            }else {
                nums1[index++] = nums2[j++];
            }
        }
        if (i >= newNum1.length){
            while (j < nums2.length) nums1[index++] = nums2[j++];
        }else {
            while (i < newNum1.length) nums1[index++] = newNum1[i++];
        }*/
        // 方法二：从后部插入, 还不如方法一快
        int i = nums1.length;
        while (n > 0) {
            if (m > 0 && nums1[m - 1] > nums2[n - 1]) {
                nums1[i - 1] = nums1[m - 1];
                i--;
                m--;
            }else {
                nums1[i - 1] = nums2[n - 1];
                i--;
                n--;
            }
        }
    }

    /**
     * 6. 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     */
    public boolean hasCycle(ListNode head) { // TODO: 2022/11/1 方法已经知道，要注意括号内的条件如何去写
        if (head == null || head.next == null) return false;
        ListNode fast = head.next, slow = head;
        while (slow != fast){
            if (fast == null || fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 7. 通过删除字母匹配到字典里最长单词
     * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
     * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
     * 示例 1：
     * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
     * 输出："apple"
     * 示例 2：
     * 输入：s = "abpcplea", dictionary = ["a","b","c"]
     * 输出："a"
     */
    public String findLongestWord(String s, List<String> dictionary) { // TODO: 2022/11/1
        /*Collections.sort(dictionary, (a, b)->{
            if (a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        }); // 先对 dictionary 中的元素进行自定义排序，按照字符串长度降序，字典序升序
        int n = s.length();
        for (String str : dictionary) {
            int m = str.length();
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (s.charAt(i) == str.charAt(j)) j++;
                i++;
            } // 比对当前str是否是s的子串，
            if (j == m) return str; // j遍历到了最后，说明是子串，而且dictionary已经排好序了，最靠前的第一个子串，就是满足题目要求的子串，直接返回即可
        }
        return "";*/
        // 官方题解省去了最开始的排序步骤，用res保存满足要求的子串
        String res = "";
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < t.length() && j < s.length()) {
                if (t.charAt(i) == s.charAt(j)) {
                    ++i;
                }
                ++j;
            } // 在找满足s的子串
            if (i == t.length()) { // 说明当前的t是s的子串
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    // 如果t的长度更长 或者 长度相同的情况下，字典序更小，则更新res结果
                    // compareTo方法，按字典顺序比较两个字符串
                    res = t;
                }
            }
        }
        return res;
    }
}
