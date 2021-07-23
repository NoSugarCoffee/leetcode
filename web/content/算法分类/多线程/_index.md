---
weight: 10
title: "多线程"
---

## 交替打印FooBar [1115](https://leetcode-cn.com/problems/print-foobar-alternately/)

利用 AtomicBoolean 来互斥的访问临界资源

```java
// ../../../../../src/main/java/com/dll/multithreads/PrintFoobarAlternately.java

package com.dll.multithreads;

import java.util.concurrent.atomic.AtomicBoolean;

public class PrintFoobarAlternately {
    class FooBar {
        private int n;
        private AtomicBoolean flag = new AtomicBoolean(true);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (!flag.get()) {
                    Thread.yield();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag.set(false);
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (flag.get()) {
                    Thread.yield();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag.set(true);
            }
        }
    }
}

```


