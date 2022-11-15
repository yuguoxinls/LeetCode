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

    @Test
    public void exist() {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(solution.exist(board, word));
    }

    @Test
    public void permute() {
        int[] nums = {1,2,3};
        System.out.println(solution.permute(nums));
    }

    @Test
    public void permuteUnique() {
        int[] nums = {1,1,3};
        System.out.println(solution.permuteUnique(nums));
    }

    @Test
    public void combine() {
        System.out.println(solution.combine(4, 3));
    }

    @Test
    public void combinationSum() {
        int[] nums = {7,6,2,3};
        System.out.println(solution.combinationSum(nums, 7));
    }

    @Test
    public void combinationSum2() {
        int[] nums = {10,1,2,7,6,1,5};
        System.out.println(solution.combinationSum2(nums, 8));
    }

    @Test
    public void combinationSum3() {
        System.out.println(solution.combinationSum3(4, 1));
    }
}