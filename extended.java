import java.util.Scanner;

public class extended {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number (a): ");
        int a = scanner.nextInt();

        System.out.print("Enter the second number (b): ");
        int b = scanner.nextInt();

        int[] coefficients = extended(a, b);
        int gcd = coefficients[0];
        int x = coefficients[1];
        int y = coefficients[2];

        System.out.println("GCD of " + a + " and " + b + " is: " + gcd);
        System.out.println("Coefficients (x, y) : (" + x + ", " + y + ")");
    }

    public static int[] extended(int a, int b) {
        if (b == 0) {
            return new int[] { a, 1, 0 };
        }

        int[] coefficients = extended(b, a % b);
        int gcd = coefficients[0];
        int x1 = coefficients[2];
        int y1 = coefficients[1] - (a / b) * coefficients[2];

        return new int[] { gcd, x1, y1 };
    }
}
