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
}