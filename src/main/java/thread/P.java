package thread;

public class P {
    private String lock;
    private MyStack myStack;
    public P(String lock){
        this.lock = lock;
    }
    public P(MyStack myStack){
        this.myStack = myStack;
    }

    public void setValue(){
        try{
            synchronized (lock){
                while(!ValueObject.value.equals("")){
                    System.out.println("producter " + Thread.currentThread().getName() +  " Waiting");
                    lock.wait();
                }
                System.out.println("producter " + Thread.currentThread().getName() + " Running " );
                String value = System.currentTimeMillis()+ "_" + System.nanoTime();
                ValueObject.value = value;
                lock.notify();
//                System.out.println();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void pushService(){
        myStack.push();
    }

}
