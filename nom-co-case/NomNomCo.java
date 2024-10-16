import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.security.SecureRandom;

// TODO: #REGION =========== FACTORY PATTERN FOR DATA MODELS ==============
// Interface Class represent a Confectionary Behaviour
// public interface Confectionary {
//     String getName();
//     String getType();
//     String getSoftness();
//     List<String> getToppings();
//     double getPrice();
//     String getPaymentType();
//     void bake();
// }

// Abstract class representing a Confectionary
abstract class Confectionary {
    String name;
    String softness;
    List<String> toppings = new ArrayList<>();
    double price;
    String paymentType;

    abstract void bake();
}

// Concrete classes for Cupcake
class Cupcake extends Confectionary {
    @Override
    void bake() {
        System.out.println("Baking a Cupcake named " + name + " with " + softness + " softness and " + toppings.size() + " toppings.");
    }
}
// Concrete classes for Tart
class Tart extends Confectionary {
    @Override
    void bake() {
        System.out.println("Baking a Tart named " + name + " with " + softness + " softness and " + toppings.size() + " toppings.");
    }
}

// Factory interface
interface ConfectionaryFactory {
    Confectionary createConfectionary();
}
// Factory for creating Cupcake
class CupcakeFactory implements ConfectionaryFactory {
    @Override
    public Confectionary createConfectionary() {
        return new Cupcake();
    }
}
// Factory for creating Tart
class TartFactory implements ConfectionaryFactory {
    @Override
    public Confectionary createConfectionary() {
        return new Tart();
    }
}
// TODO: #ENDREGION =======================================


// TODO: #REGION =========== ADAPTER FOR CONVERTING DIFFERENT TYPE OF CLASS/MODEL/CONTRACT ==============
// PaymentProcessor interface (Target)
interface PaymentProcessor {
    void processPayment(double amount);
}

// CashAdapter (One Concrete Adapter)
class CashAdapter implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Price: $" + String.format("%.2f", amount));

    }
}
// TransferAdapter (Another Concrete Adapter)
class TransferAdapter implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        String accountNumber = generateAccountNumber(); // Use existing utility
        System.out.println("Price: $" + String.format("%.2f", amount) + " with Account: " + accountNumber);
    }

    private String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }
}
// CryptoAdapter (Another Concrete Adapter)
class CryptoAdapter implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        String cryptoAddress = generateCryptoAddress();
        String cryptoCurrency = generateCryptoCurrency();
        System.out.println("Price: " + cryptoCurrency + " " + String.format("%.2f", amount) + " with Address: " + cryptoAddress);
    }

    private String generateCryptoAddress() {
        SecureRandom random = new SecureRandom();
        StringBuilder cryptoAddress = new StringBuilder("0x");
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            cryptoAddress.append(characters.charAt(index));
        }
        return cryptoAddress.toString();
    }

    private String generateCryptoCurrency() {
        SecureRandom random = new SecureRandom();
        StringBuilder cryptoCurrency = new StringBuilder("");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            cryptoCurrency.append(characters.charAt(index));
        }
        return cryptoCurrency.toString();
    }
}
// TODO: #ENDREGION =======================================


// TODO: #REGION =========== SINGLETON FOR SINGLE INSTANCE CLASS/OBJECT CREATION ==============
// Singleton class for managing the database
class ConfectionaryDatabase {
    private static ConfectionaryDatabase instance;
    private List<Confectionary> confectionaryList = new ArrayList<>();

    private ConfectionaryDatabase() {
        // Private constructor to prevent instantiation
    }

    public static ConfectionaryDatabase getInstance() {
        if (instance == null) {
            instance = new ConfectionaryDatabase();
        }
        return instance;
    }

    public void addConfectionary(Confectionary confectionary) {
        confectionaryList.add(confectionary);
        System.out.println("Confectionary added to database.");
    }

    public void viewConfectionaryOrders() {
      if (confectionaryList.isEmpty()) {
          System.out.println("No Confectionary Yet...");
      } else {
          for (Confectionary c : confectionaryList) {
              System.out.println("==========================");
              System.out.println("Name: " + c.name);
              System.out.println("Softness: " + c.softness);

              // Display toppings or a default message if no toppings are present
              if (c.toppings.isEmpty()) {
                  System.out.println("Topping: -");
              } else {
                  System.out.print("Topping: ");
                  for (int i = 0; i < c.toppings.size(); i++) {
                      System.out.print(c.toppings.get(i));
                      if (i < c.toppings.size() - 1) {
                          System.out.print(", ");
                      }
                  }
                  System.out.println();
              }

              System.out.println("Payment Type: " + c.paymentType);
              
              // Display price and account number or crypto address based on payment type
              // Main code where the paymentType is determined (this might be inside a method or class)
              PaymentProcessor paymentProcessor;

              switch (c.paymentType) {
                case "Cash":
                    paymentProcessor = new CashAdapter();
                    break;
                case "Transfer":
                    paymentProcessor = new TransferAdapter();
                    break;
                case "Crypto":
                    paymentProcessor = new CryptoAdapter();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown payment type: " + c.paymentType);
              }

              paymentProcessor.processPayment(c.price);
          }
      }
    }
    
