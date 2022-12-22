package LeetCodeByClass.DataStructure.Tree;

/**
 * 实现前缀树（Trie）
 * Trie或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 */
// TODO: 2022/12/22
public class Trie {

    private Trie[] children;
    private boolean isEnd;

    public Trie() { // 初始化前缀树对象
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) { // 向前缀树中插入字符串 word
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) { // 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) { // 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
