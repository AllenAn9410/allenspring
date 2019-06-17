package tools.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(300));
        StringBuffer s = new StringBuffer();
        //StringBuilder s = new StringBuilder();
        Thread t = null;
        Do d = new Do(s);
        for(int i=0;i<100;i++){
            t = new Thread(d,"thread" + i)  ;
            executor.execute(t);
        }
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

        System.out.println(s.toString());
    }

}
