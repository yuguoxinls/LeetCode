package SwordByClass.DivideAndConquer;

public class Solution {
    /**
     * 16. 数值的整数次方
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题。
     * 示例 1：
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     * 示例 2：
     * 输入：x = 2.10000, n = 3
     * 输出：9.26100
     * 示例 3：
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     */
    public double myPow(double x, int n) {
        // TODO: 2023/1/11
        if(x == 0) return 0;
        long b = n; // 防止int出问题，转成double
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if(b % 2 == 1) res *= x; // 当指数是奇数的时候，提前乘一个x
            x *= x; // 一次增长2个x
            b /= 2; // 当然这里就要除2
        }
        return res;
    }
}
