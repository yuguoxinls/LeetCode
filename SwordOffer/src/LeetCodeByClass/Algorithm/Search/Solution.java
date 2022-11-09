package LeetCodeByClass.Algorithm.Search;


import org.testng.internal.collections.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    /**
     * 深度优先搜索DFS和广度优先搜索BFS广泛运用于树和图中
     * 在程序实现 BFS 时需要考虑以下问题：
     *  队列：用来存储每一轮遍历得到的节点；
     *  标记：对于遍历过的节点，应该将它标记，防止重复遍历。
     */

    /**        BFS
     * 1. 二进制矩阵中的最短路径
     * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
     * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
     *  路径途经的所有单元格都的值都是 0 。
     *  路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
     * 畅通路径的长度 是该路径途经的单元格总数。
     * 示例 1：
     * 输入：grid = [[0,1],[1,0]]
     * 输出：2
     * 示例 2：
     * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
     * 输出：4
     * 示例 3：
     * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
     * 输出：-1
     */
    /*public int shortestPathBinaryMatrix(int[][] grid) {  // TODO: 2022/11/8 看不懂
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int m = grid.length, n = grid[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                Pair<Integer, Integer> cur = queue.poll();
                assert cur != null;
                int cr = cur.first(), cc = cur.second();
                if (grid[cr][cc] == 1) {
                    continue;
                }
                if (cr == m - 1 && cc == n - 1) {
                    return pathLength;
                }
                grid[cr][cc] = 1; // 标记
                for (int[] d : direction) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    queue.add(new Pair<>(nr, nc));
                }
            }
        }
        return -1;
    }*/
    private static int[][] directions = {{0,1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private int row, col;
    public int shortestPathBinaryMatrix(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        if(grid[0][0] == 1 || grid[row - 1][col - 1] == 1) return -1;
        Queue<int[]> pos = new LinkedList<>();
        grid[0][0] = 1; // 直接用grid[i][j]记录从起点到这个点的最短路径长。按照题意 起点也有长度1
        pos.add(new int[]{0,0});
        while(!pos.isEmpty() && grid[row - 1][col - 1] == 0){ // 求最短路径 使用BFS
            int[] xy = pos.remove();
            int preLength = grid[xy[0]][xy[1]]; // 当前点的路径长度
            for(int i = 0; i < 8; i++){
                int newX = xy[0] + directions[i][0];
                int newY = xy[1] + directions[i][1];
                if(inGrid(newX, newY) && grid[newX][newY] == 0){
                    pos.add(new int[]{newX, newY});
                    grid[newX][newY] = preLength + 1; // 下一个点的路径长度要+1
                }
            }
        }
        return grid[row - 1][col - 1] == 0 ? -1 : grid[row - 1][col - 1]; // 如果最后终点的值还是0，说明没有到达
    }
    private boolean inGrid(int x, int y){
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    /**
     * 2. 完全平方数
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * 完全平方数是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和16都是完全平方数，而 3和 11不是。
     * 示例 1：
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * 示例 2：
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     */
    public int numSquares(int n) { // TODO: 2022/11/9
        /**
         * 可以将每个整数看成图中的一个节点，如果两个整数之差为一个平方数，那么这两个整数所在的节点就有一条边。
         * 要求解最小的平方数数量，就是求解从节点 n 到节点 0 的最短路径。
         *
         * 上面这段话可以这么理解，给 n 每次减一个完全平方数，直到减为0，这时候的层数也就是减了几个完全平方数
         * 要求最少数量，那就是BFS到浅的层数，就是答案
         * 例如，输入的是7，比7小的完全平方数有1，4，因此构造如下的树，7=4+1+1+1，因此输出4，从节点0->节点7
         *          7
         *         / \
         *        6   3
         *      / \    \
         *     5   2    2
         *    / \   \    \
         *  4    1   1    1
         * / \   \   \    \
         * 3  0  0   0     0
         * \
         * 2
         * \
         * 1
         * \
         * 0
         */
        List<Integer> squares = generateSquares(n); // squares里存了小于等于n的所有平方数
        Queue<Integer> queue = new LinkedList<>(); // queue BFS用到的队列
        boolean[] marked = new boolean[n + 1]; // marked用来标记当前节点是否已经遍历过
        queue.add(n);
        marked[n] = true;
        int level = 0; // 为了最后返回level，这里认为第一层是0；也可以level=1，最后返回的时候要返回level-1
        while (!queue.isEmpty()) { // 标准的BFS过程
            int size = queue.size(); // 获得当前层的节点数目
            level++; // level++表示要弄下一层了todo
            while (size-- > 0) { // 循环操作当前层的每一个节点
                int cur = queue.poll(); // 取出当前要操作的节点
                for (int s : squares) { // 针对所有完全平方数
                    int next = cur - s; // 用当前的数和完全平方数做差，把他当作节点
                    if (next < 0) { // 由于完全平方数是按升序排序，如果一旦出现小于零，后边的不用看了，肯定也小于零
                        break;
                    }
                    if (next == 0) { // 如果等于0，说明已经到了我们要找的层，返回此时的层数
                        return level;
                    }
                    if (marked[next]) { // 能到这里，说明next>0，如果该节点已经被遍历过，进行下一次循环
                        continue;
                    }
                    marked[next] = true; // 能到这里，说明next>0且该节点未被遍历过，将其标志设为true
                    queue.add(next); // 添加到队列中，准备下一层的遍历
                }
            }
        }
        return n;
    }
    /**
     * 生成小于等于 n 的平方数序列
     * @return 1,4,9,...
     */
    public List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1, num = 1;
        while (square <= n) {
            squares.add(square);
            num++;
            square = num * num;
        }
        return squares;
    }

    /**
     * 3. 二叉树的层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     * 示例 2：
     * 输入：root = [1]
     * 输出：[[1]]
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                tmp.add(treeNode.val);
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            ans.add(tmp);
        }
        return ans;
    }

    /**
     * 4. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * 示例 1：
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 示例 2：
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     */
    public int numIslands(char[][] grid) { // TODO: 2022/11/9 看不懂 麻了
        /**
         * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。
         * 在广度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。直到队列为空，搜索结束。
         * 最终岛屿的数量就是我们进行广度优先搜索的次数。
         */
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRow = grid.length;
        int numCol = grid[0].length;
        int numLands = 0;

        for (int r = 0; r < numRow; ++r) {
            for (int c = 0; c < numCol; ++c) { // 遍历网格中的每个元素
                if (grid[r][c] == '1') { // 如果当前元素是1
                    ++numLands; // 岛屿数加1
                    grid[r][c] = '0'; // 并将当前元素置为0，表示已经访问过
                    Queue<Integer> queue = new LinkedList<>(); // 使用队列进行BFS
                    queue.add(r * numCol + c); // r * numCol + c 应该是grid转为一维数组后的索引
                    while (!queue.isEmpty()) { // 当队列不为空
                        int id = queue.remove(); // 取出队列第一个元素
                        int row = id / numCol; // 可得到当前元素在二维数组中的行索引
                        int col = id % numCol; // 可得到当前元素在二维数组中的列索引
                        if (row - 1 >= 0 && grid[row-1][col] == '1') { // 判断当前元素上方是否为1
                            queue.add((row-1) * numCol + col); // 是的话就加入队列
                            grid[row-1][col] = '0'; // 同时标记为0，表示已访问过
                        }
                        if (row + 1 < numRow && grid[row+1][col] == '1') { // 同理，下
                            queue.add((row+1) * numCol + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') { // 同理，左
                            queue.add(row * numCol + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < numCol && grid[row][col+1] == '1') { // 同理，右
                            queue.add(row * numCol + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return numLands;
    }
}