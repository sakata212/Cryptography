import java.util.Scanner;

public class AffineCipher {
    private static final int ALPHABET_SIZE = 26;

    public static String affineEncrypt(String plainText, int a, int b) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char encryptedChar = encryptCharacter(ch, a, b);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    public static String affineDecrypt(String encryptedText, int a, int b) {
        StringBuilder decryptedText = new StringBuilder();
        for (char ch : encryptedText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char decryptedChar = decryptCharacter(ch, a, b);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }

    private static char encryptCharacter(char ch, int a, int b) {
        if (Character.isUpperCase(ch)) {
            return (char) ((a * (ch - 'A') + b) % ALPHABET_SIZE + 'A');
        } else {
            return (char) ((a * (ch - 'a') + b) % ALPHABET_SIZE + 'a');
        }
    }

    private static char decryptCharacter(char ch, int a, int b) {
        int inverseA = findMultiplicativeInverse(a);
        if (Character.isUpperCase(ch)) {
            return (char) ((inverseA * (ch - 'A' - b + ALPHABET_SIZE)) % ALPHABET_SIZE + 'A');
        } else {
            return (char) ((inverseA * (ch - 'a' - b + ALPHABET_SIZE)) % ALPHABET_SIZE + 'a');
        }
    }

    private static int findMultiplicativeInverse(int a) {
        for (int i = 1; i < ALPHABET_SIZE; i++) {
            if ((a * i) % ALPHABET_SIZE == 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid key: " + a);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plain text: ");
        String plainText = scanner.nextLine();

        System.out.print("Enter the multiplicative key (a): ");
        int multiplicativeKey = scanner.nextInt();

        System.out.print("Enter the additive key (b): ");
        int additiveKey = scanner.nextInt();

        String encryptedText = affineEncrypt(plainText, multiplicativeKey, additiveKey);
        String decryptedText = affineDecrypt(encryptedText, multiplicativeKey, additiveKey);

        System.out.println("Plain text: " + plainText);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
