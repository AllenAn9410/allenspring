package thread;

import static java.lang.Thread.sleep;

public class Thread6 implements Runnable{
    public void run() {
       try {
           for(int i=0;i<25;i++) {
               if(Thread.interrupted()){
                   System.out.println("break");
                   throw new InterruptedException();
               }
               sleep(100);
               System.out.println(i);
           }
           System.out.println("run end");
       } catch ( InterruptedException e){
           e.printStackTrace();
       }
    }

    public static void main(String[] args){
        Thread6 t6 = new Thread6();
        Thread t = new Thread(t6,"a");
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

    }
}
