/**
 * Используем счётчик MultiThreadUnSafeCounter
 * Начальное значение счётчика : 0
 * Конечное значение счётчика : -274756
 * Прошло времени, мс: 55
 *
 * Используем счётчик MultiThreadSafeCounter
 * Начальное значение счётчика : 0
 * Конечное значение счётчика : 0
 * Прошло времени, мс: 103
 *
 * */


public class Task {
    public static void main(String[] args) {
        //ICounter counter = new MultiThreadUnSafeCounter();
        ICounter counter = new MultiThreadSafeCounter();

        System.out.println("Используем счётчик " + counter.getClass().getName());

        long start = System.currentTimeMillis();
        System.out.println("Начальное значение счётчика : " + counter.getValue());

        Runnable incTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.incCounter();
            }
        };

        Runnable decTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.decCounter();
            }
        };

        Thread t1 = new Thread(incTask);
        Thread t2 = new Thread(decTask);
        Thread t3 = new Thread(incTask);
        Thread t4 = new Thread(decTask);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Конечное значение счётчика : " + counter.getValue());
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Прошло времени, мс: " + elapsed);

    }
}
