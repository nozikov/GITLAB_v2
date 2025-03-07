package com;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;

/**
 * Quest: fill in missing gaps (TODO or commented lines)
 */
public class TestSingleton {

    static TestSingleton instance;

    public TestSingleton(){}

    private static TestSingleton getInstance(){
        if (instance == null) {
            // TODO: complete
          instance = new TestSingleton();
          return instance;
        }
        return instance;
    }

    /**
     * Fill in the gaps and insert instructions to make code executable
     *
     * @throws InterruptedException
     */
    @Test
    public void testThread() throws InterruptedException {
        final AtomicReference<TestSingleton> instance = new AtomicReference<>();

        Thread thread1 = createThread(() -> {
            // TODO: replace with working code
            instance.compareAndSet(null, getInstance()); // TODO
        });

        thread1.start();

//        thread1.join(); // TODO
      Thread.currentThread().join(500);

        assertEquals(TestSingleton.getInstance(), instance.get());
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