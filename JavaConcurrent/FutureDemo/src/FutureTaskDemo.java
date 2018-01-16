import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) {
        ExecutorService executor= Executors.newScheduledThreadPool(3);
        MyFutureTask myFutureTask=new MyFutureTask(new CallableDemo.MyTask());
        executor.submit(myFutureTask);
        executor.shutdown();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            myFutureTask.get();//
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    static class MyFutureTask extends FutureTask<Integer>{

        public MyFutureTask(Callable<Integer> callable) {
            super(callable);
        }
    }
}
