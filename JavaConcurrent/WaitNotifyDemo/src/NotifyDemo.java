import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class NotifyDemo {
    protected static AtomicInteger cakeNum = new AtomicInteger(0);
    protected static AtomicBoolean isHaveTheCake =new AtomicBoolean(false);
    protected static Object cakeLock = new Object();

    public static void main(String[] args) {
        for (int i=1;i<6;i++){
            Thread thread=new GetCakeThread();
            thread.setName("Waiter"+i);
            thread.start();
        }
        new MakeCakeThread().start();
    }


}


class GetCakeThread extends Thread {


    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (NotifyDemo.cakeLock) {
            System.out.println(name + " is waiting for cake");
            try {
                NotifyDemo.cakeLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " got the cake" + NotifyDemo.cakeNum.intValue());
            System.out.println("");
            NotifyDemo.isHaveTheCake.set(false);
        }
    }
}

class MakeCakeThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (NotifyDemo.isHaveTheCake.get()==true){
                i--;
                continue;
            }
            makeCake(i);
            NotifyDemo.isHaveTheCake.set(true);
        }
    }

    private void makeCake(int i) {
        synchronized (NotifyDemo.cakeLock) {
            System.out.println("start make cake"+(i+1));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NotifyDemo.cakeNum.addAndGet(1);
            System.out.println("made a cake"+ NotifyDemo.cakeNum.intValue());
            NotifyDemo.cakeLock.notify();
            System.out.println("wait someone to get cake"+ NotifyDemo.cakeNum.intValue());
        }
    }
}
