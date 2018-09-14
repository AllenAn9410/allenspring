package thread;

public class Thread2 extends Thread {

    private int i = 10;

    public synchronized void run(){
        --i;
        System.out.println(this);
        System.out.println(this.currentThread().getName() + " : " + i);
    }

    public static void main( String[] args){
        Thread2 t2 = new Thread2();
        Thread a = new Thread(t2,"a");
        Thread b = new Thread(t2,"b");
        Thread c = new Thread(t2,"c");
        Thread d = new Thread(t2,"d");
        a.start();
        b.start();
        c.start();
        d.start();

    }


}
