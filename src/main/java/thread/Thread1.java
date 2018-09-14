package thread;

public class Thread1 extends Thread{
    private String name;
    int i = 0;
    public Thread1( String name ){
        this.name = name;
    }

    public void run(){

        while( true ){
            System.out.println( name + " : " + i++);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Thread1 t1 = new Thread1("t1");
        // Thread1 t2 = new Thread1("t2");
        t1.start();
        //  t2.start();
        try {
            Thread.sleep(2000);
            sleep(10000);
            System.out.println("1111");
            // t1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
