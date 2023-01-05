package SwordByClass.List;

import java.util.*;

public class Solution {
    /**
     * 6. 从尾到头打印链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     */
    public int[] reversePrint(ListNode head) {
        /*if (head == null) return new int[0];
        List<Integer> ans = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null) {
            ans.add(tmp.val);
            tmp = tmp.next;
        }
        int[] res = new int[ans.size()];
        int index = res.length-1;
        for (Integer num : ans) {
            res[index--] = num;
        }
        return res;*/
        // 这种反向的操作，很自然可以想到使用栈来操作
        Deque<Integer> stack = new LinkedList<>();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        int[] ans = new int[stack.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    /**
     * 18. 删除链表的节点
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     * 注意：此题对比原题有改动
     * 示例 1:
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode tmp = head;
        while (tmp.next != null && tmp.next.val != val){
            tmp = tmp.next;
        }
        if (tmp.next == null) return head;
        tmp.next = tmp.next.next;
        return head;
    }

    /**
     * 22. 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第k个节点。
     * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。
     * 这个链表的倒数第 3 个节点是值为 4 的节点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * 返回链表 4->5.
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        /*if (head == null) return null;
        int len = 0;
        ListNode tmp = head;
        while (tmp != null){
            len++;
            tmp = tmp.next;
        }
        int step = len-k;
        tmp = head;
        while (step-- != 0){
            tmp = tmp.next;
        }
        return tmp;*/
        // TODO: 2023/1/5 上面是第一时间想到的方法，其实可以使用双指针，省去统计链表长度的那一步
        if (head == null) return null;
        ListNode fast = head, slow = head; // 定义两个指针，使他们之间的距离为 k
        while (k-- != 0){
            fast = fast.next;
        }
        while (fast != null){ // 同时移动这2个指针，这样当 快指针到链表末尾时，慢指针就指向倒数第 k个节点了
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 24. 反转链表
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, cur = head;
        while (cur != null){
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = pre;
            pre = tmp;
        }
        return pre;
    }

    /**
     * 25. 合并两个排序的链表
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     * 示例1：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dump = new ListNode(0), tmp = dump;
        while (l1 != null && l2 != null){
            ListNode node;
            if (l1.val < l2.val){
                node = new ListNode(l1.val);
                l1 = l1.next;
            }else {
                node = new ListNode(l2.val);
                l2 = l2.next;
            }
            tmp.next = node;
            tmp = tmp.next;
        }
        if (l1 == null) tmp.next = l2;
        if (l2 == null) tmp.next = l1;
        return dump.next;
    }

    /**
     * 52. 两个链表的第一个公共节点
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B){
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }

    /**
     * 35. 复杂链表的复制
     * 请实现 copyRandomList 函数，复制一个复杂链表。
     * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     */
    public Node copyRandomList(Node head) {
        // TODO: 2023/1/5 问题关键在于这个random指针，可以用一个哈希表存储原始链表和新链表节点的对应关系
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node tmp = head;
        while (tmp != null){ // 用哈希表存储原始链表和新链表节点的对应关系
            map.put(tmp, new Node(tmp.val));
            tmp = tmp.next;
        }
        tmp = head;
        Node dump = new Node(0), tmpD = dump;
        while (tmp != null){
            tmpD.next = map.get(tmp);
            tmpD = tmpD.next;
            Node randomKey = tmp.random;
            tmpD.random = map.get(randomKey);
            tmp = tmp.next;
        }
        return dump.next;

    }
}
