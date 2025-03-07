package com;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

/**
 * Explorer wait/notify mechanism for threads
 */
public class TestThreadWaitNotify {

  AtomicInteger counter = new AtomicInteger(0);

  /**
   * Fill in the gaps and insert instructions to make code executable
   */
  @Test
  public void testThread() throws InterruptedException {
    Thread thread1 = createThread(() -> {
      try {
        synchronized (counter) {
          counter.wait();
          counter.incrementAndGet();
          counter.notify();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread thread2 = createThread(() -> {
      try {
        synchronized (counter) {
          counter.wait();
          counter.incrementAndGet();
          counter.notify();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    thread1.start();
    thread2.start();

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      for (int i = 0; i < 100; i++) {
        System.out.println();
      }
    }

    // ensure WAITING
    // ensure WAITING

    assertEquals(thread1.getState(), Thread.State.WAITING);
    assertEquals(thread2.getState(), Thread.State.WAITING);

    // TODO: notify thread
    // TODO: notify thread

    synchronized (counter){
      counter.notify();
    }

    // delay
    Thread.sleep(1000);
    assertEquals(counter.get(), 2);
  }

  private Thread createThread() {
    final Thread thread = new Thread();
    return thread;
  }

  private Thread createThread(Runnable runnable) {
    final Thread thread = new Thread(runnable);
    return thread;
  }

}