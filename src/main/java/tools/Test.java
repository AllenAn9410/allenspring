package tools;

import javax.swing.plaf.metal.MetalLookAndFeel;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){
        int[] a = {1,1,4,6,7,9,9,11,13,15};
        List<Integer> al = new ArrayList<Integer>();
        int temp = 0;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length-1;j++){
                temp = a[i] + a[j];
                al.add(temp);
            }
        }
        for(int b:al){
            System.out.println(b);
        }
        int c = a.length/2;
    }

}
