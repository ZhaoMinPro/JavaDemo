import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private ReentrantLock lock=new ReentrantLock();

    public void set(){
        try {
            lock.lock();
            System.out.println("print $$");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get(){
        try{
            lock.lock();
            System.out.println("print ##");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
