import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class HashFunctionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input 1
        System.out.print("Enter message: ");
        String input1 = scanner.nextLine();

        try {
            // Compute hash of message
            byte[] hash1 = computeHash(input1);
            System.out.println("Hash of message: " + Arrays.toString(hash1));

            // Simulate receiver computing the hash and checking integrity of message
            boolean isIntegrityIntact = checkIntegrity(input1, hash1);
            System.out.println("Integrity Check for message: " + isIntegrityIntact);

            // modifiedinput
            System.out.print("\nEnter modifiedinput: ");
            String input2 = scanner.nextLine();

            // Compute hash of modifiedinput
            byte[] hash2 = computeHash(input2);
            System.out.println("Hash of modifiedinput: " + Arrays.toString(hash2));

            // Compare hash of modifiedinput with hash of message for equality
            boolean isHashEqual = Arrays.equals(hash1, hash2);
            System.out.println("Hash Equality Check: " + isHashEqual);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static byte[] computeHash(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(message.getBytes());
    }

    public static boolean checkIntegrity(String message, byte[] expectedHash) throws NoSuchAlgorithmException {
        byte[] actualHash = computeHash(message);
        return Arrays.equals(actualHash, expectedHash);
    }
}

