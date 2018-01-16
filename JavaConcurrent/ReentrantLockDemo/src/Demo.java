import javafx.util.Pair;

public class Demo {
    public static void main(String[] args) {
        MyService service=new MyService();
        Thread threadA=new ThreadA(service);
        Thread threadB=new ThreadB(service);
        threadA.start();
        threadB.start();
    }
}

class ThreadA extends Thread{
    private MyService service;

    public ThreadA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            service.set();
        }
    }
}

class ThreadB extends Thread{
    private MyService service;

    public ThreadB(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while(true){
            service.get();
        }
    }
}
