import java.io.*;
import java.util.*;

/**
 * This program will encrypt the input file and output as decrypt
 * output file or decrypt the input file and output as encrypt file
 * The cipher will be based on the letter and substitute of corresponding
 * letters
 * White spaces are preserved and non-alphabetic characters are unchanged
 * @author Furkan Karabulut
 */
public class Cipher {
    public static void main(String[] args) {
        //Input file
        String fileName = args[0];
        //Output file
        String outputFile = args[1];

        try {
            // open the file to scan
            FileInputStream fis = new FileInputStream(fileName);
            Scanner in = new Scanner(fis);
            // open the output file and print
            FileOutputStream fos = new FileOutputStream(outputFile);
            PrintStream out = new PrintStream(fos);

        } catch (FileNotFoundException e) {
            System.out.print("Cannot create output file.");
            System.exit(1);
        }
    }

     /**
      * This method returns true if the cipher is not null and
      * contains exactly 26 letters
      * one for each letter of alphabet
      * Otherwise returns false.
      * @param cipher is char array
      * @return true cipher is not null and contains 26 letters otherwise
      * is false.
      */
    public static boolean isValidCipher (char[] cipher) {
        if(cipher.length != 26) {
            return false;
        } else {
            for (int i = 0; i < cipher.length; i++) {
                if (cipher[i] == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method process the work of the main idea
     * if encrypt is true, encrypts the lines in the input file
     * using the cipher and outputs them to the output file
     *
     * if encrypt is false, decrypts the lines in the input file
     * using the cipher and outputs them to the output file
     *
     * @param encrypt the boolean value will tell if encrypts or decrypts
     * @param in will scan the input file
     * @param out will out into the output file
     * @param cipher is the char array
     * @throws IllegalArgumentException with message Invalid Parameter, if in is null,
     * out is null and cipher is invalid
     */
    public static void processFile(boolean encrypt, Scanner in, PrintWriter out, char[] cipher) {
        if(encrypt == true) {

        }
    }

    /**
     * Return string containing encrypted line
     * @param line process will be working on the given line
     * @param cipher is the char array
     * @return string line which encrypted line
     * @throws IllegalArgumentException with message Invalid Parameter, if line is null
     * or cipher will be invalid
     */
    public static String encryptLine(String line, char[] cipher) {
        String s = "";
        for (int i = 0; i < line.length(); i++) {
            s += line.charAt(i);
        }
        return s;
    }

    /**
     * Return string containing decrypted line
     * @param line process will be working on the given line
     * @param cipher is the char array
     * @return string line whihc the decrypted line
     */
    public static String decryptLine(String line, char[] cipher) {
        String s = "";
        for (int i = 0; i < line.length(); i++) {
            s += line.charAt(i);
        }
        return s;
    }
}
