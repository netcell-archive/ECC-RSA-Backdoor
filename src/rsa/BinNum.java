
/* NGUYEN TUAN ANH - 20090131
 * TT1.K54.SAMI.HUST.HN.VN
 * DO AN 1 - SEMESTER 20112
 * HTTP://ANHNTCV.CO.CC
 */

package rsa;

import java.io.FileInputStream;
import java.util.Random;
import java.util.Scanner;

    
public class BinNum {
    
    /* BinNum is integer under an array of holders, each holder save value of a bit.
     * - The right most bit (the tail) is saved in the first holder, while
     * - The left  most bit (the head) is saved in the last  holder.
     */
    
    //The number
    private ALB num;
    
    /*-----------------------CONSTANT VALUES-----------------------*/
    
    final static public BinNum ZERO  = new BinNum("0");
    final static public BinNum ONE   = new BinNum("1");
    final static public BinNum TWO   = new BinNum("10");
    final static public BinNum THREE = new BinNum("11");
    final static public BinNum FOUR  = new BinNum("100");
    final static public BinNum THREE_POW_FOUR = new BinNum(Integer.toBinaryString(81));
    final static public BinNum BETA  = new BinNum("00111110010101000111001111110001111110011010000000101011011101111101001010101011011010111110110101100001101101010110000110110000101100011010001000001000010101111110010011100111010111111000010111011001101011010101011010100001111110010110110111010000001110100101001111101010101111000110100110011101000010010110010100110001111110110111101001011100010001000100110000101111000011110001100010100011100110000001010001011110001100110100011011100000000011011111000110100111101101110101100011011011111101111011110101000010100110101100101111011101011011011111100111001010001011101010111010111000100000010010101100001011100110101101100010011100111011000011100000111011000011010111111110100000100000000011000101101110001100000101111001010111101011110001000100110000101011101000101011100000110001101011110011001101011100110110100100110101001011100100100000010111110100011101111111010010011010101111100100011000010111111010010001111001011111110000100101111010110111110010111010110111010101000110101111100000100101100101001001000110100111110110001011001001011000111011011000010100101000011000110100010010000100000011010011110011111000110101001000100110010111000000101000000111111111001000100110110100101010100101110010100000000011001101110001000010110010100101000100011110000000101111001011101000100010001110101111111101100110011001011110010111000010011101111100011101010010010011110110100010010010111110111000011011110010110111101010011100100100000000100000111101111000000110011110010010000100110000000100110101100001101010101001101110110011001010010000101000100000001100101110011110001111001011111100101100011101110000111111010011010001111101110100110101100000111111100010101010000010010100101111110110011010110110011110001101101111111000100010010010010001010010100000011101111111101111111111111110001101010100100111111010101010101110100110101000001010001000010100100011111001111101010100010111011011001010010101101110100001111111100100010100111011110110100011000001011011011010110001111110111010101000010111100001101011111001001001011100011110011011100101011010000011101100111001110101011110110010101101001101101100100101011110001001100100101001100001011010001100101001011011110001010010010111000001011101111010001001000011011101111010111111010110101001110011000001100110011001011011111000000110101110000100101111111010100111010010100110101101101001000010100011001101011110000011110111001111101111111101100010010110101101111001110110001000000111010001000011001010111101010010010100000101000010101010110110101010111001101010100110011010101101001111101000000111101111010011001101001101101000111000111011100100110111100100111111100101010100111001010000010111111000001101011111101000111100110010110011101101011111101001100101100001101000111000100100010010011110011100100011000110010110110010010001110110010110101010001111110111001000000101101100110101110111001010101101010000110001110101001100100011010101001101101101100010000011100101100111101001010100010000111000101001101101000000001101101100001011011001000101111010011011011001101010000100110111011001001100100011010000110000001000000001000011001010001000000010010010101111110010110110100100100100110111001000111101011111001100011111001010001101101101111000001011101010100000100001010001001011001010110001100110100110101010010010011010100000010000110011100000011010001011101101110111011111001101010010010010001100001001000001000000111001010101110111110011010000010010110101110101011000111100000011001110111101011111101110011001101010101100111111111000100101010101111000111000000001011100110110010110000001100011010111000111111010110111111110000011001100111101101100001101010100111011111101100101001101001101011110101000100010010010101011000101011111001001011010110010110000000010000010000110100010101001101100001010010001000110011100100101111010001000010011110100010011001011011111001001100111011000011000010000110010011011101001101011100011010111110000101101101001110011000111001100101110111101001001100111110110001100111111111000110111101010011000001110001011101101111110100011111100000100001011101110110000101110111010000111100010001101110101011111001101100000000011000110000100100111011110001100001000010001000101001011110101110101111000111001110010001110100111101001000000010011000001001011100111111111000001111111110000000101100001010011001010011101101100101011001000100101010110100001000101001101010010010101101101111011010110101100100011100001001101111011111111101011000111111010000001101100011001000001001101100011111010101101010101000111111000111101100000001111011101011010100101101111010111011110101001111010100011111101001011101011010100000111001101000110110100110101100001000001101100011010111101111101011001010001111001001001100011000011000100001110000011110001101100111001010010010100100011010010010100100010110000101011101000001011010001000011010101110110100111110111011010010101110110110000101001011110111010111000101100100110000111101101110011010101011100010100110001011111101000010010001101011101000011010110111100100101001010110010101000101110000");
    
