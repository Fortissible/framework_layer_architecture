import java.util.Scanner;

public class CreditCardPayment implements PaymentMethod {
    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;

    public CreditCardPayment(Scanner scanner) {
        System.out.print("Enter card number: ");
        this.cardNumber = scanner.nextLine();
        System.out.print("Enter expiry date (MM/YY): ");
        this.expiryDate = scanner.nextLine();
        System.out.print("Enter CVV: ");
        this.cvv = scanner.nextLine();
    }

    @Override
    public String pay(double amount) {
        // Simulate credit card payment
        System.out.printf("Paying $%.2f using Credit Card...\n", amount);
        return "CC-" + System.currentTimeMillis();
    }
}
