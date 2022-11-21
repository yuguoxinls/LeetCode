package LeetCodeByClass.Algorithm.DynamicProgramming;

import org.testng.annotations.Test;

public class SolutionTest {
    Solution j = new Solution();

    @Test
    public void minPathSum() {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(j.minPathSum(grid));
    }

    @Test
    public void uniquePaths() {
        System.out.println(j.uniquePaths(3, 2));
    }

    @Test
    public void integerBreak() {
        System.out.println(j.integerBreak(10));
    }

    @Test
    public void numDecodings() {
        String s = "2206";
        System.out.println(j.numDecodings(s));
    }

    @Test
    public void lengthOfLIS() {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(j.lengthOfLIS(nums));
    }

    @Test
    public void findLongestChain() {
        int[][] pairs = {{3,4},{1,2},{2,3}};
        System.out.println(j.findLongestChain(pairs));
    }

    @Test
    public void wiggleMaxLength() {
        int[] nums = {1,7,4,9,2,5};
        System.out.println(j.wiggleMaxLength(nums));
    }
}