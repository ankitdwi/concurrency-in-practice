package Practice.MultiThreading;

import java.util.LinkedList;
import java.util.Random;

class ProdConProcessor {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {

                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {

                while (list.size() == 0) {
                    lock.wait();
                }

                System.out.println("List size is :" + list.size());
                int value = list.removeFirst();
                System.out.println("value is: " + value);
                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }
}

public class ProducerConsumerWaitNotify {
    private static ProdConProcessor prodConProcessor = new ProdConProcessor();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                prodConProcessor.producer();
            } catch (InterruptedException e) {

            }
        });


        Thread t2 = new Thread(() -> {
            try {
                prodConProcessor.consumer();
            } catch (InterruptedException e) {

            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}

