package Practice.MultiThreading;

import java.util.concurrent.*;

// How to timeout a thread
public class InterviewQuestion {
    public void withThreadPools() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // 1. start the thread
        threadPool.submit(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                // perform task
            }
        });


        // 2. timeout for 10 mins


        // 3. stop the thread
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
        }
    }

    public void withFuture(){
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // 1. start the thread
        Future<?> future =  threadPool.submit(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                // perform task
            }
        });


        // 2. timeout for 10 mins
        try {
            future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {

        }catch (TimeoutException e) {
            // 3. stop the thread
            future.cancel(true);
        }


    }

    public void usingThreadsOnly() {
        Thread t1 = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                // perform task
            }
        });

        t1.start();

        // 2. timeout for 10 mins
        try {
            Thread.sleep(10*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. stop the thread
        t1.interrupt();
    }
}
