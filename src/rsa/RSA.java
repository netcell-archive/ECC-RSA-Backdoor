
/* NGUYEN TUAN ANH - 20090131
 * TT1.K54.SAMI.HUST.HN.VN
 * DO AN 1 - SEMESTER 20112
 * HTTP://ANHNTCV.CO.CC
 */

package rsa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSA {
    
    public static BinNum[] key;
    
    /* Pre-generated primes constants */
    // Prime size
    public static final int size=256;
    // File saved re-generated primes
    public static final String primeFile = "prime"+size+".txt";
    // Number of primes pre-generated
    public static final int    primeFileSize = 1817;
    //Files
    public static final String publicKeyFile  = "PublicKey.txt";
    public static final String privateKeyFile = "PrivateKey.txt";
    public static final String extraINFFile   = "ExtraINF.txt";
    public static final String plainTextFile  = "PlainText.txt";
    public static final String cipherTextFile = "CipherText.txt";
    //Folders
    public static final String sourcesFolder = "Sources/";
    public static final String resultFolder  = "Result/";
    
    
    public static void Attack(){
        
        BinNum publicKeyPermutation = new BinNum();
        BinNum publicKey = new BinNum();
        BinNum commonKey = new BinNum();
        
        String publicKeyString = "";
        
        FileInputStream is = null;
        System.out.println(" RSA BACKDOOR CONNECTOR");
        System.out.println(" ----------------------");
        notExistFolderHandle(sourcesFolder);
        notExistFolderHandle(resultFolder);
        System.out.println(" ----------------------");
        System.out.print  ("/>Reading public key...");
        try {
            is = new FileInputStream(sourcesFolder+publicKeyFile);
            Scanner input = new Scanner(is);
            commonKey = new BinNum(input.nextLine());
            publicKeyString = input.nextLine();
            publicKey            = new BinNum(publicKeyString);
            publicKeyPermutation = new BinNum(publicKeyString).permutation();
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+publicKeyFile+" not found! Please copy "+publicKeyFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
//                System.out.print  (" n      :");
//                System.out.println(commonKey.toHex());
//                System.out.print  (" e      :");
//                System.out.println(publicKey.toHex());
//                System.out.print  (" epsilon:");
//                System.out.println(publicKeyPermutation.toHex());
            } catch (IOException ex) {
                System.out.println("Invalid "+publicKeyFile+"... Exiting");
                System.exit(0);
            }
        }
        System.out.println(" ----------------------");
        existFileHandle(sourcesFolder+privateKeyFile);
        System.out.println(" ----------------------");
        
        System.out.print  ("/>Connecting to backdoor...");
        BinNum privateKey = HSDAttack(commonKey,publicKeyPermutation,publicKey);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(resultFolder+privateKeyFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(commonKey.toString());
            output.print  (privateKey.toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("  Backdoor connected!");
        System.out.println("/>Private key has been saved to folder"+resultFolder+". Backdoor disconnected.\n\n");
        
    }
    
    public static void Decrypt(){
        
        BinNum cipherText = new BinNum();
        BinNum privateKey = new BinNum();
        BinNum commonKey = new BinNum();
        
        FileInputStream is = null;
        
        
        System.out.println(" RSA DECRYPTION PROCESS");
        System.out.println(" ----------------------");
        
        notExistFolderHandle(sourcesFolder);
        notExistFolderHandle(resultFolder);
        System.out.println(" ----------------------");
        System.out.print  ("/>Reading cipher text...");
        try {
            is = new FileInputStream(sourcesFolder+cipherTextFile);
            Scanner input = new Scanner(is);
            cipherText = new BinNum(input.nextLine());
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+cipherTextFile+" not found! Please copy "+cipherTextFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
            } catch (IOException ex) {
                System.out.println("Invalid "+cipherTextFile+"... Exiting");
                System.exit(0);
            }
        }
        System.out.print  ("/>Reading private key...");
        try {
            is = new FileInputStream(sourcesFolder+privateKeyFile);
            Scanner input = new Scanner(is);
            commonKey = new BinNum(input.nextLine());
            privateKey = new BinNum(input.nextLine());
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+privateKeyFile+" not found! Please copy "+privateKeyFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
//                System.out.print("  n:");
//                System.out.println(commonKey.toHex());
//                System.out.print("  e:");
//                System.out.println(privateKey.toHex());
            } catch (IOException ex) {
                System.out.println("Invalid "+privateKeyFile+"... Exiting");
                System.exit(0);
            }
        }
        
        System.out.println(" ----------------------");
        existFileHandle(resultFolder+plainTextFile);
        System.out.println(" ----------------------");
        
        System.out.print  ("/>Decrypting cipher text...");
        BinNum cipher = cipherText.modExp(privateKey, commonKey);
        
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(resultFolder+plainTextFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(cipher.toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        System.out.println("Cipher text decrypted.");
        System.out.println(" ----------------------");
        System.out.println("/>Plain text has been saved to folder "+resultFolder+". You may send "+plainTextFile+" to your partner.\n\n");
    }
    
    public static void Encrypt(){
        
        BinNum plainText = new BinNum();
        BinNum publicKey = new BinNum();
        BinNum commonKey = new BinNum();
        
        FileInputStream is = null;
        
        
        System.out.println(" RSA ENCRYPTION PROCESS");
        System.out.println(" ----------------------");
        
        notExistFolderHandle(sourcesFolder);
        notExistFolderHandle(resultFolder);
        System.out.println(" ----------------------");
        System.out.print  ("/>Reading plain text...");
        try {
            is = new FileInputStream(sourcesFolder+plainTextFile);
            Scanner input = new Scanner(is);
            plainText = new BinNum(input.nextLine());
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+plainTextFile+" not found! Please copy "+plainTextFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
//                System.out.println(plainText.toHex());
            } catch (IOException ex) {
                System.out.println("Invalid "+plainTextFile+"... Exiting");
                System.exit(0);
            }
        }
        System.out.print  ("/>Reading public key...");
        try {
            is = new FileInputStream(sourcesFolder+publicKeyFile);
            Scanner input = new Scanner(is);
            commonKey = new BinNum(input.nextLine());
            publicKey = new BinNum(input.nextLine());
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+publicKeyFile+" not found! Please copy "+publicKeyFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
//                System.out.print("  n:");
//                System.out.println(commonKey.toHex());
//                System.out.print("  e:");
//                System.out.println(publicKey.toHex());
            } catch (IOException ex) {
                System.out.println("Invalid "+publicKeyFile+"... Exiting");
                System.exit(0);
            }
        }
        
        System.out.println(" ----------------------");
        existFileHandle(resultFolder+cipherTextFile);
        System.out.println(" ----------------------");
        
        System.out.print  ("/>Encrypting plain text...");
        BinNum cipher = plainText.modExp(publicKey, commonKey);
        
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(resultFolder+cipherTextFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(cipher.toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        System.out.println("Plain text encrypted.");
        System.out.println(" ----------------------");
        System.out.println("/>Cipher text has been saved to "+resultFolder+". You may send "+cipherTextFile+" to your partner.\n\n");
    }
    
    public static void existFileHandle(String filename){
        System.out.print  ("/>Checking "+filename+"...");
        File file  = new File(filename);
        if(file.exists()){
            System.out.print  (" File does exist! Deleting...");
            if(file.delete()) System.out.println(" Deleted! Continuing...");
            else {
                System.out.println(" Deletion failed! Exiting...");
                System.exit(0);
            }
        } else System.out.println(" File does not exist! Continuing...");
    }
    
    public static void notExistFolderHandle(String foldername){
        System.out.print  ("/>Checking "+foldername+"...");
        File file  = new File(foldername);
        if(!file.exists()){
            System.out.print  (" Folder does not exist! Creating...");
            if(file.mkdir()) System.out.println(" Create! Continuing...");
            else {
                System.out.println(" Creation failed! Exiting...");
                System.exit(0);
            }
        } else System.out.println(" Folder does exist! Continuing...");
    }
    
    public static void KeyGen(){
        System.out.println(" KEY GENERATION PROCESS");
        System.out.println(" ----------------------");
        System.out.println("/>System is loading... ");
        File file = new File(primeFile);
        if (!file.exists()){
            System.out.println("/>ERROR: "+primeFile+" not found! Please reinstall this application. Exiting...     ");
            System.exit(0);
        }
        System.out.println("/>System is online...  ");
        System.out.println("/>Generating RSA key...");
        key = HSDKeyGenerator();
        System.out.println(" ----------------------");
        System.out.println(" RSA key generated     ");
//        System.out.println("  n = "+key[0].toHex());
//        System.out.println("  e = "+key[2].toHex());
//        System.out.println("  d = "+key[1].toHex());
        System.out.println(" ----------------------");
//        System.out.println("/>Extra informations:  ");
//        System.out.println("  p = "+key[4].toHex());
//        System.out.println("  q = "+key[5].toHex());
//        System.out.println(" ----------------------");
        
        notExistFolderHandle(resultFolder);
        existFileHandle(resultFolder+publicKeyFile);
        existFileHandle(resultFolder+privateKeyFile);
        existFileHandle(resultFolder+extraINFFile);
        System.out.println(" ----------------------");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(resultFolder+publicKeyFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(key[0].toString());
            output.print  (key[2].toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } System.out.println("/>Public  key        has been saved to "+publicKeyFile+".");
        try {
            out = new FileOutputStream(resultFolder+privateKeyFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(key[0].toString());
            output.print  (key[1].toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } System.out.println("/>Private key        has been saved to "+privateKeyFile+".");
        try {
            out = new FileOutputStream(resultFolder+extraINFFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(key[4].toString());
            output.print  (key[5].toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } System.out.println("/>Extra informations has been saved to "+extraINFFile+".");
        
        System.out.println("\n/>Key generation succeeds. Your keys has been saved to folder "+resultFolder);
        System.out.println("You may send "+publicKeyFile+" to your partner.\n");
    }
    
    public static void Protect(){
        
        BinNum publicKey = new BinNum();
        BinNum privateKey = new BinNum();
        BinNum commonKey = new BinNum();
        BinNum p = new BinNum();
        BinNum q = new BinNum();
        
        FileInputStream is = null;
        
        
        System.out.println(" RSA PROTECTION PROCESS");
        System.out.println(" ----------------------");
        
        notExistFolderHandle(sourcesFolder);
        notExistFolderHandle(resultFolder);
        System.out.println(" ----------------------");
        System.out.print  ("/>Reading public key...");
        try {
            is = new FileInputStream(sourcesFolder+publicKeyFile);
            Scanner input = new Scanner(is);
            commonKey = new BinNum(input.nextLine());
            publicKey = new BinNum(input.nextLine());
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+publicKeyFile+" not found! Please copy "+publicKeyFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
            } catch (IOException ex) {
                System.out.println("Invalid "+publicKeyFile+"... Exiting");
                System.exit(0);
            }
        }
        System.out.print  ("/>Reading private key...");
        try {
            is = new FileInputStream(sourcesFolder+privateKeyFile);
            Scanner input = new Scanner(is);
            commonKey = new BinNum(input.nextLine());
            privateKey = new BinNum(input.nextLine());
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+privateKeyFile+" not found! Please copy "+privateKeyFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
            } catch (IOException ex) {
                System.out.println("Invalid "+privateKeyFile+"... Exiting");
                System.exit(0);
            }
        }
        System.out.print  ("/>Reading primes fractorization...");
        try {
            is = new FileInputStream(sourcesFolder+extraINFFile);
            Scanner input = new Scanner(is);
            p = new BinNum(input.nextLine());
            q = new BinNum(input.nextLine());
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(" "+extraINFFile+" not found! Please copy "+extraINFFile+" to "+sourcesFolder+" and run this application again. Exiting...");
            System.exit(0);
        } finally {
            try {
                is.close();
                System.out.println(" Done!");
            } catch (IOException ex) {
                System.out.println("Invalid "+extraINFFile+"... Exiting");
                System.exit(0);
            }
        }
        key = HSDProtect(p,q,publicKey,privateKey);
        notExistFolderHandle(resultFolder);
        existFileHandle(resultFolder+publicKeyFile);
        existFileHandle(resultFolder+privateKeyFile);
        existFileHandle(resultFolder+extraINFFile);
        System.out.println(" ----------------------");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(resultFolder+publicKeyFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(commonKey.toString());
            output.print  (key[0].toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } System.out.println("/>Public  key        has been saved to "+publicKeyFile+".");
        try {
            out = new FileOutputStream(resultFolder+privateKeyFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(commonKey.toString());
            output.print  (key[1].toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } System.out.println("/>Private key        has been saved to "+privateKeyFile+".");
        try {
            out = new FileOutputStream(resultFolder+extraINFFile, false);
            PrintWriter output = new PrintWriter(out);
            output.println(p.toString());
            output.print  (q.toString());
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } System.out.println("/>Extra informations has been saved to "+extraINFFile+".");
        
        System.out.println("\n/>Key generation succeeds. Your keys has been saved to folder "+resultFolder);
        System.out.println("You may send "+publicKeyFile+" to your partner.\n");
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("\n ----------------------");
        System.out.println("    RSA CRYPT SYSTEM   ");
        System.out.println(" COMMAND LINE INTERFACE");
        System.out.println(" ----------------------");
        System.out.println("  HTTP://ANHNTCV.CO.CC ");
        System.out.println("  NGUYEN TUAN ANH 2012 ");
        System.out.println(" ----------------------");
        
//        KeyGen();
        
//        Encrypt();
        
//        Decrypt();
        
//        Attack();
        
        Protect();
        
//        key = HSDKeyGenerator();
//        BinNum[] result = HSDProtect(key[4],key[5],key[2], key[1]);
//        System.out.println(result[1].toString());
//        BinNum plain = new BinNum("1001");
//        System.out.println("Done");
//        BinNum cipher = plain.modExp(result[0], key[0]);
//        plain = cipher.modExp(result[1], key[0]);
//        System.out.print(plain.toString());
        
    }
    
    public static BinNum[] HSDProtect(BinNum p, BinNum q, BinNum e, BinNum d){
        Boolean loop = true;
        BinNum result[] = new BinNum[2];
        BinNum phi = (p.subtract(BinNum.ONE)).multiply(q.subtract(BinNum.ONE));
        
        while(loop){
            loop=false;
            while(true){
                BinNum beta = new BinNum().randEven(e.length());
                beta.set(beta.length()-1, (byte) 0);
                result[0]=e.XOR(beta);

                if (result[0].compareTo(phi)>=0) loop = true;
                if (loop == true) break;

                result[1]=result[0].inverseMod(phi);
                if (result[1].length()<d.length()) loop = true;
                break;
            }
        }
        return result;
    }
    
    public static BinNum HSDAttack(BinNum n, BinNum e, BinNum Le){
        
        BinNum result = new BinNum();
        BinNum eps = new BinNum(e);
        ConFrac q = new ConFrac();
        ArrayList<BinFrac> r = new ArrayList<BinFrac>();
        
        boolean loop = true;
        
        for(int i=0; loop; i++) {
            
            /* Calculate qi, ri */
            if(i==0) {
                // q0 = floor(e/n)
                q.add(e.divide(n)[1]);
                // q0 = e/n - q0
                r.add(new BinFrac(e,n).subtract(q.get(0)));
            } else {
                BinFrac rPre = r.get(i-1);
                // qi = floor(1/r(i-1))
                q.add(rPre.inverse().intUnderestimate());
                // ri = 1/r(i-1) - qi
                r.add(rPre.inverse().subtract(q.get(i)));
            }
            
            /* Step 4
             * k/dg = <q0...qi>     if i is odd
             * k/dg = <q0...q(i+1)> if i is even
             */
            ConFrac kOdg = new ConFrac();
            kOdg.addAll(q);
            if(i%2==0) kOdg.set(i, q.get(i).add(BinNum.ONE));
            
            BinNum k   = kOdg.getNumerator();
            
            /* Step 5
             * Guess edg=e·dg
             */
            BinNum edg = kOdg.getDenominator().multiply(e);
            
            /* Step 6.1
             * Guess (p−1)(q−1) = floor(edg/k)
             */
            BinNum phi = edg.divide(k)[1];
            
            BinNum[] HalfpPlusq = new BinNum[2];
            BinNum HalfpMinusq = new BinNum();
            
            /* Step 6.2
             * if (p−1)(q−1) = 0, i++ restart the loop
             */
            if(phi.compareTo(BinNum.ZERO)!=0){
                /* Step 8.1
                 * Guess (p+q)/2 = (n - phi + 1)/2
                 */
                BinNum pPlusq = n.subtract(phi).add(BinNum.ONE);
                HalfpPlusq = pPlusq.divide(BinNum.TWO);
                /* Step 8.2
                 * if (p+q)/2 = 0, i++ restart the loop
                 */
                if(pPlusq.compareTo(BinNum.ZERO)!=0) {
                    /* Step 9.1
                     * Guess [(p-q)/2]^2 = [(p-q)/2]^2 - n;
                     */
                    HalfpMinusq = HalfpPlusq[1].multiply(HalfpPlusq[1]);
                    if(HalfpMinusq.compareTo(n)>0){
                        HalfpMinusq = HalfpMinusq.subtract(n);
                        HalfpMinusq = HalfpMinusq.perfectSquareRoot();
                        if(HalfpMinusq.compareTo(BinNum.ZERO)!=0) loop = false;
                    } 
                    else {
                        System.out.print("Larger than n");
                        loop = false;
                    } 
                }
            }
            
            BinNum pM1 =HalfpPlusq[1].add(HalfpMinusq).subtract(BinNum.ONE);
            BinNum qM1 =HalfpPlusq[1].subtract(HalfpMinusq).subtract(BinNum.ONE);
            
            result = Le.inverseMod(pM1.multiply(qM1));

        }
        return result;
    }
    
    public static BinNum[] HSDKeyGenerator(){
        
        /* result: n, e, d, φ(n), p, q */
        BinNum[] result = new BinNum[7];
        
        BinNum p   = new BinNum();
        BinNum q   = new BinNum();
        BinNum phi = new BinNum();
        
        /* CONDITIONAL LOOP AND NON-STOP-CASE PREVENTION */
        boolean restart = true;
        
        while(restart){
            
            restart = false;
            /* Pick 2 random prime p and q */
            p = new BinNum().randPrime();
            q = new BinNum().randPrime();

            while(p.compareTo(q)==0) 
                q = new BinNum().randPrime();

            /* Calculate n = p.q */
            result[0] = p.multiply(q).simple();

            /* Calculate φ(n) = (p-1).(q-1) */
            phi = p.subtract(BinNum.ONE).multiply(q.subtract(BinNum.ONE));

            BinNum gcd = new BinNum(BinNum.TWO);
            
            int i = 0;

            /* until gcd(e, φ(n)) = 1 */
            while (gcd.compareTo(BinNum.ONE)!=0){
                
                if(i==15) restart = true;
                
                /* PREVENT TOO LONG LOOP */
                if(restart==true) break;i++;
                
                BinNum delta = new BinNum().randOdd(result[0].length()/4-1);
                
                /* CONDITION: δ < (1/3)*n^0.25 */
                BinNum deltaSquare = delta.multiply(delta);
                if(deltaSquare.multiply(deltaSquare).multiply(BinNum.THREE_POW_FOUR).compareTo(result[0])>=0) restart=true;
                if(restart==true) break;

                /* Pick a random odd δ such that gcd(δ, φ(n)) = 1 */
                int k = 0;
                while (gcd.compareTo(BinNum.ONE)!=0&&!restart){
                    
                    /* PREVENT TOO LONG LOOP */
                    if(k==15) restart = true; k++;
                    
                    delta = delta.add(BinNum.TWO);
                    gcd     = delta.GCD(phi);
                    
                } if(restart==true) break;
                
                /* CONDITION: δ < (1/3)*n^0.25 */
                deltaSquare = delta.multiply(delta);
                if(deltaSquare.multiply(deltaSquare).multiply(BinNum.THREE_POW_FOUR).compareTo(result[0])>=0) restart=true;
                if(restart==true) break;
                
                /* Compute ε := δ−1 mod φ(n) */
                BinNum eps = delta.inverseMod(phi).simple();
                
                /* CONDITION: ε < n */
                if(eps.compareTo(/*result[0]*/phi)>0) restart=true;
                if(restart==true) break;
                
                /* CONDITION: ε*δ < n */
                if(eps.multiply(delta).compareTo(result[0])<0) restart=true;
                if(restart==true) break;
                
                result[6]=new BinNum(eps);

                /* e := πβ(ε) */
                result[2]    = new BinNum(eps).permutation();
                
                /* CONDITION: e < n */
                if(result[2].compareTo(/*result[0]*/phi)>0) restart=true;
                if(restart==true) break;

                gcd = result[2].GCD(phi);
            }
        
        }
        
        /* d := e−1 mod φ(n) */
        result[1] = result[2].inverseMod(phi);
        
        /* Extra Informations */
        result[3] = phi;
        result[4] = p;
        result[5] = q;
        
        return result;
        
    }
        
}
