package com.dll.linkedList;

public class DesignLinkedList707 {
  class MyLinkedList {
    private Node dummy;
    // tail refs to last one of the list
    private Node tail;
    private int len;

    /** Initialize your data structure here. */
    public MyLinkedList() {
      dummy = new Node(-1);
      tail = dummy;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
      Node p = dummy.next;
      if (index < 0 || index >= len) {
        return -1;
      }
      while (index > 0) {
        p = p.next;
        index--;
      }
      return p.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the
     * new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
      Node pre = dummy;
      Node p = dummy.next;
      pre.next = new Node(val, p);
      len++;
      if (p == null) {
        tail = pre.next;
      }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
      Node node = new Node(val);
      tail.next = node;
      tail = node;
      len++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the
     * length of linked list, the node will be appended to the end of linked list. If index is
     * greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
      Node pre = dummy;
      Node p = dummy.next;

      if (index < 0) {
        this.addAtHead(val);
        return;
      }
      if (index > len) {
        return;
      }
      if (index == len) {
        this.addAtTail(val);
        return;
      }

      while (index > 0) {
        index--;
        pre = p;
        p = p.next;
      }
      pre.next = new Node(val, p);
      len++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
      Node p = dummy.next;
      Node pre = dummy;
      if (index < 0 || index >= len) {
        return;
      }
      while (index > 0) {
        index--;
        pre = p;
        p = p.next;
      }

      pre.next = p.next;
      len--;
      if (p.next == null) {
        tail = pre;
      }
    }

    @Override
    public String toString() {
      Node p = this.dummy.next;
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      while (p != null) {
        sb.append(p.val + "->");
        p = p.next;
      }
      if (tail == null) {
        sb.append("null] tail: null");
      } else {
        sb.append("null] tail:" + tail.val);
      }
      return sb.toString();
    }

    class Node {
      private int val;
      private Node next;

      public Node(int val) {
        this(val, null);
      }

      public Node(int val, Node next) {
        this.val = val;
        this.next = next;
      }
    }
  }
}
