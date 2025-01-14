import java.util.Scanner;

public class PayPalPayment implements PaymentMethod {
    private final String email;
    private final String password;
    private final double balance;

    public PayPalPayment(Scanner scanner) {
        System.out.print("Enter PayPal email: ");
        this.email = scanner.nextLine();
        System.out.print("Enter PayPal password: ");
        this.password = scanner.nextLine();
        this.balance = 1000;
    }

    @Override
    public String pay(double amount) {
        // Simulate PayPal payment
        try {
            Thread.sleep(3000); // Simulate QR validity timeout
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Paying $%.2f using PayPal...\n", amount);
        return "PAYPAL-" + System.currentTimeMillis();
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
