package LeetCodeByClass.Algorithm.Search;


import org.testng.annotations.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void numSquares() {
        System.out.println(solution.numSquares(13));
    }

    @Test
    public void generateSquares() {
        System.out.println(solution.generateSquares(9));
    }

    @Test
    public void numIslands() {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(solution.numIslands(grid));
    }

    @Test
    public void numIslandsV2() {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(solution.numIslands(grid));
    }

    @Test
    public void findCircleNum() {
        int[][] grid = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(solution.findCircleNum(grid));
    }

    @Test
    public void letterCombinations() {
        String s = "356";
        System.out.println(solution.letterCombinations(s));
    }

    @Test
    public void restoreIpAddresses() {
        String s = "25525511135";
        System.out.println(solution.restoreIpAddresses(s));
    }
}