/* NGUYEN TUAN ANH - 20090131
 * TT1.K54.SAMI.HUST.HN.VN
 * DO AN 1 - SEMESTER 20112
 * HTTP://ANHNTCV.CO.CC
 */

package rsa;

import java.util.ArrayList;

public class ALB extends ArrayList<Byte> {
    
    /* An array of byte was created to be the number holder
     * in the earliest installations of the algorithm.
     * Later, to change the holder to an array list,
     * this class was created and hold the number instead.
     * This was a really dumb installation since the problem
     * can simply be solve with BinNum extending ArrayList<Byte>.
     * Anyway, since that does not really matter that much,
     * no further change will be applied to this part.
     */
    
    final static private byte[] stringToByte = {0,0,0,0,0,0,0,0,0,0,
                                                0,0,0,0,0,0,0,0,0,0,
                                                0,0,0,0,0,0,0,0,0,0,
                                                0,0,0,0,0,0,0,0,0,0,
                                                0,0,0,0,0,0,0,0,0,1};
    
    public int getLowestSetBit(){
        int count = 0;
        for(int i=0;i<size();i++){
            if(get(i)==1) return count;
            else count++;
        }
        return -1;
    }
    
    public ALB init(int x){
        clear();
        add((byte)x);
        trimToSize();
        return this;
    }
    
    public ALB init(byte[] num){
        clear();
        for(int i=num.length-1;i>=0;i--)
            add(num[i]);
        return this;
    }
    
    public ALB init(String num){
        clear();
        for(int i=num.length()-1;i>=0;i--){
            add(stringToByte[(int)num.charAt(i)]);
        }
        return this;
    }
    
    public ALB addA(ALB that){
        addAll(that);
        return this;
    }
    
    public void simple(){
        for(int i=size()-1;this.get(i)==0&&i>0;i--){
            this.remove(i);
        }
        this.trimToSize();
    }
    
    public void remove(int s, int e){
        for(int i=s;i<e;i++){
            remove(s);
        }
    }
    
    public void replace(int i, ALB num){
        for(int j=i;j<size();j++){
            set(j,num.get(j-i));
        }
    }
    
    public void resizeHead(int length){
        //insert 0 to the head
        for(int i=0;i<length;i++) add((byte)0);
    }
    
    public void resizeTail(int length){
        for(int i=0;i<length;i++) add(0,(byte)0);
    }
    
    public void shiftRight(){
        add(get(0));
        remove(0);
    }
    
    public void arithmeticShiftRight(){
        add(get(0));
        set(1,get(0));
        remove(0);
    }
    
    public void shiftLeft(){
        add(0,get(size()-1));
        remove(size()-1);
    }
    
    public void shiftLeft(int s){
        for(int i=0; i<s; i++)
            shiftLeft();
    }
}


