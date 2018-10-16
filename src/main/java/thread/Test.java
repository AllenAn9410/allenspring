package thread;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Add a = new Add(lock);
        Subtract s = new Subtract(lock);
        Thread4 t4 = new Thread4(s);
        Thread3 t3 = new Thread3(a);
        Thread tt3 = new Thread(t3,"add");
        Thread tt4 = new Thread(t4,"s1 subtract");
        tt4.start();
        Thread ttt4 = new Thread(t4,"s2 subtract");
        ttt4.start();
        Thread.sleep(1000);
        tt3.start();
        tt3.join();

    }
}
