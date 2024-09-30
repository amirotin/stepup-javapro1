package ru.stepup.task3;

public class Runner {
    public static void main(String[] args) {
        BasicThreadPool pool = new BasicThreadPool(3);
        for (int i = 0; i < 7; i++) {
            final int taskNumber = i;
            pool.execute(() -> {
                System.out.println("Начало: Поток " + Thread.currentThread().getName() + " задача " + taskNumber);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Конец: Поток " + Thread.currentThread().getName() + " задача " + taskNumber);
            });
        }

        pool.shutdown();
        pool.awaitTerminator();
    }
}
