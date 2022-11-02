package LeetCodeByClass.Algorithm.Sort;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SolutionTest {
    Solution solution = new Solution();
    @Test
    public void findKthLargest() {
        int[] nums = {3,2,5,9,6,2,8};
        System.out.println(solution.findKthLargest(nums, 3));
    }

    @Test
    public void topKFrequent() {
        int[] nums = {1};
        System.out.println(Arrays.toString(solution.topKFrequent(nums, 1)));
    }

    @Test
    public void frequencySort() {
        String s = "Aabb";
        System.out.println(solution.frequencySort(s));
    }

    @Test
    public void sortColors() {
        int[] nums = {2,0,2,1,1,0};
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}