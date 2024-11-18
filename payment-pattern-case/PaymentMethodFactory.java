import java.util.Scanner;

public class PaymentMethodFactory {
    public static PaymentMethod getPaymentMethod(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                return new PayPalPayment(scanner);
            case 2:
                return new CreditCardPayment(scanner);
            case 3:
                return new QRPayment();
            default:
                return null;
        }
    }
}
