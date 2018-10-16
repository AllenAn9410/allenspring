package join;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        ThreadB threadB = new ThreadB();
//        ThreadA threadA = new ThreadA(threadB);
//        new Thread(threadA).start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new ThreadC(threadB)).start();

        Thread2 t2 = new Thread2();
        Thread tt2 = new Thread(t2);

        Thread1 t1 = new Thread1(tt2);
        Thread tt1 = new Thread(t1);

        tt1.start();
        tt2.start();
        tt2.join(2000);
        System.out.println("main end " + System.currentTimeMillis());
    }
}
