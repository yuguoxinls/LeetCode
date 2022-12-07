package LeetCodeByClass.DataStructure.LinkedList;

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

}
