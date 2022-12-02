package LeetCodeByClass.Algorithm.Math;

public class Solution {
    /**
     * 使用数学
     */

    /**
     * 1. 计数质数
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     * 示例 1：
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     * 输入：n = 1
     * 输出：0
     */
    public int countPrimes(int n) {
        /*int count = 0;
        for (int i = 0; i < n; i++) {
            if (isZ(i)) count++;
        }
        return count;*/ // 枚举会超时
        // TODO: 2022/12/2 埃氏筛
        /**
         * 如果 x 是质数，则x的倍数 2x,3x,...一定不是质数
         * 设 notPrimes[i]表示数 i 是不是质数。
         * 从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记0，这样在运行结束的时候我们即能知道质数的个数。
         * 还可以继续优化，对于一个质数 x，如果按上文说的我们从 2x 开始标记其实是冗余的，应该直接从 x⋅x开始标记，
         * 因为 2x,3x,…这些数一定在 x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。
         */
        boolean[] notPrimes = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrimes[i]) { // 如果不是质数，就判断下一个数，而起始数是2，是质数，这个分支不会执行
                continue;
            }
            count++;
            // 从 i * i 开始，因为如果 k < i，那么 k * i 在之前就已经标记过了
            for (long j = (long) (i) * i; j < n; j += i) { // j+=i, 表示每次增加i的一倍，也就是在小于n的范围内对i的倍数进行标记
                notPrimes[(int) j] = true;
            }
        }
        return count;
    }
    public boolean isZ(int num) {
        if (num == 0 || num == 1) return false;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * 进制转换
     * 1. 七进制数
     * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
     * 示例 1:
     * 输入: num = 100
     * 输出: "202"
     * 示例 2:
     * 输入: num = -7
     * 输出: "-10"
     */
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean flag = false;
        if (num < 0) {
            flag = true;
            num = -num;
        }
        StringBuilder ans = new StringBuilder();
        while (num != 0){
            int yu = num % 7;
            num = num / 7;
            ans.append(yu);
        }
        if (flag) ans.append("-");
        return ans.reverse().toString();
        /*StringBuilder ans = new StringBuilder(); 可以套用26进制的那个，就是处理不了负数
        while (num != 0){
            int re = num % 7;
            num /= 7;
            ans.append((int)(re));
        }
        return ans.reverse().toString();*/
    }

    /**
     * 2. 数字转换为十六进制数
     * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
     * 注意:
     * 十六进制中所有字母(a-f)都必须是小写。
     * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
     * 给定的数确保在32位有符号整数范围内。
     * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
     * 示例 1：
     * 输入:
     * 26
     * 输出:
     * "1a"
     * 示例 2：
     * 输入:
     * -1
     * 输出:
     * "ffffffff"
     */
    public String toHex(int num) { // TODO: 2022/12/2
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 0b1111]); //0b表示的是二进制数，将一个数和1111与会得到这个数的最后四位，进制转换 源进制 -> 2进制 -> 目标进制，16进制是4位2进制一组
            num >>>= 4; // 因为考虑的是补码形式，因此符号位就不能有特殊的意义，需要使用无符号右移，左边填 0
        }
        return sb.reverse().toString();
    }

    /**
     * 3. Excel表列名称
     * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
     * 例如：
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     * ...
     * 示例 1：
     * 输入：columnNumber = 1
     * 输出："A"
     * 示例 2：
     * 输入：columnNumber = 28
     * 输出："AB"
     * 示例 3：
     * 输入：columnNumber = 701
     * 输出："ZY"
     * 示例 4：
     * 输入：columnNumber = 2147483647
     * 输出："FXSHRXW"
     */
    public String convertToTitle(int columnNumber) {
        /*string ans;
        while(columnNumber) {
            --columnNumber;
            int re = columnNumber % 26;
            ans.push_back('A' + re);
            columnNumber /= 26;
        }
        reverse(ans.begin(), ans.end());
        return ans;*/
        // TODO: 2022/12/2 变体的26进制
        /**
         * 和正常的26进制不同，正常的是0-->A, 1-->B,...,25-->Z
         * 本体在以上基础上加了1，因此在处理每一位的时候，要减 1
         */
        StringBuilder ans = new StringBuilder();
        while (columnNumber>0){
            columnNumber--;
            int re = columnNumber % 26;
            ans.append((char)('A' + re));
            columnNumber /= 26;
        }
        return ans.reverse().toString();
    }

}
