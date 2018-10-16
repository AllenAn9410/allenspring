package thread;

public class C {
    private String lock;
    private MyStack myStack;

    public C(MyStack myStack){
        this.myStack = myStack;
    }

    public C(String lock){
        this.lock = lock;
    }

    public void getValue(){
        try{
            synchronized (lock){
                while(ValueObject.value.equals("")){
                    System.out.println("consumer " + Thread.currentThread().getName() + " Waiting");
                    lock.wait();
                }
                System.out.println("consumer " + Thread.currentThread().getName() + " Running ");
                ValueObject.value = "";
                lock.notify();
            }
        } catch ( InterruptedException e){
            e.printStackTrace();
        }
    }

    public void popService(){
        System.out.println("pop = " + myStack.pop());
    }


}
