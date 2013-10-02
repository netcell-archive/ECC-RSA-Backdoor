/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.util.ArrayList;

/**
 *
 * @author tebaomang
 */
public class ConFrac extends ArrayList<BinNum>{

    public BinFrac getValue() {
        this.trimToSize();
        if(this.size()==1) return new BinFrac(this.get(0),BinNum.ONE);
        BinFrac value = new BinFrac(this.get(this.size()-1),BinNum.ONE);
        for(int i=this.size()-2; i>=0; i--){
            value = value.inverse();
            value = value.add(this.get(i));
        }
        return value;
    }
    
    public BinNum getNumerator(){
        return this.getValue().getNumerator();
    }
    
    public BinNum getDenominator(){
        return this.getValue().getDenominator();
    }
    
}
