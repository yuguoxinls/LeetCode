package SwordByClass.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 12. 矩阵中的路径
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     */
    int row, col;
    public boolean exist(char[][] board, String word) { // TODO: 2023/1/11 dfs+剪枝
        char[] words = word.toCharArray();
        row = board.length;
        col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == words[0]){
                    if (check(board, i, j, words, 0)) return true;
                }
            }
        }
        return false;
    }
    private boolean check(char[][] board, int i, int j, char[] words, int k) {
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != words[k]) {
            // 保证索引不越界，k指向words中的索引为k的字符，如果和当前矩阵中的字符不相等，也返回false
            return false;
        }
        if (k == words.length-1) {
            // k遍历到了words中的最后一个字符，且没在上一个if返回false，说明匹配成功
            return true;
        }
        board[i][j] = '#'; // 没在上边2个if返回，说明在中间的某一个时刻，用一个特殊字符标记当前位置被访问过
        // 上下左右四个方向分别遍历
        boolean res = check(board, i-1, j, words, k+1) || check(board, i+1, j, words, k+1) || check(board, i, j-1, words, k+1) || check(board, i, j+1, words, k+1);
        board[i][j] = words[k]; // 在返回结果之前，恢复当前位置，用于回溯
        return res;
    }

    /**
     * 13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
     * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 2：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     */
    int m, n, count = 0;
    boolean[][] visited;
    public int movingCount(int m, int n, int k) {
        if (k == 0) return 1;
        this.m = m;
        this.n = n;
        visited = new boolean[m][n];
        dfs(0,0, k);
        return count;
    }
    private void dfs(int i, int j, int k) {
        if (i >= m || j >= n || visited[i][j]) return;
        int sum = getSum(i)+getSum(j);
        if (sum > k) return;
        visited[i][j] = true;
        count++;
        // TODO: 2023/1/12 和一般的dfs不同的是，默认只会往 右 或者 下 走，可以减少2个dfs过程，但是不知道为什么
        dfs(i+1, j, k);
        dfs(i, j+1, k);
    }
    public int getSum(int num) {
        int sum = 0;
        while (num != 0){
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }

    /**
     * 38. 字符串的排列
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     * 示例:
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     */
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars); // TODO: 2023/1/12 元素进行排序，便于下边排除重复字符串
        List<String> ans = new ArrayList<>();
        boolean[] visited = new boolean[s.length()];
        StringBuilder prefix = new StringBuilder();
        backTracing(chars, ans, prefix, visited);
        String[] res = new String[ans.size()];
        return ans.toArray(res);
    }
    private void backTracing(char[] chars, List<String> ans, StringBuilder prefix, boolean[] visited) {
        if (prefix.length() == chars.length){
            ans.add(prefix.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {

            if (i>0 && chars[i] == chars[i-1] && !visited[i-1]) continue; // TODO: 2023/1/12 排除重复字符串

            if (visited[i]) continue;
            visited[i] = true;
            prefix.append(chars[i]);
            backTracing(chars, ans, prefix, visited);
            prefix.deleteCharAt(prefix.length()-1);
            visited[i] = false;
        }
    }
}
