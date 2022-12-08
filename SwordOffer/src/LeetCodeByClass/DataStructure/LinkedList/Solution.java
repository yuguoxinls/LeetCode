package LeetCodeByClass.DataStructure.LinkedList;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /**
     * 1. 相交链表
     * 例如以下示例中 A 和 B 两个链表相交于 c1：
     * A:          a1 → a2
     *                     ↘
     *                       c1 → c2 → c3
     *                     ↗
     * B:    b1 → b2 → b3
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B =headB;
        while (A != B){
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }

    /**
     * 2. 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null, tmp = head.next;
        while(tmp != null){
            head.next = pre;
            pre = head;
            head = tmp;
            tmp = tmp.next;
        }
        head.next = pre;
        return head;
    }

    /**
     * 3. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode falseHead = new ListNode(), tmp = falseHead;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                tmp.next = new ListNode(list1.val);
                list1 = list1.next;
            }else {
                tmp.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            tmp = tmp.next;
        }
        if (list1 == null) tmp.next = list2;
        if (list2 == null) tmp.next = list1;
        return falseHead.next;
    }

    /**
     * 4. 删除排序链表中的重复元素
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode falseHead = new ListNode(), tmpNode = falseHead, tmpHead = head;
        int tmpValue = head.val;
        while(true){
            tmpNode.next = new ListNode(tmpValue);
            tmpNode = tmpNode.next;
            tmpHead = tmpHead.next;
            if (tmpHead == null) break;
            while (tmpHead != null && tmpHead.val == tmpValue) tmpHead = tmpHead.next;
            if (tmpHead == null) break;
            tmpValue = tmpHead.val;
        }
        return falseHead.next;
    }

    /**
     * 5. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /*if (head == null || n == 0) return head;
        int len = 0;
        ListNode tmp = head;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        if (len == n) return head.next;
        tmp = head;
        if (len == 1){
            return null;
        }
        if (len == 2){
            if (n == 1){
                head.next = null;
                return head;
            }
            if (n == 2) return head.next;
        }
        int step = len - n;
        while (--step > 0){
            tmp = tmp.next;
        }
        tmp.next = tmp.next.next;
        return head;*/ // 速度也可以，就是代码看起来有点繁琐
        // TODO: 2022/12/7
        ListNode dummy = new ListNode(0, head); // 建立一个哑节点，这样即使删除的是头节点，也可以在最后返回dummy.next
        /**
         * 使用栈
         */
        /*Deque<ListNode> stack = new LinkedList<>();
        ListNode tmp = dummy;
        while (tmp != null){
            stack.push(tmp);
            tmp = tmp.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        ListNode pre = stack.peek(); // 这样就方便的定位到了要删除的节点的前一个结点
        pre.next = pre.next.next;
        return dummy.next;*/
        /**
         * 双指针：可以使用两个指针 fast 和 slow 同时对链表进行遍历，并且 fast 比 slow 超前 n 个节点。
         * 当 fast 遍历到链表的末尾时，slow 就恰好处于倒数第 n 个节点。
         * 具体操作时，删除结点的时候，让slow位于待删除节点的前一个结点更好操作，因此可以让slow指向头部哑节点
         */
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        ListNode slow = dummy;
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 6. 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     * 输入：head = [1]
     * 输出：[1]
     */
    public ListNode swapPairs(ListNode head) {
        if (head== null || head.next == null) return head;
        ListNode fast = head.next, slow = head, dummy = new ListNode(), tmp = dummy;

        while (true) {
            tmp.next = new ListNode(fast.val);
            tmp = tmp.next;
            tmp.next = new ListNode(slow.val);
            tmp = tmp.next;
            if (fast.next == null) break;
            if (fast.next.next == null) {
                tmp.next = new ListNode(fast.next.val);
                break;
            }
            fast = fast.next.next;
            slow = slow.next.next;
        }
        return dummy.next;
    }

    /**
     * 7. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * 示例1：
     * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
     * 输出：[7,8,0,7]
     * 示例2：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[8,0,7]
     * 示例3：
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) { // TODO: 2022/12/8 肯定要用栈，重点在如何处理进位
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        Deque<Integer> stack1 = buildStack(l1);
        Deque<Integer> stack2 = buildStack(l2);
        ListNode dummy = new ListNode();
        int carry = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){ // 只要还有数没加完，就继续加，不管是来自原来两个链表的数，还是进位
            int x = stack1.isEmpty() ? 0 : stack1.pop(); // 查看当前栈还有数没加吗，有的话取这个数，没有的话就取0
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + carry; // 计算 2 个数的和，包括之前的进位
            ListNode tmpNode = new ListNode(sum%10); // sum%10能够得到和的个位
            tmpNode.next = dummy.next; // 这两步操作很关键，每次把计算得到的结果节点都插到哑节点的后面，这样就不用最后再把链表翻转一次了
            dummy.next = tmpNode;
            carry = sum/10; // sum/10能够得到和的进位
        }
        return dummy.next;
    }
    private Deque<Integer> buildStack(ListNode head) {
        Deque<Integer> ans = new LinkedList<>();
        ListNode tmp = head;
        while (tmp != null){
            ans.push(tmp.val);
            tmp = tmp.next;
        }
        return ans;
    }

    /**
     * 8. 回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(ListNode head) {
        if (head==null) return true;
        List<Integer> val = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null){
            val.add(tmp.val);
            tmp= tmp.next;
        }
        int left = 0, right = val.size() - 1;
        while (left < right){
            if (val.get(left) != val.get(right)) return false;
            left++;
            right--;
        }
        return true;
    }

}
