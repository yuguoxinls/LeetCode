package LeetCodeByClass.Algorithm.Greedy;


import org.junit.Test;

public class SolutionTest {

    Solution solution = new Solution();
    @Test
    public void findContentChildren() {
        int[] g = {1,2,3}, s={3};
        System.out.println(solution.findContentChildren(g, s));
    }

    @Test
    public void reconstructQueue() {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        solution.reconstructQueue(people);
    }

    @Test
    public void maxProfit() {
        int[] p = {7,6,4,3,1};
        System.out.println(solution.maxProfit(p));
    }

    @Test
    public void canPlaceFlowers() {
        int[] p = {0,0,0,0,0,1,0,0};
        System.out.println(solution.canPlaceFlowers(p, 1));
    }

    @Test
    public void isSubsequence() {
        String s = "axc", t = "ahbgdc";
        System.out.println(solution.isSubsequence(s, t));
    }

    @Test
    public void checkPossibility() {
        int[] nums = {4,2,3};
        System.out.println(solution.checkPossibility(nums));
    }

    @Test
    public void maxSubArray() {
        int[] nums = {5,4,-1,7,8};
        System.out.println(solution.maxSubArray(nums));
    }

    @Test
    public void partitionLabels() {
        String S = "jababcbacadefegdehijhklij";
        System.out.println(solution.partitionLabels(S));
    }
}