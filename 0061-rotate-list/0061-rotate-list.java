class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: find length and tail
        ListNode tail = head;
        int n = 1;

        while (tail.next != null) {
            tail = tail.next;
            n++;
        }

        // Step 2: make it circular
        tail.next = head;

        // Step 3: reduce k
        k = k % n;
        int stepsToNewTail = n - k;

        // Step 4: find new tail
        ListNode newTail = tail;
        while (stepsToNewTail-- > 0) {
            newTail = newTail.next;
        }

        // Step 5: break the cycle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}