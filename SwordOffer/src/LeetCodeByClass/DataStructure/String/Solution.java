package LeetCodeByClass.DataStructure.String;

import java.util.*;

public class Solution {
    /**
     * 1. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     */
    public boolean isAnagram(String s, String t) {
        /*Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        for (char c : sArr) {
            sMap.put(c, sMap.getOrDefault(c, 0)+1);
        }
        for (char c : tArr) {
            tMap.put(c, tMap.getOrDefault(c, 0)+1);
        }

        if (sMap.size() != tMap.size()) return false;

        for (Character sKey : sMap.keySet()) {
            if (tMap.containsKey(sKey)){
                Integer sVal = sMap.get(sKey);
                Integer tVal = tMap.get(sKey);
                if (!Objects.equals(sVal, tVal)) {
                    return false;
                }
            }else return false;
        }
        return true;*/ // 速度太慢
        // TODO: 2022/12/25 由于本题的字符串只包含 26 个小写字符，因此可以使用长度为 26 的整型数组对字符串出现的字符进行统计，不再使用 HashMap
        // 简言之，利用长度为26的数组来记录字符串中相应字母出现的次数
        int[] count = new int[26];
        for (char c : s.toCharArray()) { // 统计相应字母出现的次数
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) { // 将相应的字母次数自减
            count[c - 'a']--;
        }
        for (int data : count) { // 全是零，说明两个字符串所含字母及其个数完全相同，否则就不同
            if (data != 0) return false;
        }
        return true;
    }

    /**
     * 2. 最长回文串
     * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
     * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
     * 示例 1:
     * 输入:s = "abccccdd"
     * 输出:7
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     * 示例 2:
     * 输入:s = "a"
     * 输入:1
     * 示例 3：
     * 输入:s = "aaaaaccc"
     * 输入:7
     */
    public int longestPalindrome(String s) {
        // TODO: 2022/12/25 对于每个字符 ch，假设它出现了 v 次，我们可以使用该字符 v / 2 * 2 次，在回文串的左侧和右侧分别放置 v / 2 个字符 ch，其中 / 为整数除法。
        //  例如若 "a" 出现了 5 次，那么我们可以使用 "a" 的次数为 4，回文串的左右两侧分别放置 2 个 "a"
        //  如果有任何一个字符 ch 的出现次数 v 为奇数（即 v % 2 == 1），那么可以将这个字符作为回文中心，注意只能最多有一个字符作为回文中心。
        //  在代码中，我们用 ans 存储回文串的长度，由于在遍历字符时，ans 每次会增加 v / 2 * 2，因此 ans 一直为偶数。
        //  但在发现了第一个出现次数为奇数的字符后，我们将 ans 增加 1，这样 ans 变为奇数，在后面发现其它出现奇数次的字符时，我们就不改变 ans 的值了。

        int[] count = new int[128]; // 使用了一个长度为 128 的数组，存储每个字符出现的次数，这是因为字符的 ASCII 值的范围为 [0, 128)
        // 这个数组中不会用到全部的位置，最多用到 26+26 个英文字母的位置

        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            count[c]++;
        }

