package com.dll.linkedList;

import org.junit.Test;

public class DesignLinkedList707Test {
    @Test
    public void test() {
        DesignLinkedList707.MyLinkedList linkedList = new DesignLinkedList707().new MyLinkedList();
        linkedList.addAtHead(1);
        System.out.println("addAtHead(1)" + linkedList.toString());
        linkedList.addAtTail(3);
        System.out.println("addAtTail(3)" + linkedList.toString());
        linkedList.addAtIndex(1,2);
        System.out.println("addAtIndex(1,2)" + linkedList.toString());
        System.out.println("get(1)" + linkedList.get(1));
        System.out.println("deleteAtIndex(1)" + linkedList.toString());
        linkedList.deleteAtIndex(1);
        System.out.println("deleteAtIndex(1)" + linkedList.toString());
        System.out.println("get(1)" + linkedList.get(1));
        linkedList.deleteAtIndex(1);
        System.out.println("deleteAtIndex(1)" + linkedList.toString());
        linkedList.deleteAtIndex(0);
        System.out.println("deleteAtIndex(0)" + linkedList.toString());
    }
}
