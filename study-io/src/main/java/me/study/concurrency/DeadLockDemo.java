package me.study.concurrency;

public class DeadLockDemo {

    public static final String A = "A";

    public static final String B = "B";

    public static void main(String[] args) {

        new DeadLockDemo().deadLock();

    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (B) {
                        System.out.println("thread 1");
                    }
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("thread 2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

}
