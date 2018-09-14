package thread;

import static java.lang.Thread.sleep;

public class Thread8 implements Runnable {
    public void run() {
       try {
           System.out.println("run");
           sleep(100000000);
           System.out.println("end");
       } catch (InterruptedException e){
           e.printStackTrace();
       }

    }

    public static void main (String[] args){
        Thread8 t8 = new Thread8();
        Thread t = new Thread(t8);
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

    }
}
