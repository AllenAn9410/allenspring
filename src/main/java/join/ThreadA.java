package join;

public class ThreadA implements Runnable {

    private Thread b;

    public ThreadA(ThreadB b){
        this.b = new Thread(b);
    }
    public void run() {
        try{
            synchronized (b){
                b.start();
                b.join();
                for(int i=0;i<Integer.MAX_VALUE;i++){
                    String newString = new String();
                }
//                Thread.sleep(6000);

            }
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
    }
}
