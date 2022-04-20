package Practice.MultiThreading;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class PriceComparator {
    ExecutorService threadPool = Executors.newFixedThreadPool(4);

    // using completable future
    private Set<Integer> getPricesAsyncWay(int productId) throws ExecutionException, InterruptedException, TimeoutException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new AsyncTask("url1", productId, prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new AsyncTask("url2", productId, prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new AsyncTask("url3", productId, prices));

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        allTasks.get(3, TimeUnit.SECONDS);

        return prices;
    }

    // using Executor Service
    private Set<Integer> getPrices(int productId) throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch latch = new CountDownLatch(3);

        threadPool.submit((new Task("url1", productId, prices, latch)));
        threadPool.submit((new Task("url2", productId, prices, latch)));
        threadPool.submit((new Task("url3", productId, prices, latch)));

        // wait for timeout
       latch.await(3, TimeUnit.SECONDS);

        return prices;
    }


    private class AsyncTask implements Runnable {
        private String url;
        private int productId;
        private Set<Integer> prices;

        public AsyncTask(String url, int produvtId, Set<Integer> prices) {
            this.url = url;
            this.productId = produvtId;
            this.prices = prices;
        }

        public void run() {
            int price = 0;
            // make http call

            prices.add(price);
        }
    }

    private class Task implements Runnable {
        private String url;
        private int productId;
        private Set<Integer> prices;
        private CountDownLatch latch;

        public Task(String url, int produvtId, Set<Integer> prices, CountDownLatch latch) {
            this.url = url;
            this.productId = produvtId;
            this.prices = prices;
            this.latch = latch;
        }

        public void run() {
            int price = 0;
            // make http call

            prices.add(price);
            latch.countDown();
        }
    }
}
