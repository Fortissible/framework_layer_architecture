import java.util.Scanner;

public class CreditCardPayment implements PaymentMethod {
    private final String ownerName;
    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;
    private final double balance;

    public CreditCardPayment(Scanner scanner) {
        System.out.print("Enter your name: ");
        this.ownerName = scanner.nextLine();
        System.out.print("Enter card number: ");
        this.cardNumber = scanner.nextLine();
        System.out.print("Enter expiry date (MM/YY): ");
        this.expiryDate = scanner.nextLine();
        System.out.print("Enter CVV: ");
        this.cvv = scanner.nextLine();
        this.balance = 2000;
    }

    @Override
    public String pay(double amount) {
        // Simulate credit card payment
        try {
            Thread.sleep(3000); // Simulate QR validity timeout
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Paying $%.2f using Credit Card...\n", amount);
        return "CC-" + System.currentTimeMillis();
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
