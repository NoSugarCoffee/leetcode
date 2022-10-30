package com.dll.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache146 {

  private DoubleLinkedList list = new DoubleLinkedList();
  private int capacity;
  public LRUCache146(int capacity) {
    if (capacity <= 0) {
      throw new RuntimeException();
    }
    this.capacity = capacity;
  }

  /**
   * get value by key from cache
   *
   * @param key key of cache
   * @return value of key
   */
  public int get(int key) {
    DoubleLinkedList.Node node = list.get(key);
    if (node == null) {
      return -1;
    }
    list.delete(node);
    list.addFirst(node.key, node.value);
    return node.value;
  }

  /**
   * put key and value to cache
   *
   * @param key key of cache
   * @param value value of key
   */
  public void put(int key, int value) {
    // 1. exist in map -> remove(value) & push(value)
    // 2. don't exist in map -> deque is full -> removeLast & push(value)
    // 3. don't exist in map -> deque is empty -> push(value)
    if (list.containsKey(key)) {
      list.delete(list.get(key));
    } else {
      if (list.getSize() >= this.capacity) {
        list.deleteLast();
      }
    }
    list.addFirst(key, value);
  }

  class DoubleLinkedList {
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int size;

    private DoubleLinkedList() {
      head = new Node(null, -1, -1, null);
      tail = new Node(null, -1, -1, null);
      head.next = tail;
      tail.prev = head;
      this.map = new HashMap<>();
    }

    public int getSize() {
      return this.size;
    }

    private boolean containsKey(int key) {
      return this.map.containsKey(key);
    }

    public Node get(int key) {
      return this.map.getOrDefault(key, null);
    }

    public void delete(Node node) {
      Node prev = node.prev;
      Node next = node.next;
      prev.next = next;
      next.prev = prev;
      this.map.remove(node.key);
      this.size--;
    }

    private Node addFirst(int key, int value) {
      Node node = new Node(head, key, value, head.next);
      head.next.prev = node;
      head.next = node;
      this.size++;
      this.map.put(key, node);
      return node;
    }

    private Node deleteLast() {
      Node deletedPrev = tail.prev.prev;
      Node deleted = deletedPrev.next;
      deletedPrev.next = tail;
      tail.prev = deletedPrev;
      this.map.remove(deleted.key);
      return deleted;
    }

    class Node {
      int key;
      int value;
      Node prev;
      Node next;

      Node(Node prev, int key, int value, Node next) {
        this.key = key;
        this.prev = prev;
        this.value = value;
        this.next = next;
      }
    }
  }
}
