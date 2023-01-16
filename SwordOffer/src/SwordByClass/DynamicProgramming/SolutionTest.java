package SwordByClass.DynamicProgramming;

import org.junit.Test;

import java.util.Arrays;


public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void lengthOfLongestSubstring() {
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    public void nthUglyNumber() {
        System.out.println(solution.nthUglyNumber(10));
    }

    @Test
    public void constructArr() {
        System.out.println(Arrays.toString(solution.constructArr(new int[]{0})));
    }
}