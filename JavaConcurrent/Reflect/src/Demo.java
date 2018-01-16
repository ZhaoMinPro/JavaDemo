public class Demo {
    private static int a = 1;
    private static int b = 2;
    private static int c = 3;


    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        for (int i=0;i<100000000;i++){
            getTest1();
        }
        long b=System.currentTimeMillis();

        System.out.println("all:"+(b-a)+";  per:"+(b-a)/10000);
    }

    private static Test getTest1() {
        Class clazz=Test3.class;
        if (clazz == Test1.class) {
            return null;
        } else if (clazz == Test2.class) {
            return null;
        } else {
            return new Test3();
        }
    }

}