    /*----------------------CONVERSION TABLES----------------------*/
    
    //HEX table
    final static private String[][][][] bin2hex = { { { { "0" , "1" },
                                                        { "2" , "3" } },
                                                      { { "4" , "5" },
                                                        { "6" , "7" } } },
                                                    { { { "8" , "9" },
                                                        { "A" , "B" } },
                                                      { { "C" , "D" },
                                                        { "E" , "F" } } } };
    
    /*------------------------LOGIC TABLES------------------------*/
    
    /* XOR Table */
    final static private byte[] xor = { 0, 1, 1, 0 };
    
    /* NOT Table */
    final static private byte[] not = { 1, 0 };
    
    /* COMBINATION Table */
    final static private byte[][] combination = { { 0 , 1 },
                                                  { 2 , 3 } };
    
    /* ADD Tables */
        //Remainer
        final static private byte[][] addRem      = { { 0, 0, 0, 1 },
                                                      { 0, 1, 1, 1 } };
        //Output
        final static private byte[][] addOut      = { { 0, 1, 1, 0 },
                                                      { 1, 0, 0, 1 } };
        
    /* SUBTRACT Tables */
        //Remainer
        final static private byte[][] subRem      = { { 0, 1, 0, 0 },
                                                      { 1, 1, 0, 1 } };
        //Output
        final static private byte[][] subOut      = { { 0, 1, 1, 0 },
                                                      { 1, 0, 0, 1 } };
        
    /* SIGNED Tables */
        //Multiply
        final static private byte[]   mulSig      =   { 0, 1, 1, 0 };
        
        //Divide
        final static private byte[][] divSig      = { { 0, 0, 1, 1 },
                                                      { 0, 1, 1, 0 } };
   
    
    /*------------------------PERMUTATION------------------------*/
        
    /* Permutation */
    public BinNum permutation(){
        
        /* Input : k-bit integer
         * Output: this XOR k bits of beta
         * Choose k right most bits of beta
         */
        return this.XOR(this.beta());
    }
        
    /* Operator XOR */
    public BinNum XOR(BinNum that){
        
        /* Input : 2 integers a and b
         * Output: a XOR b
         */
        
        BinNum result;
        
        if (this.length()>=that.length()){
        
            result = new BinNum(this);
            
            for(int i=0;i<that.length();i++)
               result.set(i+this.length()-that.length(), 
                          xor[combination[this.get(i+this.length()-that.length())]
                                         [that.get(i)]
                             ]
                         );
            
            return result;
        }
        
        return that.XOR(this);
    }
    
