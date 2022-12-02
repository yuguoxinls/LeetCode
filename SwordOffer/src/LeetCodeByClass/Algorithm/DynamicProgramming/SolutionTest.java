package LeetCodeByClass.Algorithm.DynamicProgramming;

import org.junit.Test;

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
    public void longestCommonSubsequence() {
        String text1 = "abc", text2 = "def";
        System.out.println(j.longestCommonSubsequence(text1, text2));
    }

    @Test
    public void canPartition() {
        int[] nums = {1,5,11,5};
        System.out.println(j.canPartition(nums));
    }

    @Test
    public void findMaxForm() {
        String[] strs = {"10","0001","111001","1","0"};
        System.out.println(j.findMaxForm(strs, 3, 4));
    }

    @Test
    public void coinChange() {
        int[] coins = {1,2147483647};
        System.out.println(j.coinChange(coins, 2));
    }
}