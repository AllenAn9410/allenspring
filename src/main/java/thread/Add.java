package thread;

public class Add {
    private String lock;
    public Add(String lock){
        this.lock = lock;
    }

    public void add(){
        synchronized (lock){
            ValueObject.list.add("anything");
            System.out.println(" list added ");
            lock.notifyAll();
        }
    }

}
