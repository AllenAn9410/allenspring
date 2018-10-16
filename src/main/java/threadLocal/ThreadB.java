package threadLocal;

public class ThreadB implements Runnable {
    public void run() {
        for(int i=0;i<100;i++){
            Tools.tl.set("ThreadB" + (i+1));
            System.out.println("ThreadB get Value = " + Tools.tl.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
