package thread;

public class TestPS {
    public static void main(String[] args){
//        String lock = "";
//        P p = new P(lock);
//        C c = new C(lock);
//        ThreadP[] tp = new ThreadP[2];
//        ThreadC[] tc = new ThreadC[2];
//        for(int i=0;i<2;i++){
//            tp[i] = new ThreadP(p);
//            Thread t1 = new Thread(tp[i]);
//            t1.setName("productor " + ( i + 1 ));
//            tc[i] = new ThreadC(c);
//            Thread t2 = new Thread(tc[i]);
//            t2.setName("consumer " + ( i + 1));
//            t1.start();
//            t2.start();
//        }
        MyStack myStack = new MyStack();
        P p = new P(myStack);
        C c = new C(myStack);
//        new Thread(new ThreadP(p)).start();
//        new Thread(new ThreadC(c),"11").start();
//        new Thread(new ThreadC(c),"22").start();
//        new Thread(new ThreadC(c),"33").start();
//        new Thread(new ThreadC(c),"44").start();
//        new Thread(new ThreadC(c),"55").start();
        new Thread(new ThreadP(p),"1111").start();
        new Thread(new ThreadP(p),"2222").start();
        new Thread(new ThreadP(p),"3333").start();
        new Thread(new ThreadP(p),"4444").start();
        new Thread(new ThreadP(p),"5555").start();
        new Thread(new ThreadC(c),"6666").start();


    }
}
