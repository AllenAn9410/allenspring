package thread;

public class Thread2 implements Runnable {
    private Object object;
    Thread2 (Object object){
        this.object = object;
    }

    public void run() {
        synchronized ( object ){
            System.out.println("start to notify");
            object.notify();
            System.out.println("finish to notify");
        }
        for(int i=0;i<5;i++){
            System.out.println("notify : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
