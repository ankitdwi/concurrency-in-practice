package Practice.MultiThreading;

import java.util.Scanner;

class WaitNotifyProcessor {
    public void producer() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running.....");
            wait();
            System.out.println("Resumed.");
        }
    }

    public void consumer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this){
            System.out.println("waiting for return key. ");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
            Thread.sleep(5000);
        }
    }
}

public class WaitAndNotify {
    private static WaitNotifyProcessor notifyProcessor = new WaitNotifyProcessor();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    notifyProcessor.producer();
                } catch (InterruptedException e) {

                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    notifyProcessor.consumer();
                } catch (InterruptedException e) {

                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