    /* k bits of beta */
    public BinNum beta(){
        
        /* Input : k-bit integer
         * Output: k right most bits of beta
         *  The left  most bits of k is 1 so that eps 
         *  and e have the same ammount of meaningful bits
         *  The right most bits of k is 1 so e is odd
         */
        BinNum result = new BinNum(BETA);
        result.remove(0,5003-this.length());
        result.set(result.length()-1, (byte) 0);
        
        return result;
    }
    
    /*---------------------PERFECT SQUARE ROOT--------------------*/
    
    public BinNum perfectSquareRoot(){
        
        /* Input : k-bit integer
         * Output: 
         *         0                   IF this is NOT a perfect square root
         *         square root of this IF this is     a perfect square root
         * Method: Solve equation f(x) = x^2-S =0 using BISECTION METHOD
         */
        
        BinNum S = new BinNum(this).simple();
        
        /* 0 < b < x < a */
        BinNum a = new BinNum();
        BinNum b = new BinNum();
        
        int len = S.length();
        
        /* Choose of a and b:
         * notice: square root of 2^2k is 2^k
         * 2^2(k-1) < x^2 < 2^2(k+1)
         * 2^ (k-1) <  x  < 2^ (k+1)
         */
        
        for(int i=0; i<len/2+1; i++)
            a.insert((byte)0);
        a.insert((byte) 1);
        
        for(int i=0; i<len/2-1; i++)
            b.insert((byte)0);
        b.insert((byte) 1);
        
        /* The algorithm */
        BinNum c = a.add(b).divide(TWO)[1];
        
        int compareAMinusB = a.subtract(b).compareTo(ONE);
        int compareCSquare = c.multiply(c).compareTo(S);
        
        while (compareCSquare!=0&&compareAMinusB>0){
            
            if (compareCSquare>0)
                 a = new BinNum(c);
            else b = new BinNum(c);
            
            c = a.add(b).divide(TWO)[1];
            
            compareAMinusB = a.subtract(b).compareTo(ONE);
            compareCSquare = c.multiply(c).compareTo(S);
        }
        
        if (compareCSquare!=0) c = new BinNum(ZERO);
        
        return c;
    }
    
    /*-------------------------RANDOMIZER-------------------------*/
    
    /* Normal number randomizer */
    public BinNum rand(int length){
        
        /* Return a random length-bit number */
        
        Random random = new Random();
        
        /* Every bit is taken randomly in {0,1} */
        for(int i=0; i<length-1; i++)
            insert((byte)random.nextInt(2));
        
        /* Left most bit is 1
         * To make sure that there is no 0 bit at the head
         * (0 bit at the head is meaningless)
         */
        insert((byte)1);
        
        return this;
    }
    
    /* Normal number randomizer with boundary*/
    public BinNum rand(BinNum max){
        
        /* Return a random number smaller that max
         * NOTE: This random function will not provide a wide variant of number
         *       at theoretical level BUT wide enough for using purpose in this
         *       particular case.
         */
        
        Random random = new Random();
        
        BinNum Max = new BinNum(max).simple();
        
        for(int i=Max.length()-1; i>=1; i--){
            if (Max.get(i)==0) insert((byte)0);
            else insert((byte)random.nextInt(2));
        }
        return this;
    }
    
    /* Odd number randomizer */
    public BinNum randOdd(int length){
        
        /* Return a random length-bit odd number */
        
        Random random = new Random();
        
        /* Similar to rand() but the tail is 1
         * Which inicate that the number is
         * sum of 2^k (k>0, which are even) and
         *        2^0 which is odd
         * The result is an odd number
         */
        
        insert((byte)1);
        
        for(int i=1; i<length-1; i++)
            insert((byte)random.nextInt(2));
        
        insert((byte)1);
        
        return this;
    }
    
