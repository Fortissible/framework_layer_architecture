public class QRPayment implements PaymentMethod {
    @Override
    public String pay(double amount) {
        System.out.printf("Paying $%.2f using QR Code...\n", amount);
        System.out.println("[QR Code Displayed]");
        try {
            Thread.sleep(5000); // Simulate QR validity timeout
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "QR-" + System.currentTimeMillis();
    }
}
