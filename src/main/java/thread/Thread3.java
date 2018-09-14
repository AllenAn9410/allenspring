package thread;

public class Thread3 implements Runnable {
    int i = 10;
     synchronized public void run() {
        --i;
        System.out.println(Thread.currentThread().getName() + " : " + i);
    }

    public static void main(String[] args){
        Thread3 t3 = new Thread3();
        Thread a = new Thread(t3,"a");
        Thread b = new Thread(t3,"b");
        Thread c = new Thread(t3,"c");
        Thread d = new Thread(t3,"d");
        a.start();
        b.start();
        c.start();
        d.start();
    }

}
