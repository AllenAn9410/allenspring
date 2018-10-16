package thread;

public class Thread4 implements Runnable {
    private Subtract s;
    public Thread4(Subtract s){
        this.s = s;
    }

    public void run() {
        s.subtract();
    }
}
