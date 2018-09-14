package thread;

public class Thread5 implements Runnable {

    public Thread5(){
        System.out.println(" Thread5 bagin");
        System.out.println(Thread.currentThread().getName());
        System.out.println(" Thread5 end");
    }
    public void run() {
        System.out.println("run bagin");
        System.out.println(Thread.currentThread().getName());
        System.out.println(" run end");
    }
    public static void main(String[] args){
        Thread5 t5 = new Thread5();
        Thread t = new Thread(t5);
        t.setName("a");
        t.start();

    }
}