    /* Even number randomizer */
    public BinNum randEven(int length){
        
        /* Return a random length-bit even number */
        
        Random random = new Random();
        
        /* Similar to rand() but the tail is 0
         * Which inicate that the number is
         * sum of 2^k (k>0)
         * The result is an even number
         */
        
        insert((byte)0);
        
        for(int i=1; i<length-1; i++)
            insert((byte)random.nextInt(2));
        
        insert((byte)1);
        
        return this;
    }
    
    /* RSA.size-bit prime number randomizer */
    public BinNum randPrime(){
        
        /* Return a random RSA.size-bit prime number
         * Prime numbers are pre-generated by checking all the odd numbers
         * from 2^255+1 to 2^256-1 using Miller-Rabin algorithm (50 iterations)
         * and saved in a text file as: 1 number - 1 line.
         */
        
        Random random = new Random();
        
        String readNumber = "1";
        BinNum prime = new BinNum(ONE);
        
        try{
                FileInputStream is = new FileInputStream(RSA.primeFile);
                Scanner input = new Scanner(is);
                
                /* Take the number from a random position */
                int position = (int)random.nextInt(RSA.primeFileSize);
                int count=0;
                
                while(input.hasNextLine()&&count<=position)
                {
                    readNumber = input.nextLine();
                    count++;
                }
                is.close();
                
                prime = new BinNum(readNumber);
        }catch(Exception e){System.out.println(e.toString());}
        
        return prime;
    }    
    
