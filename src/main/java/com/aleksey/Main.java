package com.aleksey;

import com.aleksey.module_1_4.Foo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread thread1 = new Thread(foo::first);
        Thread thread2 = new Thread(foo::second);
        Thread thread3 = new Thread(foo::third);

        thread2.start();
        thread1.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        try(ExecutorService executor = Executors.newFixedThreadPool(3)) {
//            CompletableFuture.runAsync(foo::first, executor)
//                    .thenRunAsync(foo::second, executor)
//                    .thenRunAsync(foo::third, executor);
//        }
    }
}