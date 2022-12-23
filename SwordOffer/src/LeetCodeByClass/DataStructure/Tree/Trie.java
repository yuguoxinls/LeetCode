package LeetCodeByClass.DataStructure.Tree;

/**
 * 实现前缀树（Trie）
 * Trie或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 */
// TODO: 2022/12/22
public class Trie {
    /**
     * 前缀树是一颗多叉树，每个节点代表一个字符，至多可有26个叉
     */

    private Trie[] children; // 用于表示子节点，通常是26个字母
    private boolean isEnd; // 用于标记当前字符是否是一个单词的结束

    public Trie() { // 初始化前缀树对象
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) { // 向前缀树中插入字符串 word
        Trie node = this; // 可以理解为根节点
        for (int i = 0; i < word.length(); i++) { // 遍历字符串 word 的每一个字符
            char ch = word.charAt(i); // 得到当前字符
            int index = ch - 'a'; // 得到当前字符在 0-25 中的索引值
            if (node.children[index] == null) { // 如果父节点的下一个节点不是当前字符
                node.children[index] = new Trie(); // 创建节点
            }
            node = node.children[index]; // 说明父节点的下一个子节点已经有当前字符了，更新当前节点为父节点
        }
        node.isEnd = true; // 遍历字符串 word 完成后，标记结束
    }

    public boolean search(String word) { // 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
        Trie node = searchPrefix(word);
        return node != null && node.isEnd; // search方法是判断word整个单词，而不是前缀，因此节点不为null的同时，还要求结束标志为true
    }

    public boolean startsWith(String prefix) { // 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
        Trie node = searchPrefix(prefix);
        return node != null; // 判断前缀，是不是整个单词无所谓，只要不为null即可
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this; // 从根节点开始检查
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i); // 得到当前字符
            int index = ch - 'a'; // 得到当前字符在 0-25 中的索引值
            if (node.children[index] == null) { // 如果父节点的下一个节点不是当前字符，直接返回null
                return null;
            }
            node = node.children[index]; // 说明父节点的下一个子节点已经有当前字符了，更新当前节点为父节点
        }
        return node;
    }
}
