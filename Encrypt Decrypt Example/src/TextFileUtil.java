import java.io.*;
import java.util.Scanner;
public class TextFileUtil implements  EncryptDecrypt{
    //I understood that we need to cipher all English Alphabet and left other characters unchanged
    public void encrypt(String inputFileName, String key) {
        Scanner inputScanner = null;
        try {
            inputScanner=new Scanner(new FileInputStream(inputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String plainText="";
        while(inputScanner.hasNextLine()){
            plainText+=inputScanner.nextLine();
            //I will add this line because of some complex input possibilities
            plainText+="\n";
        }
        //I need to find the length of the plainText only consisting of the letters
        int letterLength=0;
        for(int i=0;i<plainText.length();i++){
            for(int x=0;x<26;x++){
                if((plainText.charAt(i)>='a' && plainText.charAt(i)<='z')|| (plainText.charAt(i)>='A' && plainText.charAt(i)<='Z')){
                    letterLength++;
                    break;
                }
            }
        }
        //now we need key to be exactly that long
        int index=0;
        int decrease=key.length();
        for(int i=0;i<letterLength-decrease;i++){
            if(index==key.length())
                index=0;
            key+=key.charAt(index);
            index++;
        }
        //now we will do the encryption using Vinegere Ciphers Algoritm
        String writeThis="";
        int x=0;
        for(int i=0;i<plainText.length();i++){
            if((plainText.charAt(i)>='a' && plainText.charAt(i)<='z')){
                key=key.toLowerCase();
                writeThis+=(char)('a'+(plainText.charAt(i)-'a'+key.charAt(x)-'a')%26);
                x++;
            }
            else if((plainText.charAt(i)>='A' && plainText.charAt(i)<='Z')){
                key=key.toUpperCase();
                writeThis+=(char)('A'+(plainText.charAt(i)-'A'+key.charAt(x)-'A')%26);
                x++;
            }
            else{
                writeThis+=(char)(plainText.charAt(i));
            }
        }
        //time to write
        String outputFile=inputFileName+".encr";
        PrintWriter outputStream = null;
        try {
            outputStream=new PrintWriter(new FileOutputStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outputStream.print(writeThis);
        outputStream.close();
    }
    public void decrypt(String inputFileName, String key, String outputFileName) {
        Scanner inputScanner = null;
        try {
            inputScanner=new Scanner(new FileInputStream(inputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String plainText="";
        while(inputScanner.hasNextLine()){
            plainText+=inputScanner.nextLine();
            plainText+="\n";
        }
        //I need to find the length of the plainText only consisting of the letters
        int letterLength=0;
        for(int i=0;i<plainText.length();i++){
            for(int x=0;x<26;x++){
                if((plainText.charAt(i)>='a' && plainText.charAt(i)<='z')|| (plainText.charAt(i)>='A' && plainText.charAt(i)<='Z')){
                    letterLength++;
                    break;
                }
            }
        }
        //now we need key to be exactly that long
        int index=0;
        int decrease=key.length();
        for(int i=0;i<letterLength-decrease;i++){
            if(index==key.length())
                index=0;
            key+=key.charAt(index);
            index++;
        }
        //now we will do the decryption using Vinegere Ciphers Algoritm
        String writeThis="";
        int x=0;
        int toplam=0;
        for(int i=0;i<plainText.length();i++){
            if((plainText.charAt(i)>='a' && plainText.charAt(i)<='z')){
                key=key.toLowerCase();
                toplam=((plainText.charAt(i)-'a')-(key.charAt(x)-'a'));
                if(toplam<0){
                    toplam+=26;
                }
                writeThis+=(char)('a'+(toplam)%26);
                x++;
            }
            else if((plainText.charAt(i)>='A' && plainText.charAt(i)<='Z')){
                key=key.toUpperCase();
                toplam=((plainText.charAt(i)-'A')-(key.charAt(x)-'A'));
                if(toplam<0){
                    toplam+=26;
                }
                writeThis+=(char)('A'+(toplam)%26);
                x++;
            }
            else{
                writeThis+=(char)(plainText.charAt(i));
            }
        }
        //time to write
        PrintWriter outputStream = null;
        try {
            outputStream=new PrintWriter(new FileOutputStream(outputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outputStream.print(writeThis);
        outputStream.close();
    }
    public static  void main(String[]args){
        TextFileUtil obj=new TextFileUtil();
        obj.encrypt("deneme","Lemon");
        obj.decrypt("deneme.encr","Lemon","deneme.out");
    }
}
