package SwordByClass.Others;

public class Solution {
    /**
     * 17. 打印从1到最大的n位数
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     * 示例 1:
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     */
    public int[] printNumbers(int n) {
        int len = (int) Math.pow(10, n);
        int[] res = new int[len-1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i+1;
        }
        return res;
        // TODO: 2023/1/16 本题的考点应该在n很大时，int会越界，也就是大数问题
    }
}
