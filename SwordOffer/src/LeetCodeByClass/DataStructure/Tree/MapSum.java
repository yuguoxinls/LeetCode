package LeetCodeByClass.DataStructure.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 键值映射
 * 设计一个 map ，满足以下几点:
 *  字符串表示键，整数表示值
 *  返回具有前缀等于给定字符串的键的值的总和
 */
// TODO: 2022/12/23
public class MapSum {

    /*class TrieNode {
        int val = 0;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root;
    Map<String, Integer> map;

    public MapSum() { // 初始化 MapSum 对象
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) { // 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对 key-value 将被替代成新的键值对
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode node = root;
        for (char c : key.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
            node.val += delta;
        }
    }

    public int sum(String prefix) { // 返回所有以该前缀 prefix 开头的键 key 的值的总和
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                return 0;
            }
            node = node.next[c - 'a'];
        }
        return node.val;
    }*/ // 用前缀树反而慢了？？？
    // 暴力扫描
    Map<String, Integer> map;

    public MapSum() { // 初始化 MapSum 对象
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int ans = 0;
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            if (s.startsWith(prefix)){
                Integer val = map.get(s);
                ans += val;
            }
        }
        return ans;
    }
}
