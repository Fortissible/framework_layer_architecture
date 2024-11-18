import java.util.Scanner;

public class PayPalPayment implements PaymentMethod {
    private final String email;
    private final String password;

    public PayPalPayment(Scanner scanner) {
        System.out.print("Enter PayPal email: ");
        this.email = scanner.nextLine();
        System.out.print("Enter PayPal password: ");
        this.password = scanner.nextLine();
    }

    @Override
    public String pay(double amount) {
        // Simulate PayPal payment
        System.out.printf("Paying $%.2f using PayPal...\n", amount);
        return "PAYPAL-" + System.currentTimeMillis();
    }
}
