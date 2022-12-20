import java.io.*;
import java.util.Random;


public class OTP {


// Generates a pad of random characters

public static String generate_pad(int length){
    String pad = "";
    char randomCharacter; 
    Random r = new Random();

    for( int i = 0; i < length; i++ ){
        randomCharacter = (char) r.nextInt(256);
        pad += randomCharacter;
        }

    return pad;
    }

// Encrypts the input text and returns the pad and ciphertext

public static String encrypt(String text, String pad){

    String ciphertext = "";
    char xoredValue;
    for( int i = 0; i < text.length(); i++ ){
        xoredValue = (char) (text.charAt(i) ^ pad.charAt(i));
        ciphertext += xoredValue;
        }

    return ciphertext;
    }

// Decrypts the ciphertext using the provided pad

public static String decrypt( String pad, String ciphertext){
    String plaintext = "";
    char xoredValue;

    for( int i = 0; i < pad.length(); i++ ){
        xoredValue = (char) (ciphertext.charAt(i) ^ pad.charAt(i));
        plaintext += xoredValue;
        }

    return plaintext;
    }

}