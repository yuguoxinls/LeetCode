package LeetCodeByClass.Algorithm.BinarySearch;

import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void findMin() {
        int[] nums = {4,5,6,7};
        System.out.println(solution.findMin(nums));
    }

    @Test
    public void searchRange() {
        int[] nums = {1,2,2,3,4,5,5,6,6,6,6,7,8};
        System.out.println(Arrays.toString(solution.searchRange(nums, 3)));
    }
}