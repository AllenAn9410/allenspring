package thread;

public class Thread3 implements Runnable {
    private Add a ;
    Thread3(Add a){
        this.a = a;
    }

    public void run() {
        a.add();
    }
}
