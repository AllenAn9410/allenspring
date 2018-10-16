package thread;

public class ThreadC implements Runnable{
    private C c;
    public ThreadC(C c){
        this.c = c;
    }
    public void run() {
        while(true){
          //  c.getValue();
            c.popService();
        }
    }
}
