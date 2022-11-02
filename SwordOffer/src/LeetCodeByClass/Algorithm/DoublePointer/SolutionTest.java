package LeetCodeByClass.Algorithm.DoublePointer;

import org.testng.annotations.Test;

import java.util.Arrays;

public class SolutionTest {

    Solution solution = new Solution();

    @Test
    public void judgeSquareSum() {
        System.out.println(solution.judgeSquareSum(1));
    }

    @Test
    public void reverseVowels() {
        String s = "hello";
        System.out.println(solution.reverseVowels(s));
    }

    @Test
    public void validPalindrome() {
        System.out.println(solution.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
        char c = 127;
        int sum = 200;
        c += 1;
        sum += c;
        System.out.println(sum);
    }

    @Test
    public void merge() {
        int[] nums1 = {1,2,3,0,0,0}, nums2 = {2,5,6};
        solution.merge(nums1,3,nums2,3);
        System.out.println(Arrays.toString(nums1));
    }
}