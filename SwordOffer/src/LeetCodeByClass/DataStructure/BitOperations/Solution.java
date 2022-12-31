package LeetCodeByClass.DataStructure.BitOperations;

public class Solution {
    /**
     * 1. 汉明距离
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     * 示例 1：
     * 输入：x = 1, y = 4
     * 输出：2
     * 解释：
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     * 上面的箭头指出了对应二进制位不同的位置。
     * 示例 2：
     * 输入：x = 3, y = 1
     * 输出：1
     */
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int ans = 0;
        while (tmp != 0){
            ans += tmp & 1;
            tmp = tmp >>> 1;
        }
        return ans;
    }

    /**
     * 2. 只出现一次的数字
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * 示例 1 ：
     * 输入：nums = [2,2,1]
     * 输出：1
     * 示例 2 ：
     * 输入：nums = [4,1,2,1,2]
     * 输出：4
     * 示例 3 ：
     * 输入：nums = [1]
     * 输出：1
     */
    public int singleNumber(int[] nums) { // 利用 x^x = 0, x^0 = x
        int ans = 0;
        for (int num : nums) {
            ans = ans ^ num;
        }
        return ans;
    }

    /**
     * 3. 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * 示例 1：
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 4：
     * 输入：nums = [0]
     * 输出：1
     * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
     */
    public int missingNumber(int[] nums) {
        /*Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) return i;
        }
        return -1;*/
        // TODO: 2022/12/30 位运算
        // 令n = nums.length，在nums的后面添加从0到n的所有数，这样就一共有n+(n+1)=2n+1个数
        // 由于数组中缺少从0到n的所有数中的一个，这样的话，这2n+1个数中，只有一个数出现了一次，其余的数都出现了2次
        int ans = 0;
        for (int num : nums) {
            ans = ans ^ num;
        }
        for (int i = 0; i <= nums.length; i++) {
            ans = ans ^ i;
        }
        return ans;
    }

    /**
     * 4. 只出现一次的数字 III
     * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
     * 示例 1：
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     * 示例 2：
     * 输入：nums = [-1,0]
     * 输出：[-1,0]
     * 示例 3：
     * 输入：nums = [0,1]
     * 输出：[1,0]
     */
    public int[] singleNumber3(int[] nums) {
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        int[] ans = new int[2];
        int index = 0;
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            if (val != 2) {
                ans[index++] = key;
            }
            if (index == 2) break;
        }
        return ans;*/
        // TODO: 2022/12/30 位运算
        // 看到这种找不重复数字的，一般都是把数异或一遍，但是和之前不同的是，这次有两个只出现一次的数，直接异或得到的不是最终结果
        // 而是2个出现一次的数的异或，这时要考虑后续的处理方法
        // 设这2个数为a，b，结果c = a^b
        // 1. 找到c最右侧的1，假设是在第l位，则a和b 2个数有一个数的第l位是1，另一个是0，这样异或完之后，结果才是1
        // 2. 可以把nums中的元素分为2类，第l位是1的 和 第l位不是1的
        // 3. 对于出现2次的数，一定会分在同一类中；而a和b一定出现在不同类中，这样对两类分别异或，就能得到a和b
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        diff &= -diff;  // 得到最右侧的1
        // 防止溢出
        int lsb = (diff == Integer.MIN_VALUE ? diff : diff & (-diff));

        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) == 0) { // 说明num第l位是0，应该放到第一类异或
                type1 ^= num;
            }
            else { // 说明num第l位是1，应该放到第二类异或
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }

    /**
     * 5. 颠倒二进制位
     * 颠倒给定的 32 位无符号整数的二进制位。
     * 提示：
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
     * 因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
     * 示例 1：
     * 输入：n = 00000010100101000001111010011100
     * 输出：964176192 (00111001011110000010100101000000)
     * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     * 示例 2：
     * 输入：n = 11111111111111111111111111111101
     * 输出：3221225471 (10111111111111111111111111111111)
     * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
     */
    // you need treat n as an unsigned value
    public int reverseBits(int n) { // TODO: 2022/12/30
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            ret <<= 1;
            ret |= (n & 1); // n&1 获取n的最低一位，和0或之后还是自己，也就是取出n的最后一位放到ret中，ret左移，n右移
            n >>>= 1;
        }
        return ret;
    }

    /**
     * 6. 不用额外变量交换两个整数
     * a = a ^ b;
     * b = a ^ b;
     * a = a ^ b;
     */

    /**
     * 7. 2 的幂
     * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
     * 如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。
     * 示例 1：
     * 输入：n = 1
     * 输出：true
     * 解释：20 = 1
     * 示例 2：
     * 输入：n = 16
     * 输出：true
     * 解释：24 = 16
     * 示例 3：
     * 输入：n = 3
     * 输出：false
     * 示例 4：
     * 输入：n = 4
     * 输出：true
     * 示例 5：
     * 输入：n = 5
     * 输出：false
     */
    public boolean isPowerOfTwo(int n) {
        // TODO: 2022/12/30 一个数 n 是 2 的幂，当且仅当 n 是正整数，并且 n 的二进制表示中仅包含 1 个 1
//        return n > 0 && Integer.bitCount(n) == 1;
        // 不使用API
        // 1. n & (n - 1) 可以将n最低位的1移除，因此若移除后为0，则表示n中只有一个1，满足条件
//        return n > 0 && (n&(n-1)) == 0;
        // 2. n & (-n) 可以获得n最低位的1，因此如果获取最低位的1和n相等，说明n中只有一个1，满足条件
        return n > 0 && (n&(-n)) == n;
    }

    /**
     * 8. 4的幂
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
     * 示例 1：
     * 输入：n = 16
     * 输出：true
     * 示例 2：
     * 输入：n = 5
     * 输出：false
     * 示例 3：
     * 输入：n = 1
     * 输出：true
     */
    public boolean isPowerOfFour(int n) {
        /*if (n <= 0) return false;
        int count = 0;
        boolean b = (n & (-n)) == n;
        if (!b) return false;
        while (n != 0){
            int last = n & 1;
            if (last == 1) break;
            count++;
            n >>>= 1;
        }
        return count%2 == 0;*/
        // TODO: 2022/12/31 上面这一堆就在说明一个事，n是整数 并且 n只有一个1，这个1还在奇数位，可以用一句话概括
        return n > 0 && (n & (n - 1)) == 0 && (n & 0b01010101010101010101010101010101) != 0;
        // 也可以用正则表达式来匹配
//        return Integer.toString(n, 4).matches("10*"); // 不过这个巨慢，还不如自己写的那个
    }

    /**
     * 9. 交替位二进制数
     * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
     * 示例 1：
     * 输入：n = 5
     * 输出：true
     * 解释：5 的二进制表示是：101
     * 示例 2：
     * 输入：n = 7
     * 输出：false
     * 解释：7 的二进制表示是：111.
     * 示例 3：
     * 输入：n = 11
     * 输出：false
     * 解释：11 的二进制表示是：1011.
     */
    public boolean hasAlternatingBits(int n) {
        /*int ptr = 1;
        boolean flag1 = isFlag2(ptr, n, false, 1, 0);
        boolean flag2 = isFlag2(ptr, n, false, 0, 1);
        return !flag1 || !flag2;*/
        // TODO: 2022/12/31 上面自己写的这种也能100%，但是太啰嗦了
        // 但是下面这种不太好想
        int a = (n ^ (n >> 1)); // 当n是交替二进制数时，n ^ (n >> 1)的结果每一位都是 1，也就是a的每一位都是 1
        return (a & (a + 1)) == 0; // a+1 的每一位就都是0，和a &之后，就是0
    }
    private boolean isFlag2(int ptr, int tmp, boolean flag, int i, int j) {
        while (tmp != 0){
            int last = tmp & 1;
            if (ptr % 2 != 0 && last == i){
                ptr++;
                tmp >>>= 1;
            }else if (ptr % 2 == 0 && last == j){
                ptr++;
                tmp >>>= 1;
            }else {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 10. 数字的补数
     * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
     * 例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
     * 给你一个整数 num ，输出它的补数。
     * 示例 1：
     * 输入：num = 5
     * 输出：2
     * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
     * 示例 2：
     * 输入：num = 1
     * 输出：0
     * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
     */
    public int findComplement(int num) {
        int tmp = num, count = 0;
        while (tmp != 0){
            count++;
            tmp >>>= 1;
        }
        int help = 0;
        for (int i = 0; i < count; i++) {
            help += Math.pow(2,i);
        }
        return num ^ help;
    }

    /**
     * 11. 两整数之和
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
     * 示例 1：
     * 输入：a = 1, b = 2
     * 输出：3
     * 示例 2：
     * 输入：a = 2, b = 3
     * 输出：5
     */
    public int getSum(int a, int b) {
        // TODO: 2022/12/31
        //  首先，考虑两个二进制位相加的四种情况如下：
        //   0 + 0 = 0
        //   0 + 1 = 1
        //   1 + 0 = 1
        //   1 + 1 = 0 (进位)
        //  可以发现，对于整数 a 和 b：
        //   在不考虑进位的情况下，其无进位加法结果为 a⊕b。
        //   而所有需要进位的位为 a&b，进位后的进位结果为 (a&b)<<1
        return b == 0 ? a : getSum((a ^ b), (a & b) << 1); // b如果是0的话直接返回a；不是0就要计算a和b的无进位和以及进位，相加即可
    }

    /**
     * 12. 最大单词长度乘积
     * 给你一个字符串数组 words ，找出并返回 length(words[i]) * length(words[j]) 的最大值，并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
     * 示例 1：
     * 输入：words = ["abcw","baz","foo","bar","xtfn","abcdef"]
     * 输出：16
     * 解释：这两个单词为 "abcw", "xtfn"。
     * 示例 2：
     * 输入：words = ["a","ab","abc","d","cd","bcd","abcd"]
     * 输出：4
     * 解释：这两个单词为 "ab", "cd"。
     * 示例 3：
     * 输入：words = ["a","aa","aaa","aaaa"]
     * 输出：0
     * 解释：不存在这样的两个单词。
     */
    public int maxProduct(String[] words) {
        // TODO: 2022/12/31 本题的关键在于判断2个单词中是否含有公共字母，可以采用位运算来实现
        // 对于每个单词，采用一个长为26的掩码来表示哪些字母出现在该单词中，出现的位置记为1
        // 对于words中的每一对单词，当二者的掩码 & 之后 == 0，说明没有相同的字母
        // 此时，用这2个单词的长度乘积更新最大长度乘积
        int len = words.length;
        int[] masks = new int[len]; // 用来记录每个单词的掩码
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int length = word.length();
            for (int j = 0; j < length; j++) {
                char curCh = word.charAt(j);
                int index = curCh - 'a'; // 得到当前字母在掩码中的位置索引：0~25
                masks[i] |= 1 << index; // 把 1 向左移动 index 个位置，添加到当前掩码中
            }
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if ((masks[i] & masks[j]) == 0) max = Math.max(max, words[i].length()*words[j].length());
            }
        }
        return max;
    }

    /**
     * 13. 比特位计数
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     * 示例 1：
     * 输入：n = 2
     * 输出：[0,1,1]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 示例 2：
     * 输入：n = 5
     * 输出：[0,1,1,2,1,2]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 3 --> 11
     * 4 --> 100
     * 5 --> 101
     */
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int count = 0, tmp = i;
            while (tmp != 0){
                /*if ((tmp&1) == 1){
                    count++;
                }*/ // 这个地方导致整个程序很慢
                // TODO: 2022/12/31
                count += tmp&1;
                tmp >>>= 1;
            }
            ans[i] = count;
        }
        return ans;
    }


}
