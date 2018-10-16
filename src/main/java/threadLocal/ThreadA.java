package threadLocal;

public class ThreadA implements Runnable {

    public void run() {
        for(int i=0;i<100;i++){
            Tools.tl.set("ThreadA " + (i+1));
            System.out.println("ThreadA get Value = " + Tools.tl.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
