package tools.pool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Do implements Runnable{
    StringBuffer s = null;
    //StringBuilder s = null;
    public Do(StringBuffer s){
        this.s = s;
    }


    @Override
    public void run() {
        try {
            Date now = new Date();
            Thread.currentThread().sleep(500);
            String res = Thread.currentThread().getName() + " , "+  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(now);
            String temp = res + "\n";
            s.append(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
