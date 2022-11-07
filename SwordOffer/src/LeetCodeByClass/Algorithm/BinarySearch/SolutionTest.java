package LeetCodeByClass.Algorithm.BinarySearch;

import org.testng.annotations.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void mySqrt() {
        System.out.println(solution.mySqrt(2147395599));
    }

    @Test
    public void nextGreatestLetter() {
        char[] letters = {'c', 'c', 'c', 'c', 'j'};
        System.out.println(solution.nextGreatestLetter(letters, 'c'));
    }

    @Test
    public void singleNonDuplicate() {
        int[] nums = {1,1,3,3,4,4,5,8,8};
        System.out.println(solution.singleNonDuplicate(nums));
    }
}