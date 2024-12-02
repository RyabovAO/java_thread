package com.aleksey.module_1_4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();

    public void first() {
        lock.lock();
        try{
            System.out.print("first");
            condition1.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void second() {
        lock.lock();
        try {
            condition1.await();
            System.out.print("second");
            condition2.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void third() {
        lock.lock();
        try {
            condition2.await();
            System.out.print("third");
            condition2.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
