package LeetCodeByClass.Algorithm.Math;

import java.util.Arrays;

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

    /**
     * 阶乘
     * 1. 阶乘后的零
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
     * 示例 1：
     * 输入：n = 3
     * 输出：0
     * 解释：3! = 6 ，不含尾随 0
     * 示例 2：
     * 输入：n = 5
     * 输出：1
     * 解释：5! = 120 ，有一个尾随 0
     * 示例 3：
     * 输入：n = 0
     * 输出：0
     */
    public int trailingZeroes(int n) { // TODO: 2022/12/5
        /**
         * 尾部的 0 由 2 * 5 得来，2 的数量明显多于 5 的数量，因此只要统计有多少个 5 即可。
         * 对于一个数 N，它所包含 5 的个数为：N/5 + N/5^2 + N/5^3 + ...，
         * 其中 N/5 表示不大于 N 的数中 5 的倍数贡献一个 5，N/5^2 表示不大于 N 的数中 5^2 的倍数再贡献一个 5 ...。
         */
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
        /**
         * 如果统计的是 N! 的二进制表示中最低位 1 的位置，只要统计有多少个 2 即可，
         * 和求解有多少个 5 一样，2 的个数为 N/2 + N/2^2 + N/2^3 + ...
         */
    }

    /**
     * 字符串加法减法
     * 1. 二进制求和
     * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
     * 示例 1：
     * 输入:a = "11", b = "1"
     * 输出："100"
     * 示例 2：
     * 输入：a = "1010", b = "1011"
     * 输出："10101"
     */
    public String addBinary(String a, String b) { // TODO: 2022/12/5
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        StringBuilder str = new StringBuilder();
        while (carry == 1 || i >= 0 || j >= 0) {
            if (i >= 0 && a.charAt(i--) == '1') {
                carry++;
            }
            if (j >= 0 && b.charAt(j--) == '1') {
                carry++;
            }
            str.append(carry % 2);
            carry /= 2;
        }
        return str.reverse().toString();
    }

    /**
     * 2. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
     * 示例 1：
     * 输入：num1 = "11", num2 = "123"
     * 输出："134"
     * 示例 2
     * 输入：num1 = "456", num2 = "77"
     * 输出："533"
     * 示例 3：
     * 输入：num1 = "0", num2 = "0"
     * 输出："0"
     */
    public String addStrings(String num1, String num2) {  // TODO: 2022/12/5 这其实是对任意进制的通用方法，上面的二进制也能用
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuilder str = new StringBuilder();
        while (carry == 1 || i >= 0 || j >= 0) {
            int x = i < 0 ? 0 : num1.charAt(i--) - '0'; // 获得当前的数字，如果已经遍历完，就认为是0
            int y = j < 0 ? 0 : num2.charAt(j--) - '0';
            str.append((x + y + carry) % 10); // 当前数字相加，再加上进位，对10取余便是得数的个位
            carry = (x + y + carry) / 10; // 获得 得数的进位
        }
        return str.reverse().toString();
    }

    /**
     * 相遇问题
     * 1. 最小操作次数使数组元素相等 II
     * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数。
     * 在一次操作中，你可以使数组中的一个元素加 1 或者减 1 。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：2
     * 解释：
     * 只需要两次操作（每次操作指南使一个元素加 1 或减 1）：
     * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     * 示例 2：
     * 输入：nums = [1,10,2,9]
     * 输出：16
     */
    public int minMoves2(int[] nums) { // TODO: 2022/12/6
        /**
         * 设 m 为中位数。a 和 b 是 m 两边的两个元素，且 b > a。要使 a 和 b 相等，它们总共移动的次数为 b - a，
         * 这个值等于 (b - m) + (m - a)，也就是把这两个数移动到中位数的移动次数。
         */
        Arrays.sort(nums);
        int ans = 0;
        int left = 0, right = nums.length - 1;
        while (left <= right){
            ans = ans + (nums[right--] - nums[left++]);
        }
        return ans;
    }

    /**
     * 多数投票问题
     * 1. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1：
     * 输入：nums = [3,2,3]
     * 输出：3
     * 示例 2：
     * 输入：nums = [2,2,1,1,1,2,2]
     * 输出：2
     */
    public int majorityElement(int[] nums) {
        int win = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == win){
                count++;
            }else {
                count--;
                if (count == 0){
                    win = nums[i];
                    count = 1;
                }
            }
        }
        return win;
    }

    /**
     * 其他
     * 1. 有效的完全平方数
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * 进阶：不要 使用任何内置的库函数，如  sqrt 。
     * 示例 1：
     * 输入：num = 16
     * 输出：true
     * 示例 2：
     * 输入：num = 14
     * 输出：false
     */
    public boolean isPerfectSquare(int num) {
        long left = 1, right = num/2; // TODO: 2022/12/6 注意使用long，int在计算 mid*mid 时可能会越界
        while(left < right){
            long mid = left + (right-left)/2;

            if (mid * mid > num) right = mid;
            else if (mid * mid < num) left = mid + 1;
            else return true;
        }
        return left * left == num;
    }

    /**
     * 2. 3 的幂
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
     * 示例 1：
     * 输入：n = 27
     * 输出：true
     * 示例 2：
     * 输入：n = 0
     * 输出：false
     * 示例 3：
     * 输入：n = 9
     * 输出：true
     * 示例 4：
     * 输入：n = 45
     * 输出：false
     */
    public boolean isPowerOfThree(int n) { // TODO: 2022/12/6
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 3. 除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 示例 1:
     * 输入: nums = [1,2,3,4]
     * 输出: [24,12,8,6]
     * 示例 2:
     * 输入: nums = [-1,1,0,-3,3]
     * 输出: [0,0,9,0,0]
     */
    public int[] productExceptSelf(int[] nums) { // TODO: 2022/12/6
        /**
         * 用数组, L[i] 存储nums[i]左侧所有元素的乘积, L[0]=1
         * 用数组, R[i] 存储nums[i]右侧所有元素的乘积, R[nums.length - 1] = 1
         * answer[i] = L[i] * R[i]
         */
        // 方法一
        /*int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];

        L[0] = 1;
        for (int i = 1; i < L.length; i++) {
            L[i] = L[i-1] * nums[i-1];
        }

        R[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            R[i] = R[i+1] * nums[i+1];
        }

        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = L[i] * R[i];
        }
        return ans;*/
        // 方法二：使用ans数组作为 L 数组，动态构造 R 数组
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }

    /**
     * 4. 三个数的最大乘积
     * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：6
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：24
     * 示例 3：
     * 输入：nums = [-1,-2,-3]
     * 输出：-6
     */
    /*int max = Integer.MIN_VALUE;
    public int maximumProduct(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        backTracing(sub, nums, 0);
        return max;
    }
    private void backTracing(List<Integer> sub, int[] nums, int start) {
        if (sub.size() == 3){
            int sum = 1;
            for (Integer data : sub) {
                sum *= data;
            }
            max = Math.max(max, sum);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            sub.add(nums[i]);
            backTracing(sub, nums, i+1);
            sub.remove(sub.size() - 1);
        }
    }*/ // 回溯，超出时间限制
    public int maximumProduct(int[] nums) { // TODO: 2022/12/6
        //  方法一 ：排序
        // 如果全是非负数或非正数，升序排列后，三个数的最大乘积就是最后三个数的乘积
        // 如果正数负数都有，三个数的最大乘积
        //  1. 三个最大正数的乘积
        //  2. 两个最小负数和一个最大正数的乘积
        /*Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[len-1]*nums[len-2]*nums[len-3], nums[0]*nums[1]*nums[len-1]);*/
        // 方法二：线性扫描
        // 在方法一中，我们实际上只要求出数组中 最大的三个数 以及 最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) { // 当前的数比最小的数还小，那当前的数应该是最小的数，之前的最小的数应该是第二小的
                min2 = min1;
                min1 = x;
            } else if (x < min2) { // 当前的数比最第二小的数小，那当前的数应该是第二小的
                min2 = x;
            }

            if (x > max1) { // 当前的数比最大的数还大，那当前的数应该是最大的数，之前的最大的数应该是第二大的，之前第二大的应该是第三大的
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) { // 当前的数比最第二大的数大，那当前的数应该是第二大的
                max3 = max2;
                max2 = x;
            } else if (x > max3) { // 当前的数比最第三大的数大，那当前的数应该是第三大的
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


}