    /* Pre-generated small primes */
    public static final int[] primes = { 503, 509, 521, 523, 541, 547, 557, 563, 569, 
                                         571, 577, 587, 593, 599, 601, 607, 613, 617, 
                                         619, 631, 641, 643, 647, 653, 659, 661, 673, 
                                         677, 683, 691, 701, 709, 719, 727, 733, 739, 
                                         743, 751, 757, 761, 769, 773, 787, 797, 809, 
                                         811, 821, 823, 827, 829, 839, 853, 857, 859, 
                                         863, 877, 881, 883, 887, 907, 911, 919, 929, 
                                         937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
    
    /* Small prime number randomizer */
    public BinNum randPrime2(){
        
        /* Return a random small prime number
         * Prime numbers are pre-generated saved in constant primes array above
         */
        
        Random random = new Random();
        
        return new BinNum(Integer.toBinaryString(primes[random.nextInt(primes.length)]));
    }
    
    /*-----------------------PRIMARITY TEST-----------------------*/
        
    public boolean primarityTestMillerRabin(int iterations){
        
        /* Input : Number to be tested and iterations
         * Output: True is the number is probably prime
         * 
         * Algorithm 4.24, page 139, Handbook of Applied Cryptography 
         * by A. Menezes, P. van Oorschot, and S. Vanstone, CRC Press, 1996 
         */
        
        BinNum n = new BinNum(this);
        n.simple();
        
        if (n.length()==1) return false;
        else if (n.length()==2) return true;
        else { 
            if (n.get(0) ==0) return false;
            else{
                
                /* Step 1:
                 * Write n - 1 = (2^s)*r
                 * with r is odd
                 */
                
                    /* n - 1 */
                    BinNum nMinusOne = n.subtract(ONE);

                    /* Find s:
                     * s is the smallest power of 2
                     */
                    int s = nMinusOne.getLowestSetBit();
                    
                    /* Find r:
                     * 1. r = 0
                     * 2. For each 2^k, r = r + 2^(k-s)
                     */
                    BinNum r = new BinNum(ZERO);
                    r.resizeHead(nMinusOne.length()-1);
                    
                    for(int i=s;i<nMinusOne.length();i++)
                        if (nMinusOne.get(i)==1)
                            r.set(i-s,(byte)1);
                
                /* For use to choose a random integer a: 2 < a < n-2
                 * - Choose a random integer a: 0 < a < n-4
                 * - a = a + 2
                 */
                BinNum nMinusFour = n.subtract(FOUR);
                
                /* Step 2: For i from 1 to number of iterations do the following
                 * 
                 */
                for (int i = 1; i <= iterations; ++i){
                    
                    /* Step 2.1: 
                     * Choose a random integer a: 2 < a < n-2
                     */
                    BinNum a = new BinNum().rand(nMinusFour).add(TWO);
                    
                    /* Step 2.2:
                     * Compute y = a^r mod n using Algorithm 2.143
                     */
                    BinNum y = a.modExp(r, n);
                    
                    /* Step 2.3:
                     * If y != 1 and y != n − 1
                     */
                    if (y.compareTo(ONE)!=0 && y.compareTo(nMinusOne)!=0){
                        
                        int j = 1;
                        
                        /* While j ≤ s − 1 and y != n − 1 */
                        while(j<=s-1 && y.compareTo(nMinusOne)!=0){
                            
                            /* Compute y = y^2 mod n */
                            y = y.multiply(y).divide(n)[0];
                            
                            /* If y = 1 then return“composite” */
                            if (y.compareTo(ONE)==0) return false;
                            
                            j++;
                        }
                        
                        /* If y  != n − 1 then return “composite” */
                        if (y.compareTo(nMinusOne)!=0) return false;
                        
                    }
                }
            }
        }
        
        return true;
        
    }
    
    public int getLowestSetBit(){
        return num.getLowestSetBit();
    }
        
    /*-------------------------COMPARISON-------------------------*/
        
    public int compareTo(BinNum that){
        
        /* Input : 2 integer a and b (in that order)
         * Output: 
         *          1 IF a > b
         *          0 IF a = b
         *         -1 IF a < b
         */
        
        BinNum A = new BinNum(this).simple();
        BinNum B = new BinNum(that).simple();
        
        if (A.length()>B.length())
            return 1;
        
        else if (A.length()<B.length())
            return -1;
        
        else
            for(int i=A.length()-1; i>=0; i--)
                if (A.get(i)>B.get(i)) return 1;
                else if (A.get(i)<B.get(i)) return -1;
        
        return 0;
    }
    
    /*---------------------ADDITION & SUBTRACTION-----------------*/
    
    public BinNum add(BinNum that){
        
        BinNum result = new BinNum();
        byte remainer=0;
        
        int minLength = Math.min(this.length(),that.length());
        int maxLength = Math.max(this.length(),that.length());
        
        /* ADD FROM 0 TO MINIMUM LENGTH INTERVAL */
        for(int i=0;i<minLength;i++){
            //Find the combination
            int com = combination[this.get(i)][that.get(i)];
            //Add
            result.insert(addOut[remainer][com]);
            //Find the remainer
            remainer    = addRem[remainer][com];
        }
        
        /*-ADD FROM MINIMUM TO MAXIMUM LENGTH INTERVAL-*/
        if (that.length()<maxLength){
        //THIS is longer
            if (remainer==1){
                for(int i=minLength; i<maxLength; i++){
                    //Find the combination
                    int com = combination[this.get(i)][0];
                    //add
                    result.insert(addOut[remainer][com]);
                    //Find the remainer
                    remainer    = addRem[remainer][com];
                }
            } else for(int i=minLength; i<maxLength; i++)
                    result.insert(addOut[remainer][combination[this.get(i)][0]]);
        } else {
        //THAT is longer
            if (remainer==1){
                for(int i=minLength; i<maxLength; i++){
                    //Find the combination
                    int com = combination[0][that.get(i)];
                    //add
                    result.insert(addOut[remainer][com]);
                    //Find the remainer
                    remainer    = addRem[remainer][com];
                }
            } else for(int i=minLength; i<maxLength; i++)
                    result.insert(addOut[remainer][combination[0][that.get(i)]]);
        }
        
        /*-ADD 1 TO THE HEAD IF REMAINER IS STILL 1-*/
        if (remainer==1) result.insert(remainer);
        
        return result;
    }
    
    public BinNum subtract(BinNum that){
        
        BinNum result = new BinNum();
        byte remainer=0;
        
        int minLength = Math.min(this.length(),that.length());
        int maxLength = Math.max(this.length(),that.length());
        
        for(int i=0;i<minLength;i++){
            //Find the combination
            int com = combination[this.get(i)][that.get(i)];
            //add
            result.insert(subOut[remainer][com]);
            //Find the remainer
            remainer    = subRem[remainer][com];
        }
        
        if (that.length()<maxLength){
        //THIS is longer
            if (remainer==1){
                for(int i=minLength; i<maxLength; i++){
                    //Find the combination
                    int com = combination[this.get(i)][0];
                    //add
                    result.insert(addOut[remainer][com]);
                    //Find the remainer
                    remainer    = subRem[remainer][com];
                }
            } else for(int i=minLength; i<maxLength; i++)
                    result.insert(subOut[remainer][combination[this.get(i)][0]]);
        } else {
        //THAT is longer
            if (remainer==1){
                for(int i=minLength; i<maxLength; i++){
                    //Find the combination
                    int com = combination[0][that.get(i)];
                    //add
                    result.insert(subOut[remainer][com]);
                    //Find the remainer
                    remainer         = subRem[remainer][com];
                }
            } else for(int i=minLength; i<maxLength; i++)
                    result.insert(subOut[remainer][combination[0][that.get(i)]]);
        }
        
        return result;
    }
    
    /*---------------MULTIPLICATION & DIVISION--------------------*/
    
    public BinNum multiply(BinNum that){
        
        BinNum M = new BinNum(that);
        
        BinNum U = new BinNum(ZERO).resizeHead(this.length()+that.length());
        
        for(int i=0;i<this.length();i++){
            if (this.get(i)==1)
                    U = U.add(M);
            M.insert(0,(byte)0);
        }
        
        return U;
        
    }
    
    public BinNum[] divide(BinNum that){
        
        /* Output: [Remainer,Quotient]
         */
        
        BinNum[] result = new BinNum[2];
        BinNum quotient = new BinNum();
        BinNum dividend = new BinNum(this);
        BinNum divisor = new BinNum(that);
        
        int compare = this.compareTo(that);
        
        if (compare==-1){
            dividend = new BinNum(this);
            quotient = new BinNum(ZERO);
        } else if (compare==0){
            dividend = new BinNum(ZERO);
            quotient = new BinNum(ONE);
        } else {
            
            dividend.simple();
            divisor.simple();
            
            int numberOfCount = dividend.length()-divisor.length()+1;
            
            divisor.resizeTail(dividend.length()-divisor.length());

            for(int i=0;i<numberOfCount;i++){
                if (dividend.compareTo(divisor)>=0) {
                    quotient.insert(0, (byte)1);
                    dividend = dividend.subtract(divisor);
                }
                else quotient.insert(0, (byte)0);
                divisor.shiftRight();
            }
        }
        
        result[0] = dividend.simple();
        result[1] = quotient.simple();
        
        return result;
    }
    
    public BinNum shiftRight(){
        num.shiftRight();
        return this;
    }
    
    /*----------------------------MODULO--------------------------*/
    
    /* Multiplicative exponentiation */
    public BinNum modExp(BinNum k, BinNum n){
        
        /* Input : Integer a, k, n
         * Output: a^k mod n
         * 
         * Algorithm 2.143, page 71, Handbook of Applied Cryptography 
         * by A. Menezes, P. van Oorschot, and S. Vanstone, CRC Press, 1996 
         */
        
        /* b = 1 */
        BinNum b = new BinNum(ONE);
        
        /* IF k = 0 THEN a^k mod n = 1*/
        if (k.compareTo(ZERO)==0) return b;
        
        /* A = a */
        BinNum A = new BinNum(this);
        
        /* IF k[0] = 1 THEN b = a */
        if (k.get(0)==1) b = new BinNum(this);
        
        for(int i=1;i<k.length();i++){
            
            /* A = A^2 */
            A = A.multiply(A).divide(n)[0];
            
            /* IF k[i] = 1 THEN b = A*b mod n */
            if (k.get(i)==1) b = A.multiply(b).divide(n)[0];
            
        }
        return b;
    }
    
    /* Greatest Common Divisor */
    public BinNum GCD(BinNum that){
        
        /* Input : Integer a, b | a>b
         * Output: GCD(a,b)
         */
        
        if (this.compareTo(that)<0) return that.GCD(this);
        
        BinNum x = new BinNum(this);
        BinNum y = new BinNum(that);
        
        while(y.compareTo(ZERO)!=0){
            BinNum r = x.divide(y)[0];
            x = new BinNum(y);
            y = new BinNum(r);
        }
        
        return x;
    }
    
    /* Multiplicative inverses */
    public BinNum inverseMod(BinNum that){
        
        /* Input : Integer a, b
         * Output: b^(-1) mod a
         * 
         * Algorithm 2.142, page 71, Handbook of Applied Cryptography 
         * by A. Menezes, P. van Oorschot, and S. Vanstone, CRC Press, 1996 
         */
        
        BinNum b = new BinNum(this);
        BinNum a = new BinNum(that);
        
        BinNum y2 = new BinNum(ZERO);
        BinNum y1 = new BinNum(ONE);
        
        BinNum r = new BinNum(ZERO);
        BinNum q = new BinNum(ZERO);
        BinNum y = new BinNum(ZERO);
        
        int length = y2.length();
        
        y.signing(length, (byte) 0);
        y2.signing(length, (byte) 0);
        y1.signing(length, (byte) 0);
        
        while(b.compareTo(ZERO)>0){
            
            r = a.divide(b)[0];
            
            if (r.compareTo(ZERO)==0) break;
                
            q = a.divide(b)[1];
                
            int localLength = Math.max ( Math.max(y2.length(),y1.length()) , Math.max(y.length(),q.length()+1) );

            if (localLength>length){
                length = localLength;
                y2.resizeSigned(length);
                y1.resizeSigned(length);
            } else localLength = length;

            q.signing(localLength-1, (byte) 0);

            y = y1.multiplySigned(q);

            if (y.length()>length){
                length = y.length();
                y2.resizeSigned(length);
                y1.resizeSigned(length);
            } else y.resizeSigned(length);

            y = y2.subtractSigned(y);

            if (y.length()>length){
                length = y.length();
                y1.resizeSigned(length);
            } else y.resizeSigned(length);

            a  = new BinNum(b);
            b  = new BinNum(r);
            y2 = new BinNum(y1);
            y1 = new BinNum(y);
        }
//        y = new BinNum(y2);
        if (y.get(y.length()-1)==1){
            y.remove(y.length()-1);
            y = that.subtract(y);
        } else y.remove(y.length()-1);
        
        return y;
    }
    
    /*---------------------SIGNED NUMBER HANDLER-------------------*/
    
    public BinNum signing(int length, int sign){
        resizeHead(length-length());
        insert((byte)sign);
        return this;
    }
    
    public BinNum resizeSigned(int length){
        byte sign = get(length()-1);
        remove(length()-1);
        resizeHead(length-1-length());
        insert((byte)sign);
        return this;
    }
    
    public BinNum subtractSigned(BinNum that){
        
        BinNum result;
        
        int length = this.length();
        
        /* This method calculate signed number
         * Input: this and that with the same length and an extra 1 bit at the head for sign
         */
        
        if (this.get(length-1)==0){
            
            BinNum a = new BinNum(this).remove(length-1);
            BinNum b = new BinNum(that).remove(length-1);
            
            if (that.get(length-1)==0){
                if (a.compareTo(b)>0) return this.subtract(that);
                else {
                    result = b.subtract(a);
                    if (result.compareTo(ZERO)==0) result.insert((byte)0);
                    else result.insert((byte)1);
                }
            } else {
                result = a.add(b);
                result.insert((byte)0);
            }
        } else return this.opposeSigned().subtractSigned(that.opposeSigned()).opposeSigned();
        return result;
    }
    
    public BinNum opposeSigned(){
        BinNum isZero = new BinNum(this).remove(this.length()-1);
        if (isZero.compareTo(ZERO)==0) {
            this.set(this.length()-1,(byte)0);
            return this;
        }
        else this.set(this.length()-1, not[this.get(this.length()-1)]);
        return this;
    }
    
    public BinNum[] divideSigned(BinNum that){
        
        BinNum[] result;
        
        int length = this.length();
        
        BinNum a = new BinNum(this).remove(length-1);
        BinNum b = new BinNum(that).remove(length-1);
        
        result = a.divide(b);
        
        result[0].simple();
        result[1].simple();
        
        int maxLength = Math.max(result[0].length(),result[1].length());
        
        if (maxLength<length-1) maxLength = length-1;
        
        if (result[0].length()<maxLength) result[0].resizeHead(maxLength-result[0].length());
        if (result[1].length()<maxLength) result[1].resizeHead(maxLength-result[1].length());
        
        int com = combination[this.get(this.length()-1)][that.get(this.length()-1)];
        
        if (result[0].compareTo(ZERO)==0) result[0].insert((byte)0);
        else result[0].insert(divSig[0][com]);
        if (result[1].compareTo(ZERO)==0) result[1].insert((byte)0);
        else result[1].insert(divSig[1][com]);
        
        return result;
    }
    
    public BinNum multiplySigned(BinNum that){
        BinNum result;
        
        int length = this.length();
        
        BinNum a = new BinNum(this).remove(length-1);
        BinNum b = new BinNum(that).remove(length-1);
        
        result = a.multiply(b);
        
        result.simple();
        
        if (result.length()<length-1) result.resizeHead(length-result.length()-1);
        
        if (result.compareTo(ZERO)==0) result.insert((byte)0);
        else result.insert(mulSig[combination[this.get(length-1)][that.get(length-1)]]);
        
        return result;
    }
    
    /*----------------------------RESIZER--------------------------*/
    
    /* EXTEND the number with 0 from head or tail */
    
    public BinNum resizeHead(int length){
        num.resizeHead(length);
        return this;
    }
    
    public BinNum resizeTail(int length){
        num.resizeTail(length);
        return this;
    }
    
    /*----------------------------PRINTERS-------------------------*/
    
    /* Binary string */
    @Override
    public String toString(){
        
        /* Return the binary string */
        
        String result = "";
        for(int i=this.length()-1;i>=0;i--){
            result = result+this.get(i);
        }
        
        return result;
    }
    
    /* Decimal number */
    public int toDec(){
        
        /* Return the decimal string */
        
        int x = 0;
        for(int i=0;i<this.length();i++)
            if (this.get(i)==1) x = x + (int)Math.pow(2,i);
        
        return x;
    }
    
    /*-----------------------------COPIERS-------------------------*/
    
    private void copy(ALB num){
        this.num = new ALB().addA(num);
    }
    
    private void copy(BinNum that){
        this.num = new ALB().addA(that.get());
    }
    
    /*---------------------------INITIALIZERS----------------------*/
    
    public BinNum(){
        this.num = new ALB();
    }
    
    public BinNum(BinNum that){
        copy(that);
    }
    
    public BinNum(String num){
        copy(new ALB().init(num));
    }
    
    /*---------------------------MANIPULATORS----------------------*/
    
    public BinNum simple(){
        num.simple();
        return this;
    }
    
    public BinNum remove(int i){
        num.remove(i);
        return this;
    }
    
    private BinNum remove(int s, int e){
        num.remove(s,e);
        return this;
    }

    public ALB get() {
        return num;
    }
    
    public byte get(int i) {
        return num.get(i);
    }
    
    public void set(int i, byte value){
        num.set(i,value);
    }
    
    public BinNum insert(byte value){
        num.add(value);
        return this;
    }
    
    public BinNum insert(int i, byte value){
        num.add(i,value);
        return this;
    }
           
    /*-------------------------INFORMATION GETTER-------------------*/
    
    public int length(){
        return num.size();
    }

}