        int ans = 0;
        for (int v: count) { // 得到每个字母出现的次数
            ans += v / 2 * 2; // v/2 代表在中心左右各出现多少次
            if (v % 2 == 1 && ans % 2 == 0) { // 如果当前的次数是奇数，那么把剩下的一个放到串的中间，也就是对ans++，然后就不能再往中间放单独的了，因此加上 ans%2==0
                ans++;
            }
        }
        return ans;
    }

    /**
     * 3. 同构字符串
     * 给定两个字符串 s 和 t ，判断它们是否是同构的。
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     * 示例 1:
     * 输入：s = "egg", t = "add"
     * 输出：true
     * 示例 2：
     * 输入：s = "foo", t = "bar"
     * 输出：false
     * 示例 3：
     * 输入：s = "paper", t = "title"
     * 输出：true
     */
    public boolean isIsomorphic(String s, String t) {
        // TODO: 2022/12/26 维护两张hashtable，用来记录从s映射到t 以及 从t映射到s的映射关系
        //  从左至右遍历两个字符串的字符，不断更新两张哈希表，如果出现冲突（即当前下标 index 对应的字符 s[index] 已经存在映射且不为 t[index]
        //  或当前下标 index 对应的字符 t[index] 已经存在映射且不为 s[index]）时说明两个字符串无法构成同构，返回 false
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                // 如果s2t包含s中的当前字符x 且 x的映射不是t中的当前字符y || t2s包含t中的当前字符y 且 y的映射不是s中的当前字符x
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }

    /**
     * 4. 回文子串
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     * 回文字符串 是正着读和倒过来读一样的字符串。
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * 示例 1：
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * 示例 2：
     * 输入：s = "aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     */
    private int cnt = 0;
    public int countSubstrings(String s) {
        // TODO: 2022/12/26 思路是枚举所有的回文子串，然后统计个数，中点在于如何枚举
        //  以每个字符为中心，向左右两边扩展，只要左右两边相同，那么就是一个子串
        for (int i = 0; i < s.length(); i++) {
            extendSubstrings(s, i, i);     // 奇数长度的子串，中心是一个字符
            extendSubstrings(s, i, i + 1); // 偶数长度的子串，中心是两个字符
        }
        return cnt;
    }
    private void extendSubstrings(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) { // 在字符串s的索引范围内，比较所有两个字符是否相同
            start--;
            end++;
            cnt++; // 子串数量自增 1
        }
    }

    /**
     * 5. 回文数
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 例如，121 是回文，而 123 不是。
     * 示例 1：
     * 输入：x = 121
     * 输出：true
     * 示例 2：
     * 输入：x = -121
     * 输出：false
     * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3：
     * 输入：x = 10
     * 输出：false
     * 解释：从右向左读, 为 01 。因此它不是一个回文数。
     */
    public boolean isPalindrome(int x) {
        /*String s = x + "";
        int left = 0, right = s.length() - 1;
        while (left < right){
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;*/
        // TODO: 2022/12/26 不转为字符串：将整数分成左右两部分，右边那部分需要转置，然后判断这两部分是否相等
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int right = 0; // 存储整数的右半部分
        while (x > right) {
            right = right * 10 + x % 10;
            x /= 10;
        }
        return x == right || x == right / 10; // x == right是x长度为偶数的情况，x == right / 10是x长度为奇数的情况
    }

    /**
     * 6. 计数二进制子串
     * 给定一个字符串 s，统计并返回具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是成组连续的。
     * 重复出现（不同位置）的子串也要统计它们出现的次数。
     * 示例 1：
     * 输入：s = "00110011"
     * 输出：6
     * 解释：6 个子串满足具有相同数量的连续 1 和 0 ："0011"、"01"、"1100"、"10"、"0011" 和 "01" 。
     * 注意，一些重复出现的子串（不同位置）要统计它们出现的次数。
     * 另外，"00110011" 不是有效的子串，因为所有的 0（还有 1 ）没有组合在一起。
     * 示例 2：
     * 输入：s = "10101"
     * 输出：4
     * 解释：有 4 个子串："10"、"01"、"10"、"01" ，具有相同数量的连续 1 和 0 。
     */
    public int countBinarySubstrings(String s) {
        // TODO: 2022/12/26 我们可以将字符串 s 按照 0 和 1 的连续段分组，存在 counts 数组中，
        //  例如 s=00111011，可以得到这样的 counts数组：counts={2,3,1,2}
        //  数组中相邻的元素 u,v 代表两种不同字符的个数，它们能组成的满足条件的子串数目为 min{u,v}，即一对相邻的数字对答案的贡献
        //  遍历所有相邻的数对，求它们的贡献总和，即可得到答案
        List<Integer> counts = new ArrayList<>();
        /*char[] chars = s.toCharArray();
        char win = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == win) count++;
            else {
                counts.add(count);
                win = chars[i];
                count = 1;
            }
        }
        counts.add(count);*/
        // 官方统计方法1
        /*int ptr = 0, n = s.length();
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            counts.add(count);
            int ans = 0;
            for (int i = 1; i < counts.size(); ++i) {
                ans += Math.min(counts.get(i), counts.get(i - 1));
            }
            return ans;
        }*/
        // 官方统计方法2 用一个 last 变量来维护当前位置的前一个位置，这样可以省去一个 counts 数组的空间
        int ptr = 0, n = s.length(), last = 0, ans = 0;
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            ans += Math.min(count, last);
            last = count;
        }
        return ans;
    }
}
