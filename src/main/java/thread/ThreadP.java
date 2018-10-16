package thread;

public class ThreadP implements Runnable {
    private P p;
    public ThreadP(P p){
        this.p = p;
    }

    public void run() {
        while(true){
           // p.setValue();
            p.pushService();
        }
    }
}
