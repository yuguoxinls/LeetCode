package SwordByClass.BitOperations;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 15. 二进制中1的个数
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量)
     * 提示：
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
     * 示例 1：
     * 输入：n = 11 (控制台输入 00000000000000000000000000001011)
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
     * 示例 2：
     * 输入：n = 128 (控制台输入 00000000000000000000000010000000)
     * 输出：1
     * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
     * 示例 3：
     * 输入：n = 4294967293 (控制台输入 11111111111111111111111111111101，部分语言中 n = -3）
     * 输出：31
     * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0){
            int res = n & 1;
            if (res == 1) count++;
            n >>>= 1;
        }
        return count;
    }

    /**
     * 56. 数组中数字出现的次数
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
     * 要求时间复杂度是O(n)，空间复杂度是O(1)。
     * 示例 1：
     * 输入：nums = [4,1,4,6]
     * 输出：[1,6] 或 [6,1]
     * 示例 2：
     * 输入：nums = [1,2,10,4,1,4,3,3]
     * 输出：[2,10] 或 [10,2]
     */
    public int[] singleNumbers(int[] nums) {
        // TODO: 2023/1/16
        // 1. 将数组中的元素全部异或，最后的结果是2个落单的数(a,b)的异或，记c = a^b
        // 2. 找到c最右侧的 1，则a b 2个数相应位置一定一个是1，一个是0，这样才能异或出来1
        // 3. 按照相应位置对nums中的数进行分类，1的一类，0的一类，那么出现两次的数一定被分到同一类
        // 4. 按照上述分类后，两类中只有一个数出现一次，分别异或，就可得到a b
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        diff &= -diff; // 找到最右侧的 1

        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & diff) == 0) type1 ^= num;
            else type2 ^= num;
        }

        return new int[] {type1, type2};
    }

    /**
     * 56.2 数组中数字出现的次数 II
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     * 示例 1：
     * 输入：nums = [3,4,3,3]
     * 输出：4
     * 示例 2：
     * 输入：nums = [9,1,7,9,7,9,7]
     * 输出：1
     */
    public int singleNumber(int[] nums) {
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) return key;
        }
        return -1;*/
        // TODO: 2023/1/16 对于出现3次的数字，他的每一个二进制位都出现了3次，因此所有出现3次的数字，其相应为出现的次数是3的倍数
        // 按位统计 1 出现的次数，按位对3求余，结果就是出现一次的数
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num&1;
                num >>>= 1;
            }
        }

        int res = 0;
        for (int i = 0; i < count.length; i++) {
            res <<= 1; // 结果先左移一位，给下边要插上的那一位腾地
            res |= count[31-i]%3;
        }
        return res;
    }

}
