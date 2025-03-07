package com;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;
import prepare.util.Utils;

public class ReentrantLockTest {

    public static class Counter implements Runnable {
        ReentrantLock lock = new ReentrantLock();
        private int count = 0;

        @Override
        public void run() {
            lock.lock();
            Utils.sleep(100);
            count++;
            try {
              validate();
            } catch (RuntimeException e){
            } finally {
              lock.unlock();
            }
        }

        private void validate() {
            if (count == 2) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * Test on the ReentrantLock usage with await() operation instead of object.wait() in critical section
     * // TODO: Fix the CounterTest#run method only
     */
    @Test
    public void testLock() throws InterruptedException {
        Counter count = new Counter();
        Thread thread1 = new Thread(count);
        Thread thread2 = new Thread(count);
        Thread thread3 = new Thread(count);

        thread1.start();
        thread2.start();
        thread3.start();
//
//        thread1.join();
//        thread2.join();
//        thread3.join();
//
        Thread.sleep(1000);

        assertEquals(3, count.count);

        System.out.println("Main exit: " + count.count);
    }
}
