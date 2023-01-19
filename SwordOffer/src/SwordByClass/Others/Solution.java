package SwordByClass.Others;

import java.util.HashSet;
import java.util.Set;

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

    /**
     * 20. 表示数值的字符串
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 数值（按顺序）可以分成以下几个部分：
     * 若干空格
     * 一个 小数 或者 整数
     * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     * 若干空格
     * 小数（按顺序）可以分成以下几个部分：
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * 部分数值列举如下：
     * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
     * 部分非数值列举如下：
     * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
     * 示例 1：
     * 输入：s = "0"
     * 输出：true
     * 示例 2：
     * 输入：s = "e"
     * 输出：false
     * 示例 3：
     * 输入：s = "."
     * 输出：false
     * 示例 4：
     * 输入：s = "    .1  "
     * 输出：true
     */
    public boolean isNumber(String s) {
        // TODO: 2023/1/18 官方和k神写的自动状态机，看不懂，下面是其他人的一个题解，也100%
        if (s == null || s.length() == 0) return false;
        //去掉首位空格
        s = s.trim();
        //是否出现数字
        boolean numFlag = false;
        //是否出现小数点
        boolean dotFlag = false;
        //是否出现e/E
        boolean eFlag = false;
        //是否出现+/-
        boolean signFlag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') { // 当前字符是数字，则将numFlag置为true
                numFlag = true;
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                // 如果当前字符是小数点，那么需要之前没有出现过小数点 并且 也没有出现过e也就是指数，因为指数后面是不许出现小数的
                dotFlag = true;
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !eFlag && numFlag) {
                // 如果当前字符是指数，要求之前没有出现过指数字符 并且 前面要出现过数字
                eFlag = true;
                // 这里要注意，因为要求e的前后都要出现数字才行，运行到这里表示之前出现过数字，也就是numFlag为true
                // 但是要注意修改其为false，由于判断在e之后有没有出现过数字，避免e以后没有出现数字
                numFlag = false;
                //判定为+-符号，只能出现在第一位或者紧接e后面
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
                //如果当前字符为+或-，它只可以出现在第一位 或者 e的后面
                signFlag = true;
            } else {
                //其他情况，都是非法的
                return false;
            }
        }
        //是否出现了数字
        return numFlag;
    }

    /**
     * 44. 数字序列中某一位的数字
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
     * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     * 请写一个函数，求任意第n位对应的数字。
     * 示例 1：
     * 输入：n = 3
     * 输出：3
     * 示例 2：
     * 输入：n = 11
     * 输出：0
     */
    public int findNthDigit(int n) {
        // TODO: 2023/1/18 宫水三叶
        // 对于 len 位数来说，其取值范围[10^(len-1), 10^(len)-1]，共有 10^(len)-1 - 10^(len-1) = 9*10^(len-1)个数
        // 所有的 len 位数会贡献出 len * [9*10^(len-1)] 个数字
        // 因此，可以不断对 n 进行试减来判断他是属于几位数
        int len = 1; // 从 1 位数开始试减
        while (getCount(len) <= n){
            n -= getCount(len);
            len++;
        }
        // 此时会停在 len 位数，它们以 10^(len-1) 开始，记为 start
        long start = (long) Math.pow(10, len-1);
        // 可以通过计算偏移量确定到底是哪个数字
        start += n/len;
        // 同时需要对n进行更新
        n -= n/len*len;
        // 如果 n == 0，说明答案为 start-1 这个数字的最后一位
        if (n == 0) return (int) (start - 1) % 10;
        else {
            // n != 0，说明答案为start开始的这个数字从左到右数第n位，可以采用将其转为字符串通过索引取得
            return String.valueOf(start).toCharArray()[n-1]-'0';
        }
    }
    private long getCount(int len) { // 该函数返回 len 位数会贡献出多少个数字
        return (long) (len * (9 * Math.pow(10, len-1)));
    }

    /**
     * 65. 不用加减乘除做加法
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     * 示例:
     * 输入: a = 1, b = 1
     * 输出: 2
     */
    public int add(int a, int b) {
        if (b == 0) return a;
        int sum = a ^ b, c = a & b;
        // TODO: 2023/1/19 注意要把进位左移一位，为非进位和留出空间
        return add(sum, c << 1);
    }

    /**
     * 61. 扑克牌中的顺子
     * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     * 示例 1:
     * 输入: [1,2,3,4,5]
     * 输出: True
     * 示例 2:
     * 输入: [0,0,1,2,5]
     * 输出: True
     */
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int minVal = 14, maxVal = -1;
        for (int num : nums) {
            if (set.contains(num)) return false;
            if (num == 0) continue;
            set.add(num);
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        return maxVal - minVal <= 4;
    }

    /**
     * 46. 把数字翻译成字符串
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
     * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     * 示例 1:
     * 输入: 12258
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     */
    public int translateNum(int num) {
        // TODO: 2023/1/19 这种题容易混淆该用dp还是回溯，一般题目问的是 多少种 方法，考虑用dp；而要是让把所有的方法列出来，就要考虑回溯了
        // dp[i]表示以第 i 个数字结尾的整数可能有dp[i]中翻译方法，dp[len]即是答案
        // 对于每一个数字，要么自己单独翻译；要么加上前面一个数字组成两位数一起翻译
        // 如果要和前面的数字一起翻译的话，需要有一些限制
        //  1 如果前面的数字是 0，那么是不能组成两位数的，也就不能一起翻译
        //  2 。。。。。。。。。1，后面这个数字没什么限制，可以组在一起翻译
        //  3 。。。。。。。。。2，后面这个数字不能大于 5，否则无法一起翻译
        //  4 。。。。。。。。。3 4 。。。，后面这个数字就不能和它组成两位数进行翻译
        // 综上，当前面的数字和当前的数字组成的数在 [10,25] 可以一起翻译，否则只能自己翻译
        // 仔细想一下，就是 有条件的青蛙跳台阶 问题，有的时候只能跳一节台阶
        // 当前数字能和前面一起绑定翻译时，dp[i] = dp[i-1]+dp[i-2] 表示可以自己翻，也可以绑定翻
        // 。。。。不能。。。。。。。。。，dp[i] = dp[i-1] 表示只能自己翻
        // 初始状态：dp[0] = 1 表示没有数字时的翻译数量；dp[1]=1 表示只有一位数字时的翻译数量
        /*String numStr = String.valueOf(num);
        char[] chars = numStr.toCharArray();
        int len = chars.length;
        if (len == 1) return 1;
//        int[] dp = new int[len];
//        dp[0] = 1;
        int a = 1, b, c = 0;
        String tmp = ""+chars[0]+chars[1];
        if (tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0) b = 2;
        else b = 1;

        for (int i = 2; i < len; i++) {
            tmp = ""+chars[i-1]+chars[i];
            if (tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0) {
                c = a+b;
            }
            else {
                c = b;
            }
            a = b;
            b = c;
        }

        return b;*/ // 思路是对的，但是写出来的代码很慢
        // TODO: 2023/1/19 k神
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for(int i = 2; i <= s.length(); i++) { // 这里的dp下标是从 1 开始算的
            String tmp = s.substring(i - 2, i); // 获得当前数字和之前数字的绑定，这一步很巧妙，比起自己写的
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a; // a -> dp[i-1], b -> dp[i-2]
            b = a;
            a = c;
        }
        return a;
    }

    /**
     * 64. 求1+2+…+n
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * 示例 1：
     * 输入: n = 3
     * 输出: 6
     * 示例 2：
     * 输入: n = 9
     * 输出: 45
     */
    public int sumNums(int n) {
        // TODO: 2023/1/19 第一时间能想到以下 3 种方法
        // 1 使用数列前n项和公式 n * (n+1) / 2，但是包含乘除法
        // 2 使用累加，int sum = 0, sum += i, i from 1 to n，但是包含for while等循环
        // 3 使用递归，n += sumNums(n-1) 终止条件 n==1 时，返回1，但是终止条件判断时，需要用到if
        // 上面 3 种方法，只有递归比较容易改造，只要能做到在 n==1 时返回即可
        // 使用逻辑与的短路性质，A && B 如果A为false，则不会执行B

        // 因此，n > 1 这个条件，当 n一路递减到1的时候，就不再满足，因此就会返回不再执行后面的递归
        // 这个地方让 n > 1 && (n += sumNums(n - 1)) 大于0，没有任何的意义，只是为了能够编译通过，让程序运行起来
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    /**
     * 67. 把字符串转换成整数
     * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     * 说明：
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
     * 如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
     * 示例 1:
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     *      因此无法执行有效的转换。
     * 示例 5:
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     */
    public int strToInt(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        char firstCh = str.charAt(0);
        if (!isNum(firstCh) && firstCh != '+' && firstCh != '-') return 0;
        int index;
        for (index = 1; index < str.length(); index++) {
            if (!isNum(str.charAt(index))) break;
        }
        String numStr;
        if (firstCh == '-'){
            numStr = str.substring(1, index);
            int len = numStr.length();
            long res = 0;
            for (int i = 0; i < len; i++) {
                res += (numStr.charAt(i) - '0') * Math.pow(10, len-1-i);
            }
            res = -res;
            return res < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) res;
        }else {
            numStr = str.substring(0, index);
            if (numStr.charAt(0) == '+') numStr = numStr.substring(1);
            int len = numStr.length();
            long res = 0;
            for (int i = 0; i < len; i++) {
                res += (numStr.charAt(i) - '0') * Math.pow(10, len-1-i);
            }
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
        }
    }
    private boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

}