    // Utility method to generate a random 10-digit account number
    private String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10); // Random digit between 0 and 9
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }

    // Utility method to generate a random 12-character crypto address (0x followed by 10 alphanumeric characters)
    private String generateCryptoAddress() {
        SecureRandom random = new SecureRandom();
        StringBuilder cryptoAddress = new StringBuilder("0x");
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            cryptoAddress.append(characters.charAt(index));
        }

        return cryptoAddress.toString();
    }

    // Utility method to generate a random 3-character crypto currency
    private String generateCryptoCurrency() {
        SecureRandom random = new SecureRandom();
        StringBuilder cryptoCurrency = new StringBuilder("");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            cryptoCurrency.append(characters.charAt(index));
        }

        return cryptoCurrency.toString();
    }
}
// TODO: #ENDREGION =======================================


// TODO: #REGION =========== MAIN FUNCTION ==============
public class NomNomCo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConfectionaryDatabase database = ConfectionaryDatabase.getInstance();
        String choice;

        do {
            System.out.println("Nom Nom Co.");
            System.out.println("===========");
            System.out.println("1. Bake Confectionary");
            System.out.println("2. View Confectionary Order");
            System.out.println("3. Exit");
            System.out.print(">> ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    bakeConfectionary(scanner, database);
                    break;
                case "2":
                    database.viewConfectionaryOrders();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (!choice.equals("3"));

        scanner.close();
    }

    public static void bakeConfectionary(Scanner scanner, ConfectionaryDatabase database) {
        ConfectionaryFactory factory = null;

        // Confectionary Type
        String type;
        do {
            System.out.print("Enter confectionary type (Cupcake/Tart): ");
            type = scanner.nextLine();
        } while (!type.equals("Cupcake") && !type.equals("Tart"));

        if (type.equals("Cupcake")) {
            factory = new CupcakeFactory();
        } else if (type.equals("Tart")) {
            factory = new TartFactory();
        }

        Confectionary confectionary = factory.createConfectionary();

        // Confectionary Name
        String name;
        do {
            System.out.print("Enter confectionary name (5-15 chars): ");
            name = scanner.nextLine();
        } while (name.length() < 5 || name.length() > 15);
        confectionary.name = name;

        // Confectionary Softness
        String softness;
        do {
            System.out.print("Enter confectionary softness (Fluffy/Medium/Hard): ");
            softness = scanner.nextLine();
        } while (!softness.equals("Fluffy") && !softness.equals("Medium") && !softness.equals("Hard"));
        confectionary.softness = softness;

        // Toppings
        String toppingOption;
        do {
            System.out.print("Do you want toppings? (Y/N): ");
            toppingOption = scanner.nextLine();
        } while (!toppingOption.equals("Y") && !toppingOption.equals("N"));

        if (toppingOption.equals("Y")) {
            for (int i = 0; i < 3; i++) {
                String topping;
                do {
                    System.out.print("Enter topping " + (i + 1) + " (1-10 chars): ");
                    topping = scanner.nextLine();
                } while (topping.length() < 1 || topping.length() > 10);
                confectionary.toppings.add(topping);
            }
        }

        // Confectionary Price
        double price;
        do {
            System.out.print("Enter confectionary price (10.0 - 50.0): ");
            price = Double.parseDouble(scanner.nextLine());
        } while (price < 10.0 || price > 50.0);
        confectionary.price = price;

        // Payment Type
        String paymentType;
        do {
            System.out.print("Enter payment type (Cash/Transfer/Crypto): ");
            paymentType = scanner.nextLine();
        } while (!paymentType.equals("Cash") && !paymentType.equals("Transfer") && !paymentType.equals("Crypto"));
        confectionary.paymentType = paymentType;

        // Convert price based on payment type
        switch (paymentType) {
            case "Cash":
                break;
            case "Transfer":
                confectionary.price = price * 1.1;
                break;
            case "Crypto":
                confectionary.price = price / 2;
                break;
        }

        // Bake and Add to Database
        confectionary.bake();
        database.addConfectionary(confectionary);

        System.out.println("Confectionary has been baked and added to the database!");
    }
}
// TODO: #ENDREGION =======================================