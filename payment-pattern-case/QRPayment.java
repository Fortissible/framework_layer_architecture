import java.util.Scanner;

public class QRPayment implements PaymentMethod {
    private final double balance;

     public QRPayment() {
        this.balance = 500;
    }

    @Override
    public String pay(double amount) {
        System.out.printf("Paying $%.2f using QR Code...\n", amount);
        System.out.println("[QR Code Displayed]");
        try {
            Thread.sleep(3000); // Simulate QR validity timeout
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "QR-" + System.currentTimeMillis();
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
