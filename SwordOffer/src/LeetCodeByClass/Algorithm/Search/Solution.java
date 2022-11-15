package LeetCodeByClass.Algorithm.Search;


import org.testng.internal.collections.Pair;

import java.util.*;

class Solution {
    /**
     * 深度优先搜索DFS和广度优先搜索BFS广泛运用于树和图中
     * 在程序实现 BFS 时需要考虑以下问题：
     *  队列：用来存储每一轮遍历得到的节点；
     *  标记：对于遍历过的节点，应该将它标记，防止重复遍历。
     * 在程序实现 DFS 时需要考虑以下问题：
     *  栈：用栈来保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点。可以使用递归栈。
     *  标记：和 BFS 一样同样需要对已经遍历过的节点进行标记。
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

    /**
     * 5. 课程表
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * 示例 2：
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) { // TODO: 2022/11/9 不会！！！
        /**
         * 分析本质可见，实际上就是判断有向图中是否有环
         */
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // Get the indegree and adjacency of every course.
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // Get all the courses with the indegree of 0.
        for(int i = 0; i < numCourses; i++)
            if(indegrees[i] == 0) queue.add(i);
        // BFS TopSort.
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre))
                if(--indegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;
    }

    /**
     * 从一个节点出发，使用 DFS 对一个图进行遍历时，能够遍历到的节点都是从初始节点可达的，DFS 常用来求解这种 可达性 问题。
     * 在程序实现 DFS 时需要考虑以下问题：
     *  栈：用栈来保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点。可以使用递归栈。
     *  标记：和 BFS 一样同样需要对已经遍历过的节点进行标记。
     */

    /**   DFS
     * 1. 岛屿的最大面积
     * 给你一个大小为 m x n 的二进制矩阵 grid 。
     * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *              [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *              [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *              [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *              [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *              [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * 输出：6
     * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
     * 示例 2：
     * 输入：grid = [[0,0,0,0,0,0,0,0]]
     * 输出：0
     */
    private int m, n;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int maxAreaOfIsland(int[][] grid) { // TODO: 2022/11/10
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }
    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0; // 说明此时的grid[r][c]==1，标记为0表示已经访问过
        int area = 1; // 定义初始的岛屿面积为1
        for (int[] d : direction) {
            area += dfs(grid, r + d[0], c + d[1]); // 其实就是递归遍历上下左右4个方向，和BFS的 4. 岛屿数量是相同的，只不过4是分开写的
        }
        return area;
    }

    /**
     * 2. 同 BFS 4. 岛屿数量，只不过是DFS解法
     */
    /*public int numIslandsV2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int numLands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(grid, i, j);
                numLands++;
            }
        }
        return numLands;
    }
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] d : direction) {
            dfs(grid, i + d[0], j + d[1]);
        }
    }*/ // 自己测试没有问题，力扣提交会报错，不知道为什么
    public int numIslandsV2(char[][] grid) { // TODO: 2022/11/11
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    /**
     * 3. 省份数量
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     * 输入：isConnected = [[1,1,0],
     *                     [1,1,0],
     *                     [0,0,1]]
     * 输出：2
     * 示例 2：
     * 输入：isConnected = [[1,0,0],
     *                     [0,1,0],
     *                     [0,0,1]]
     * 输出：3
     */
    public int findCircleNum(int[][] isConnected) { // TODO: 2022/11/11
        // int[][] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
        int n = isConnected.length;
        // 定义 boolean 数组标识顶点是否被访问
        boolean[] visited = new boolean[n];
        // 定义 cnt 来累计遍历过的连通域的数量
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // 若当前顶点 i 未被访问，说明又是一个新的连通域，则遍历新的连通域且cnt+=1.
            if (!visited[i]) {
                cnt++;
                dfs(i, isConnected, visited);
            }
        }
        return cnt;
    }
    private void dfs(int i, int[][] isConnected, boolean[] visited) {
        // 对当前顶点 i 进行访问标记
        visited[i] = true;

        // 继续遍历与顶点 i 相邻的顶点（使用 visited 数组防止重复访问）
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                dfs(j, isConnected, visited);
            }
        }
    }

    /**
     * 4. 被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * 示例 1：
     * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
     * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     * 示例 2：
     * 输入：board = [["X"]]
     * 输出：[["X"]]
     */
    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) { // 以最左列和最右列的O字符为起点，深度优先遍历，把和这两个边界相连的O都找到，并修改为A
            dfsV3(board, i, 0);
            dfsV3(board, i, col-1);
        }
        for (int i = 1; i < col; i++) { // 以最左上列和最下列的O字符为起点，深度优先遍历，把和这两个边界相连的O都找到，并修改为A
            dfsV3(board, 0, i);
            dfsV3(board, row-1, i);
        }
        // 这两个循环后，凡是和边界上的O相连的O，已经都变成了A，这样后面就不会错误修改了，题目中要求和边界O相连的O，不要修改为X
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void dfsV3(char[][] board, int i, int j) { // dfs 如果遍历到O，就改为A
        int row = board.length;
        int col = board[0].length;
        if (i >= row || i < 0 || j >= col || j < 0 || board[i][j] != 'O') return;
        board[i][j] = 'A';
        dfsV3(board, i, j+1);
        dfsV3(board, i, j-1);
        dfsV3(board, i+1, j);
        dfsV3(board, i-1, j);
    }

    /**
     * 5. 太平洋大西洋水流问题
     * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
     * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
     * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
     * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
     * 示例 1：
     * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
     * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
     * 示例 2：
     * 输入: heights = [[2,1],[1,2]]
     * 输出: [[0,0],[0,1],[1,0],[1,1]]
     */
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
//    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        // 分别以四个边界为起点，进行dfs
        for (int i = 0; i < m; i++) { // 以最左列的每个元素为起点，进行dfs
            dfs(i, 0, pacific);
        }
        for (int j = 1; j < n; j++) { // 以最上列的每个元素为起点，进行dfs
            dfs(0, j, pacific);
        }
        for (int i = 0; i < m; i++) { // 以最右列的每个元素为起点，进行dfs
            dfs(i, n - 1, atlantic);
        }
        for (int j = 0; j < n - 1; j++) { // 以最下列的每个元素为起点，进行dfs
            dfs(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) { // 题目要求，既可流向太平洋也可流向大西洋，因此用的是 &&
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }
    public void dfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) { // 已经访问过的话，直接返回
            return;
        }
        ocean[row][col] = true; // 标记为已访问过
        for (int[] dir : dirs) { // 向4个方向走
            int newRow = row + dir[0], newCol = col + dir[1]; // 走完之后的坐标
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && heights[newRow][newCol] >= heights[row][col]) { // 如果高度更高的话，才会继续往下走
                dfs(newRow, newCol, ocean);
            }
        }
    }

    /**
     * 回溯问题 Backtracking
     * Backtracking（回溯）属于 DFS。
     *  普通 DFS 主要用在 可达性问题 ，这种问题只需要执行到特点的位置然后返回即可。
     *  而 Backtracking 主要用于求解 排列组合 问题，例如有 { 'a','b','c' } 三个字符，求解所有由这三个字符排列得到的字符串，这种问题在执行到特定的位置返回之后还会继续执行求解过程。
     * 因为 Backtracking 不是立即返回，而要继续求解，因此在程序实现时，需要注意对元素的标记问题：
     *  在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；
     *  但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问 已经访问过但是不在当前递归链中 的元素。
     */

    /**
     * 1. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（9键）。注意 1 不对应任何字母。
     * 示例 1：
     * 输入：digits = "23"   2->abc   3->def
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     */
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) { // TODO: 2022/11/11
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        doCombination(new StringBuilder(), combinations, digits);
        return combinations;
    }
    private void doCombination(StringBuilder prefix, List<String> combinations, final String digits) {
        if (prefix.length() == digits.length()) { // 这一句的意思是代表完成了一次组合，比如说digits=“345”，那么字母组合的长度应该和digits的长度是一样的，可能是“dhk”
            combinations.add(prefix.toString());
            return;
        }
        int curDigits = digits.charAt(prefix.length()) - '0'; // 得到当前的数字，注意，把字符串转成了数字
        String letters = KEYS[curDigits]; // 得到当前数字对应的字母是哪几个
        for (char c : letters.toCharArray()) {
            prefix.append(c);                         // 添加
            doCombination(prefix, combinations, digits);
            prefix.deleteCharAt(prefix.length() - 1); // 执行到这里，说明完成了一次组合，但是要注意的是，完成之后要删除一个，来进行下一次的组合
        }
    }

    /**
     * 2. 复原 IP 地址
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     * 示例 1：
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     * 示例 2：
     * 输入：s = "0000"
     * 输出：["0.0.0.0"]
     * 示例 3：
     * 输入：s = "101023"
     * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     */
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) { // TODO: 2022/11/11 是真看不懂啊。。。
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    /**
     * 3. 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * 示例 1：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     * 示例 2：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
     * 输出：true
     */
    int[][] direc = {{-1,0},{1,0},{0,-1},{0,1}};
    public boolean exist(char[][] board, String word) { // TODO: 2022/11/14
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col]; // 不单单是用来标记是否访问过，更重要的是能用做回溯

        for (int i = 0; i <row; i++) {
            for (int j = 0; j < col; j++) {
                if (Backtracking(0, visited, board, i, j, word)) return true;
            }
        }
        return false;
    }
    private boolean Backtracking(int curLen, boolean[][] visited, char[][] board, int i, int j, String word) {
        if (curLen == word.length()) return true; // 如果已经比对到了最后一个字符，那么说明网格中存在该单词
        int row = board.length, col = board[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] || word.charAt(curLen) != board[i][j]) return false; // 索引越界或已经访问过并能走通或当前字符和单词中的字符不相等
        visited[i][j] = true; // 说明网格中的当前字符和单词中的当前字符相等，将其标记为已访问过
        for (int[] d : direc) {
            if (Backtracking(curLen+1, visited, board, i + d[0], j+d[1], word)) return true; // 向上下左右四个方向回溯
        }
        visited[i][j] = false; // 能运行到这，说明在前面的向上下左右的四个方向，都找不到符合要求的字符，说明当前这个字符是死路，将其重新标记为false，这也是回溯不同于dfs的地方
        return false;
    }

    /**
     * 4. 二叉树的所有路径
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点。
     * 示例 1：
     * 输入：root = [1,2,3,null,5]
     * 输出：["1->2->5","1->3"]
     * 示例 2：
     * 输入：root = [1]
     * 输出：["1"]
     */
    public List<String> binaryTreePaths(TreeNode root) { // TODO: 2022/11/14
        List<String> ans = new ArrayList<>();
        if (root == null) return ans;
        List<Integer> values = new ArrayList<>();
        Backtracking(root, ans, values);
        return ans;
    }

    private void Backtracking(TreeNode node, List<String> ans, List<Integer> values) {
        if (node == null) return;
        values.add(node.val); // 把当前节点的值放到values中
        if (isLeaf(node)) { // 如果当前节点是叶子节点，说明此条路已经走到底了，将其拼接好放到ans中
            ans.add(buildPath(values));
        }else { // 不是叶子节点，就向当前节点的两侧递归
            Backtracking(node.left, ans, values);
            Backtracking(node.right, ans, values);
        }
        values.remove(values.size()-1); // !!!!!!! 非常重要，体现了回溯，在遍历到叶子节点并把其添加到路径后，要删除其值，下一次拼接的时候，从上一个节点开始
    }

    private String buildPath(List<Integer> values) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            str.append(values.get(i));
            if (i != values.size() - 1) str.append("->");
        }
        return str.toString();
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    /**
     * 5. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     * 示例 3：
     * 输入：nums = [1]
     * 输出：[[1]]
     */
    public List<List<Integer>> permute(int[] nums) { // TODO: 2022/11/14
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subAns = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backTracking(ans, subAns, visited, nums);
        return ans;
    }
    private void backTracking(List<List<Integer>> ans, List<Integer> subAns, boolean[] visited, int[] nums) {
        if (subAns.size() == nums.length) { // 终止条件
//            ans.add(subAns);
            ans.add(new ArrayList<>(subAns)); // 一定要这样，新建一个List，不能像上面那样，如果像上面那样，下面对subAns的修改会影响到ans中之前保存的值
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            subAns.add(nums[i]);
            backTracking(ans, subAns, visited, nums);
            subAns.remove(subAns.size()-1); // 回溯时要删除最后一个元素，并将其修改为未被访问过
            visited[i] = false;
        }
    }

    /**
     * 6. 全排列 II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * 示例 2：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subAns = new ArrayList<>();
        Arrays.sort(nums); // TODO: 2022/11/15 对数组先排序，这样相同元素就挨在一起了，在后面排列的时候，判断该元素是否和之前的元素相同，如果相同且之前的元素还未被访问过，就跳过该元素
        boolean[] visited = new boolean[nums.length];
        backTrackingV2(ans, subAns, visited, nums);
        return ans;
    }
    private void backTrackingV2(List<List<Integer>> ans, List<Integer> subAns, boolean[] visited, int[] nums) { 
        if (subAns.size() == nums.length){
            ans.add(new ArrayList<>(subAns));
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            
            if (visited[i]) continue;
            visited[i] = true;
            subAns.add(nums[i]);
            backTrackingV2(ans, subAns, visited, nums);
            subAns.remove(subAns.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 7. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * 示例 2：
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subAns = new ArrayList<>();
//        boolean[] visited = new boolean[n+1];
        backTrackingV3(ans, subAns,1, k, n);
        return ans;
    }

    private void backTrackingV3(List<List<Integer>> ans, List<Integer> subAns, int start, int k, int n) {
        /*if (subAns.size() == k){
            ans.add(new ArrayList<>(subAns));
            return;
        }
        for (int i = start; i <= n; i++) {
            subAns.add(i);
            backTrackingV3(ans, subAns,i + 1, k, n);
            subAns.remove(subAns.size() - 1);
        }*/ // 比较通用的回溯模板，但是有时比较慢
        if (k == 0) {
            ans.add(new ArrayList<>(subAns));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {  // 剪枝 // TODO: 2022/11/15 剪枝什么意思
            subAns.add(i);
            backTrackingV3(ans, subAns, i + 1, k - 1, n);
            subAns.remove(subAns.size() - 1);
        }
    }

    /**
     * 8. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     * 示例 2：
     * 输入: candidates = [2,3,5], target = 8
     * 输出: [[2,2,2,2],[2,3,3],[3,5]]
     * 示例 3：
     * 输入: candidates = [2], target = 1
     * 输出: []
     */
    /*public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subAns = new ArrayList<>();
        backTrackingV4(ans, subAns, 0, candidates, target);
        return ans;
    }
    private void backTrackingV4(List<List<Integer>> ans, List<Integer> subAns, int start, int[] candidates, int target) {
        if (sum(subAns) == target){
            ans.add(new ArrayList<>(subAns));
            return;
        }
        if (sum(subAns) > target) return;
        for (int i = start; i < candidates.length; i++) {
            subAns.add(candidates[i]);
            backTrackingV4(ans, subAns, i, candidates, target);
            subAns.remove(subAns.size() - 1);
        }
    }
    private int sum(List<Integer> subAns) {
        int sum = 0;
        for (Integer sub : subAns) {
            sum += sub;
        }
        return sum;
    }*/ // TODO: 2022/11/15 自己写的，能过，太慢
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(new ArrayList<>(), ans, 0, target, candidates);
        return ans;
    }
    private void backtracking(List<Integer> subAns, List<List<Integer>> ans, int start, int target, final int[] candidates) {
        if (target == 0) {
            ans.add(new ArrayList<>(subAns));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                subAns.add(candidates[i]);
                backtracking(subAns, ans, i, target - candidates[i], candidates); // TODO: 2022/11/15 关键在于这，和上一题类似，好像也在做剪枝
                subAns.remove(subAns.size() - 1);
            }
        }
    }

    /**
     * 9. 组合总和 II
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * 注意：解集不能包含重复的组合。
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出:
     * [
     * [1,2,2],
     * [5]
     * ]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subAns = new ArrayList<>();
        boolean[] visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        backTrackingV5(ans, subAns, candidates, 0, target, visited);
        return ans;
    }

    private void backTrackingV5(List<List<Integer>> ans, List<Integer> subAns, int[] candidates, int start, int target, boolean[] visited) {
        if (target == 0){
            ans.add(new ArrayList<>(subAns));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i-1] && !visited[i-1]) continue; // TODO: 2022/11/15 去除最后结果中的重复结果
            if (candidates[i] <= target){
                subAns.add(candidates[i]);
                visited[i] = true;
                backTrackingV5(ans, subAns, candidates, i+1, target - candidates[i], visited);
                subAns.remove(subAns.size() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 10. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * 只使用数字1到9
     * 每个数字 最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     * 示例 1:
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     * 示例 2:
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 解释:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * 没有其他符合的组合了。
     * 示例 3:
     * 输入: k = 4, n = 1
     * 输出: []
     * 解释: 不存在有效的组合。
     * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subAns = new ArrayList<>();
        backTrackingV6(ans, subAns, k, n, 1);
        return ans;
    }

    private void backTrackingV6(List<List<Integer>> ans, List<Integer> subAns, int k, int target, int start) {
        if (target == 0 && subAns.size() == k){
            ans.add(new ArrayList<>(subAns));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i <= target){
                subAns.add(i);
                backTrackingV6(ans, subAns, k, target - i, i + 1);
                subAns.remove(subAns.size() - 1);
            }
        }
    }


}