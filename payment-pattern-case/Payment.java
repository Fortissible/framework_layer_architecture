import java.util.*;

public class Payment {
    public static void main(String[] args) {
        // Login simulation
        Scanner scanner = new Scanner(System.in);

        // #region Login UI Code
        System.out.println("=== Online Shopping System ===");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        // #endregion

        if (!UserAuthentication.login(username, password)) {
            // If user credential is not valid or not found
            System.out.println("Login failed! Exiting...");
            scanner.close();
            return;
        }

        // Shopping cart simulation
        ShoppingCart cart = new ShoppingCart(); // Create new shopping cart (empty state)

        // #region Shopping Cart UI Code
        System.out.println("\n=== Available Items ===");
        for (int i = 0; i < ItemDatabase.items.length; i++) {
            System.out.printf("%d. %s - $%.2f\n", i + 1, ItemDatabase.items[i].getName(), ItemDatabase.items[i].getPrice());
        }
        System.out.println("Select items to add to the cart (enter -1 to finish):");
        while (true) {
            System.out.print("Item number: ");
            int choice = scanner.nextInt();
            if (choice == -1) break;
            if (choice >= 1 && choice <= ItemDatabase.items.length) {
                cart.addItem(ItemDatabase.items[choice - 1]);
            } else {
                System.out.println("Invalid item number!");
            }
        }
        // #endregion

        // #region UI Payment selection
        System.out.println("\n=== Payment Methods ===");
        System.out.println("1. PayPal");
        System.out.println("2. Credit Card");
        System.out.println("3. QR Code");
        System.out.println("4. Gopay");
        System.out.print("Select payment method: ");
        // #endregion

        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        PaymentMethod paymentMethod = PaymentMethodFactory.getPaymentMethod(paymentChoice, scanner);
        while (paymentMethod == null) {
            System.out.println("Invalid payment method selected. Exiting...");
            paymentMethod = PaymentMethodFactory.getPaymentMethod(paymentChoice, scanner);
        }

        // Process payment
        System.out.println("\n=== Payment Process ===");
        PaymentContext paymentContext = new PaymentContext(paymentMethod);
        String paymentId = paymentContext.pay(cart.getTotalPrice());
        if (paymentId.equals("-1")){
            System.out.println("Saldo kurang...");
        }
        else if (paymentId != null) {
            System.out.println("Payment successful!");
            System.out.println("Payment ID: " + paymentId);
            System.out.println("Total: $" + cart.getTotalPrice());
            LocalPaymentDatabase.savePaymentId(paymentId); // Use real database (mySQL, Postgresql, mongoDb, file).
        } else {
            System.out.println("Payment failed.");
        }
    }
}
