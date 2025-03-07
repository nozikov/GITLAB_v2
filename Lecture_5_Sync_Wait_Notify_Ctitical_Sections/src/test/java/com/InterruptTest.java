package com;

import static org.junit.Assert.assertEquals;

import com.mycompany.prepare.utils.Utils;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;

// TODO: fix the test using only volatile
public class InterruptTest {
    static volatile  boolean flag = true;

    private boolean exec() {
        while (flag) {
            int counter = 0;
            counter++;
        }

        return true;
    }

    @Test
    public void testSync() {
        AtomicReference<Boolean> ref = new AtomicReference<>();
        ref.set(false);

        new Thread(() -> ref.set(exec())).start();

        new Thread(() -> {
            int counter = 0;
            while (true) {
                counter++;
                if (flag)
                    flag = false;
            }
        }).start();

        Utils.sleep(2000);

        assertEquals(true, ref.get());
    }

}
