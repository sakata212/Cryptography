import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PasswordFileExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a password file
        createPasswordFile(scanner);

        // Modify the password file to store hashed passwords
        modifyPasswordFile();

        // Perform identification
        performIdentification(scanner);
    }

    public static void createPasswordFile(Scanner scanner) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("passwords.txt"))) {
            for (int i = 0; i < 10; i++) {
                System.out.print("Enter password " + (i + 1) + ": ");
                String password = scanner.nextLine();
                writer.println(password);
            }

            System.out.println("Password file created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating password file: " + e.getMessage());
        }
    }

    public static void modifyPasswordFile() {
        try (Scanner scanner = new Scanner(new File("passwords.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("hashed_passwords.txt"))) {

            while (scanner.hasNextLine()) {
                String password = scanner.nextLine();
                String hashedPassword = hashPassword(password);
                writer.println(hashedPassword);
            }

            System.out.println("Password file modified to store hashed passwords.");
        } catch (IOException e) {
            System.out.println("Error modifying password file: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing password: " + e.getMessage());
        }
    }

    public static void performIdentification(Scanner scanner) {
        try (Scanner passwordScanner = new Scanner(new File("hashed_passwords.txt"))) {
            System.out.print("Enter password for identification: ");
            String password = scanner.nextLine();
            boolean isPasswordCorrect = checkPassword(password, passwordScanner);
            if (isPasswordCorrect) {
                System.out.println("Password is correct. Identification successful.");
            } else {
                System.out.println("Password is incorrect. Identification failed.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Password file not found: " + e.getMessage());
        }
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    public static boolean checkPassword(String password, Scanner scanner) {
        while (scanner.hasNextLine()) {
            String hashedPassword = scanner.nextLine();
            if (hashedPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }
}

