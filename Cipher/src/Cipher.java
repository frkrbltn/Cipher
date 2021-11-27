import java.io.*;
import java.nio.file.*;
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
        // Encrypt or Decrypt command
        String operation = args[0];
        boolean encrypt = false;
        if (operation.equals("-e")) {
            encrypt = true;
        } else if (operation.equals("-d")) {
            encrypt = false;
        }
        //Input file
        String fileName = args[1];
        //Output file
        String outputFile = args[2];
        //Cipher file
        String cipherFile = args[3];
        // Cipher array
        char[] cipher;

        // if there are no four arguments exists
        if (operation.equals(null) && fileName.equals(null) &&
                outputFile.equals(null) && cipherFile.equals(null)) {
            System.out.println("Usage: java -cp bin Cipher {-e|-d} infile outfile cipherfile");
            System.exit(1);
        }
        // if the operations are not expected values exists.
        if (!operation.equals("-e") || !operation.equals("-d")) {
            System.out.println("Usage: java -cp bin Cipher {-e|-d} infile outfile cipherfile");
            System.exit(1);
        }
        //If the input file on the command line cannot be accessed exists
        // to do that I need to open filePath
        Path inputFilePath = Path.of(fileName);
        if (!Files.exists(inputFilePath)) {
            System.out.println("Unable to access input file: test-files/in.txt");
            System.exit(1);
        }
        //If the cipher file on the command line cannot be accessed exists
        Path cipherFilePath = Path.of(cipherFile);
        if (!Files.exists(cipherFilePath)) {
            System.out.println("Unable to access cipher file: test-files/cipher.txt");
            System.exit(1);
        }

        try {
            // open the file to scan
            FileInputStream fis = new FileInputStream(fileName);
            Scanner in = new Scanner(fis);
            while (in.hasNextLine()) {
                String lineInput = in.nextLine();
            }

            //Open the the cipher file and read it
            FileInputStream cip = new FileInputStream(cipherFile);
            Scanner scnr = new Scanner(cip);

            String line = scnr.nextLine();
            cipher = new char[line.length()];
            for (int i = 0; i < line.length(); i++) {
                cipher[i] = line.charAt(i);
            }
            //To check every elements in the array are different
            //If it is not exists with a message
            for (int i = 0; i < cipher.length; i++) {
                for (int j = 0; j < cipher.length; j++) {
                    if (cipher[i] == cipher[j] && i != j) {
                        System.out.println("Invalid cipher file");
                        System.exit(1);
                    }
                }
            }
            //If the first line of the cipher file does not contain exactly 26 lowercase letters exists
            if (cipher.length != 26) {
                System.out.println("Invalid cipher file");
                System.exit(1);
            }
            // open the output file and print
            FileOutputStream fos = new FileOutputStream(outputFile);
            PrintWriter out = new PrintWriter(fos);

            Path outFilePath = Path.of(outputFile);


            //TODO: why the encrypt is always false, check it out!
            processFile(encrypt, in, out, cipher);

                //Check if outputfile is exist or not.
                if(Files.exists(outFilePath)) {
                    System.out.println("test-files/out.txt exists - OK to overwrite (y,n)?:");
                    Scanner outFile = new Scanner(System.in);
                    String isOk = outFile.next();
                    if(isOk.charAt(0) == 'y' || isOk.charAt(0) == 'Y') {
                        processFile(encrypt, in, out, cipher);
                    } else {
                        System.exit(1);
                    }
                }

            }
            catch(FileNotFoundException e){
                System.out.print("Cannot create output file.");
                System.exit(1);
            }
    }

    /**
     * This method returns true if the cipher is not null and
     * contains exactly 26 lowercase letters
     * one for each letter of alphabet
     * Otherwise returns false.
     *
     * @param cipher is char array
     * @return true cipher is not null and contains 26 letters otherwise
     * is false.
     */
    public static boolean isValidCipher(char[] cipher) {

        if (cipher.length != 26) {
            return false;
        } else {
            for (int i = 0; i < cipher.length; i++) {
                boolean x = Character.isLowerCase(cipher[i]);
                if (cipher[i] == 0 && x == false) {
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
     * <p>
     * if encrypt is false, decrypts the lines in the input file
     * using the cipher and outputs them to the output file
     *
     * @param encrypt the boolean value will tell if encrypts or decrypts
     * @param in      will scan the input file
     * @param out     will out into the output file
     * @param cipher  is the char array
     * @throws IllegalArgumentException with message Invalid Parameter, if in is null,
     *                                  out is null and cipher is invalid
     */
    public static void processFile(boolean encrypt, Scanner in, PrintWriter out, char[] cipher) {
        try {
            String line = "";
            String s = "";
            if(in != null && isValidCipher(cipher)) {
                if (encrypt == true) {
                    while (in.hasNextLine()) {
                        line += in.nextLine();
                        s += encryptLine(line, cipher);
                    }
                } else {
                    while (in.hasNextLine()) {
                        line += in.nextLine();
                        s += encryptLine(line, cipher);
                    }
                }
                out.print(s);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid parameter");
        }
    }

    /**
     * Return string containing encrypted line
     *
     * @param line   process will be working on the given line
     * @param cipher is the char array
     * @return s string line which encrypted line
     * @throws IllegalArgumentException with message Invalid Parameter, if line is null
     *                                  or cipher is invalid
     */
    public static String encryptLine(String line, char[] cipher) {
        String s = "";
        try {
            if (line != null && isValidCipher(cipher)) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) >= 'a' && line.charAt(i) <= 'z') {
                        s += cipher[line.charAt(i) - 'a'];

                    } else if (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z') {
                        s += (cipher[line.charAt(i) - 'A'] + 32);
                    } else {
                        s += line.charAt(i);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid parameter");
        }
        return s;
    }

    /**
     * Return string containing decrypted line
     * @param line process will be working on the given line
     * @param cipher is the char array
     * @return string line which the decrypted line
     */
    public static String decryptLine(String line, char[] cipher) {
        String s = "";
        try {
            if (line != null && isValidCipher(cipher)) {
//                for (int i = 0; i < line.length(); i++) {
//                    if (line.charAt(i) >= 'a' && line.charAt(i) <= 'z') {
//                            s += (char) ('a' + i);
//                    } else if (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z') {
//                        s += (char) ('A' + i - 32);
//                    } else {
//                        s += line.charAt(i);
//                    }
//                }
                for (int i = 0; i < line.length(); i++) {
                    for (int j = 0; j < cipher.length; j++) {
                        if (cipher[j]  == line.charAt(i)  && line.charAt(i) >= 'Z'){
                            s += (char) ('a' + j);
                            break;
                        }
                        else if (cipher[j]== (line.charAt(i) + 32)  && line.charAt(i) < 'a'){
                            s += (char) ('a' + j - 32);
                            break;
                        }
                    }
                }

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid parameter");
        }
        return s;
    }
}