package thread;

public class Thread1 implements Runnable {
    private Object object;
    Thread1(Object object){
        this.object = object;
    }

    public void run() {
        try{
            synchronized (object){
                System.out.println("prepare to wait");
                for(int i=0;i<10;i++){
                    System.out.println(i);
                    if( i == 5 ){
                        object.wait();
                    }
                    Thread.sleep(1000);
                }
                System.out.println("finish to wait");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
