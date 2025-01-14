import java.util.Scanner;

public class GopayPayment implements PaymentMethod {
    private final String phoneNumber;
    private final double balance;

    public GopayPayment(Scanner scanner) {
        System.out.print("Enter Gopay phoneNumber: ");
        this.phoneNumber = scanner.nextLine();
        this.balance = 1000;
    }

    @Override
    public String pay(double amount) {
        // Simulate Gopay payment
        try {
            Thread.sleep(3000); // Simulate QR validity timeout
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Paying $%.2f using Gopay...\n", amount);
        return "GOPAY-" + System.currentTimeMillis();
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
