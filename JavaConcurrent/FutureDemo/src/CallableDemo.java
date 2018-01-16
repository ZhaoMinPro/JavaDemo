import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import java.util.List;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        System.out.println("main run...");
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(new MyTask());
        executor.submit(new MyTask());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        List<Runnable> shutdownList = executor.shutdownNow();
//        System.out.println("shut down size:" + shutdownList.size());

        executor.shutdown();

        if (!executor.isTerminated()){
            try {
                System.out.println("await termination");
                boolean isTer=executor.awaitTermination(2000,TimeUnit.MILLISECONDS);//阻塞一会然后返回isTerminated
                System.out.println("await ister:"+isTer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("is shut down:"+executor.isShutdown()+", is terminated:"+executor.isTerminated());


        System.out.println(future.isDone());
        if (future.isCancelled()) {
            System.out.println("myTask be cancelled");
        } else {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.isDone());
    }

    static class MyTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("myTask start...");
            int num = 0;
            for (; num < 20; num++) {
                Thread.sleep(100);
                System.out.println("myTask run...");
            }
            System.out.println("myTask end...");
            return num;
        }
    }
}
