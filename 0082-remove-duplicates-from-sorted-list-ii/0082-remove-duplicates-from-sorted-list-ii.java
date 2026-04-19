class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy; // last node before duplicate sequence
        ListNode curr = head;

        while (curr != null) {
            // Detect duplicates
            if (curr.next != null && curr.val == curr.next.val) {
                int dupVal = curr.val;

                // Skip all nodes with this value
                while (curr != null && curr.val == dupVal) {
                    curr = curr.next;
                }

                prev.next = curr; // remove duplicates entirely
            } else {
                prev = curr; // move prev normally
                curr = curr.next;
            }
        }

        return dummy.next;
    }
}