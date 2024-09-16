package io.kit.limitting;

import java.util.concurrent.atomic.AtomicInteger;

public class LimitLock {
    int limit = 1;
    private final AtomicInteger countDownLatch;

    public LimitLock(int limit) {
        if (limit > this.limit) {
            this.limit = limit;
        }
        countDownLatch = new AtomicInteger(this.limit);
    }

    public void lock() {
        while (true) {
            if (countDownLatch.get() > 0) {
                synchronized (countDownLatch) {
                    if (countDownLatch.get() > 0) {
                        countDownLatch.decrementAndGet();
                        break;
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //do nothing
            }
        }
    }

    public void unlock() {
        if (countDownLatch.get() < this.limit) {
            synchronized (countDownLatch) {
                if (countDownLatch.get() < this.limit) {
                    countDownLatch.addAndGet(1);
                }
            }
        }
    }
}