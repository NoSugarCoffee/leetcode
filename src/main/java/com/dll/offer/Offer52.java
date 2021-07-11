package com.dll.offer;

public class Offer52 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }
    class Solution {
        public ListNode getIntersectionNOde(ListNode headA, ListNode headB) {
                ListNode pa = headA;
                ListNode pb = headB;
                int lenA = 0;
                int lenB = 0;
                while(pa != null) {
                    lenA++;
                    pa = pa.next;
                }
                while(pb != null) {
                    lenB++;
                    pb = pb.next;
                }
                int distance = Math.abs(lenA - lenB);
                ListNode pLong = headA;
                ListNode pShort = headB;
                if (lenB > lenA) {
                    pLong = headB;
                    pShort = headA;
                }
                while(distance-- > 0) {
                    pLong = pLong.next;
                }
                while(pLong != null) {
                    if (pLong == pShort) {
                        return pLong;
                    }
                    pLong = pLong.next;
                    pShort = pShort.next;
                }
                return null;
        }
    }
}
