package thread;

public class Thread7 implements Runnable {
    public void run() {

    }

    public static void main(String[] arg){
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }

}
