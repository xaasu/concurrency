package unit3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectThreadPoolDemo {
  public static class MyTask implements Runnable {

    @Override
    public void run() {
      System.out
          .println(System.currentTimeMillis() + ":Thread id" + Thread.currentThread().getId());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    MyTask task = new MyTask();
    ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
        new RejectedExecutionHandler() {
          @Override
          public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + " is discard");
          }
        });
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      es.submit(task);
      Thread.sleep(10);
    }
  }
}
