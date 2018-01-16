public class WaitLongDemo {
    public static void main(String[] args) {
        Object lock=new Object();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("son start wait");
                    try {
                        lock.wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("son end wait");
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock){
            System.out.println("father get the lock");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            lock.notify();
            System.out.println("father lose the lock");
        }

    }


}
