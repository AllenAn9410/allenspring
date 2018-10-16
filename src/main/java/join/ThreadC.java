package join;

public class ThreadC implements Runnable{

    private ThreadB threadB;
    ThreadC(ThreadB threadB){
        this.threadB = threadB;
    }

    public void run() {
        threadB.bService();
    }
}
