package SwordByClass.Search;

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
}
