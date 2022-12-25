package LeetCodeByClass.DataStructure.String;

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
}
