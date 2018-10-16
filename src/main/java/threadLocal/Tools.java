package threadLocal;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tools {
    public static ThreadLocal<String> tl = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        for(int i=0;i<100;i++){
            Tools.tl.set("main" +(i+1));
            System.out.println("Main get Value = " + Tools.tl.get());
            Thread.sleep(200);
        }
        System.out.println();
        Lock lock = new ReentrantLock();
    }
}
