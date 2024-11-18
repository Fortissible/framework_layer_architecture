import java.util.ArrayList;
import java.util.List;

public class LocalPaymentDatabase {
    private static final List<String> paymentIds = new ArrayList<>();

    public static void savePaymentId(String paymentId) {
        paymentIds.add(paymentId);
        System.out.println("Payment ID saved locally.");
    }
}
