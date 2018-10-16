package join;

public class Thread2 implements Runnable {

    synchronized public void run() {
        try {
            System.out.println( "begin 2 ThreadName " + Thread.currentThread().getName()
                    +"  "+ System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println( "end 2 ThreadName " + Thread.currentThread().getName()
                    +"  "+ System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
