import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPerformanceDemo {
    static int NUMS = 10000000;

    public static void main(String[] args) {
        AtomicPerformanceDemo demo = new AtomicPerformanceDemo();
        long a = System.currentTimeMillis();
        for (int i = 0; i < NUMS; i++) {
            demo.addAtoInt();
        }
        long b = System.currentTimeMillis();
        for (int i = 0; i < NUMS; i++) {
            demo.addSyncInt();
        }
        long c = System.currentTimeMillis();
        for (int i = 0; i < NUMS; i++) {
            demo.addVolInt();
        }
        long d = System.currentTimeMillis();
        for (int i = 0; i < NUMS; i++) {
            demo.addNorInt();
        }
        long e = System.currentTimeMillis();
        System.out.println("ato:" + (b - a));
        System.out.println("sync:" + (c - b));
        System.out.println("vol:" + (d - c));
        System.out.println("nor:" + (e - d));
        System.out.println(demo.atoInt.intValue());
        System.out.println(demo.syncInt);
    }

    private AtomicInteger atoInt = new AtomicInteger(0);

    private int syncInt = 0;
    private volatile int volInt = 0;
    private int norInt=0;

    private synchronized int addSyncInt() {
        return syncInt++;
    }

    private int addAtoInt() {
        return atoInt.addAndGet(1);
    }

    private int addVolInt() {
        return volInt++;
    }

    private int addNorInt(){
        return norInt++;
    }
}
