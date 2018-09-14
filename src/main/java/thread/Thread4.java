package thread;

import static java.lang.Thread.sleep;

public class Thread4 implements Runnable {
    int i = 0;
    synchronized public void run(){
        boolean isT = true;
        while(isT){
            System.out.println(Thread.currentThread().getName() + " : " + i++);
            try {
                sleep(100);
                if ( i==10 ){
                    isT = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Thread4 t4 = new Thread4();
        Thread a = new Thread(t4,"a");
        Thread b = new Thread(t4,"b");
        a.start();
        b.start();
        try {
            a.yield();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            b.yield();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
