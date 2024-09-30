package ru.stepup.task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class BasicThreadPool {
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);
    private final Thread[] threads;
    private final Queue<Runnable> taskQueue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public BasicThreadPool(int poolSize) {
        threads = new Thread[poolSize];
        for (int i = 0; i < poolSize; i++) {
            threads[i] = new Thread(() -> {
                while (!isShutdown.get() || !taskQueue.isEmpty()) {
                    Runnable task;
                    lock.lock();
                    try {
                        task = taskQueue.poll();
                    } finally {
                        lock.unlock();
                    }
                    if (task != null) {
                        task.run();
                    }
                }
            });
            threads[i].start();
        }
    }

    public void execute(Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("Пул остановлен");
        }
        taskQueue.add(task);
    }

    public void shutdown() {
        isShutdown.set(true);
    }

    public void awaitTerminator() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
