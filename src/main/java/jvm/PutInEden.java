package jvm;

public class PutInEden {
    private static final int MB = 1024 * 1024;
    public static void main(String[] args){
        byte[] bytes1, bytes2, bytes3, bytes4;//定义变量
        bytes1 = new byte[2 * MB];
        bytes2 = new byte[2 * MB];
        bytes3 = new byte[2 * MB];
        bytes4 = new byte[4 * MB];
        //System.gc();
    }

}
