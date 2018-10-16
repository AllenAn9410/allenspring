package join;

import org.apache.poi.ss.formula.functions.T;

public class Thread1 implements Runnable {
    private Thread thread;

    Thread1(Thread thread){
        this.thread = thread;
    }


    public void run() {
        synchronized (thread){
            try{
                System.out.println( "begin 1 ThreadName " + Thread.currentThread().getName()
                        +"  "+ System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println( "end 1 ThreadName " + Thread.currentThread().getName()
                        +"  "+ System.currentTimeMillis());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
