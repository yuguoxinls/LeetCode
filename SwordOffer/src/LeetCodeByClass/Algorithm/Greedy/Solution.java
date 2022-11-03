package LeetCodeByClass.Algorithm.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 贪心思想：保证每次操作都是局部最优的，并且最后得到的结果是全局最优的。
 */

class Solution {

    /**
     * 1. 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
     * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * 示例 1:
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1
     * 解释:
     * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
     * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
     * 所以你应该输出1。
     * 示例 2:
     * 输入: g = [1,2], s = [1,2,3]
     * 输出: 2
     * 解释:
     * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
     * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
     * 所以你应该输出2.
     */
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        if (s[s.length-1] < g[0]) return 0;
        int ans = 0, i = 0, j = 0;
        while (i < g.length && j < s.length){
            while (j < s.length && s[j] < g[i]) j++;
            if (j != s.length) {
                ans++;
                i++;
                j++;
            }else return ans;
        }
        return ans;
    }

    /**
     * 2. 无重叠区间
     * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
     * 示例 1:
     * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * 示例 2:
     * 输入: intervals = [ [1,2], [1,2], [1,2] ]
     * 输出: 2
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     * 示例 3:
     * 输入: intervals = [ [1,2], [2,3] ]
     * 输出: 0
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     */
    public int eraseOverlapIntervals(int[][] intervals) { // TODO: 2022/11/3
        if (intervals.length == 0) {
            return 0;
        }

        /*Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];
            }
        });*/ // 按照区间右端点，由小到大排序，应该避免使用 return interval1[1] - interval2[1] 这种减法操作，防止溢出
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        int n = intervals.length; // 区间总个数
        int right = intervals[0][1];
        int ans = 1; // ans代表不重叠区间的个数，n - ans 就代表要删除区间的个数
        for (int i = 1; i < n; ++i) { // 遍历剩下的区间
            if (intervals[i][0] >= right) { // 如果当前区间的左端点 大于等于 上一满足条件区间的右端点，则不重叠
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }

    /**
     * 3. 用最少数量的箭引爆气球
     * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。
     * 墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
     * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。
     * 在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆 。
     * 可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
     * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
     * 示例 1：
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：气球可以用2支箭来爆破:
     * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
     * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
     * 示例 2：
     * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
     * 输出：4
     * 解释：每个气球需要射出一支箭，总共需要4支箭。
     * 示例 3：
     * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
     * 输出：2
     * 解释：气球可以用2支箭来爆破:
     * - 在x = 2处发射箭，击破气球[1,2]和[2,3]。
     * - 在x = 4处射出箭，击破气球[3,4]和[4,5]。
     */
    public int findMinArrowShots(int[][] points) {
        // TODO: 2022/11/3 本质还是求不重叠区间的个数，因为一旦重叠，所有重叠的区间可以等效为一个区间，一支箭就可删除
        if (points == null || points.length == 0) return 0;

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        int ans = 1, right = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > right){
                ans++;
                right = points[i][1];
            }
        }
        return ans;
    }

    /**
     * 4. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
     * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     * 请你重新构造并返回输入数组 people 所表示的队列。
     * 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     * 示例 1：
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     * 示例 2：
     * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
     * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
     */
    public int[][] reconstructQueue(int[][] people) { // TODO: 2022/11/3  矮个子排在哪都对高个子没有影响，但是高个子排在矮个子前面就会造成影响。所以，矮个子要主动选择位置
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0])); // 排序，身高是否相同？相同则按照人数升序排；不同按照身高降序排
        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p); // 在queue索引为p[1]的位置，添加元素p
        }
        return queue.toArray(new int[queue.size()][]);
    }

    /**
     * 5. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 示例 1：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public int maxProfit(int[] prices) {
        int cost = prices[0], maxProfit = 0;
        for (int price : prices) {
            if (price <= cost){
                // 这一天的价格比成本低，当然要最低的作为成本
                cost = price;
            }else {
                int curProfit = price - cost;
                maxProfit = Math.max(maxProfit, curProfit);
            }
        }
        return maxProfit;
    }

    /**
     * 6. 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7 。
     * 示例 2：
     * 输入：prices = [1,2,3,4,5]
     * 输出：4
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *      总利润为 4 。
     * 示例 3：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
     */
    public int maxProfitV2(int[] prices) {
        // TODO: 2022/11/3 贪心的：只要后一天比前一天价格高，我就卖出
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i-1] > 0) profit += prices[i] - prices[i-1];
        }
        return profit;
    }

    /**
     * 7. 种花问题
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
     * 另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
     * 示例 1：
     * 输入：flowerbed = [1,0,0,0,1], n = 1
     * 输出：true
     * 示例 2：
     * 输入：flowerbed = [1,0,0,0,1], n = 2
     * 输出：false
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) { // TODO: 2022/11/3
        if (n == 0) return true;
        int len = flowerbed.length;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre = i == 0 ? 0 : flowerbed[i - 1]; // 运行到这说明当前i指向的元素肯定是0，如果第一个元素的前面没有数，默认是零，也就是满足一半的条件
            int next = i == len - 1 ? 0 : flowerbed[i + 1]; // 同样，最后一个元素的后面没有数，也默认是0
            if (pre == 0 && next == 0) { // 如果当前元素的前后都是0，满足条件，可以种
                flowerbed[i] = 1;
                n--;
            }
            if (n == 0) break;
        }
        return n == 0;
    }
}