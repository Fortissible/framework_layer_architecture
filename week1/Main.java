import java.util.*;

public class Main {
    // Map to store all users
    private static Map<String, User> users = new HashMap<>();
    // List of available tofu
    private static List<Tofu> tofuMenu = Arrays.asList(
            new Tofu("Medium Block Tofu", 20000),
            new Tofu("Firm Block Tofu", 10000),
            new Tofu("Smoked Block Tofu", 30000)
    );

    public static void main(String[] args) {
        // Pre-registered users for testing
        users.put("chandra", new User("chandra", "chandra", "gold"));
        users.put("admin", new User("admin", "admin123", "silver"));

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 3) {
            System.out.println("## Toppo Shop ##");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose >> ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1, 2, or 3.");
            }
        }

        scanner.close();
    }

    // Login method
    private static void login(Scanner scanner) {
        System.out.print("Input username [length must be more than 5]: ");
        String username = scanner.nextLine();
        System.out.print("Input password [length must be more than 5]: ");
        String password = scanner.nextLine();

        if (username.length() <= 5 || password.length() <= 5) {
            System.out.println("Username or password must be longer than 5 characters.");
            return;
        }

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Redirect to menu page...");
            System.out.println("Press enter to continue...");
            scanner.nextLine();  // Wait for user input
            showTofuMenu(scanner, user);
        } else {
            System.out.println("Wrong credentials.");
        }
    }

    // Register method
    private static void register(Scanner scanner) {
        System.out.print("Input username [length must be more than 5]: ");
        String username = scanner.nextLine();
        System.out.print("Input password [length must be more than 5]: ");
        String password = scanner.nextLine();

        if (username.length() <= 5 || password.length() <= 5) {
            System.out.println("Username or password must be longer than 5 characters.");
            return;
        }

        // Check if the user already exists
        if (users.containsKey(username)) {
            System.out.println("Username already registered.");
            return;
        }

        // Ask for member type and validate it
        String memberType = "";
        do {
            System.out.print("Input MemberType [must be 'gold' or 'silver' (insensitive)]: ");
            memberType = scanner.nextLine().toLowerCase();
        } while (!memberType.equals("gold") && !memberType.equals("silver"));

        // Register the user
        users.put(username, new User(username, password, memberType));
        System.out.println("Register successful");
    }

    // Show tofu menu and cart for a user
    private static void showTofuMenu(Scanner scanner, User user) {
        List<Tofu> cart = user.getCart();
        int choice = -1;

        while (choice != 0) {
            // Show cart
            if (cart.isEmpty()) {
                System.out.println("Your cart is empty");
            } else {
                System.out.println("Your Cart:");
                for (Tofu item : cart) {
                    System.out.println("- " + item.getName() + " : " + item.getPrice());
                }
            }
            System.out.println("-----------------------");

            // Show tofu menu
            System.out.println("Menu");
            System.out.println("==============");
            for (int i = 0; i < tofuMenu.size(); i++) {
                System.out.println((i + 1) + ". Name: " + tofuMenu.get(i).getName());
                System.out.println("   Price: " + tofuMenu.get(i).getPrice());
            }
            System.out.print("Choose Menu that you want to purchase [Enter '0' to checkout]: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice > 0 && choice <= tofuMenu.size()) {
                Tofu selectedTofu = tofuMenu.get(choice - 1);
                cart.add(selectedTofu);
                System.out.println(selectedTofu.getName() + " added to your cart.");
            } else if (choice != 0) {
                System.out.println("Invalid choice! Please choose between 1 and " + tofuMenu.size());
            }
        }

        // Checkout
        if (!cart.isEmpty()) {
            double total = calculateTotal(cart, user.getMemberType());
            System.out.println("Total of payment is " + total);
            double money = getMoneyInput(scanner, total);
            double change = money - total;
            System.out.println("Your change is: " + change);
            System.out.println("Thank you!!! :)");
            cart.clear(); // Clear the cart after checkout
        } else {
            System.out.println("You didn't purchase anything.");
        }
    }

    // Calculate total price with discount based on member type
    private static double calculateTotal(List<Tofu> cart, String memberType) {
        double total = 0;
        for (Tofu item : cart) {
            total += item.getPrice();
        }
        // Apply discount based on member type
        if (memberType.equalsIgnoreCase("gold")) {
            total *= 0.9; // 10% discount
        } else if (memberType.equalsIgnoreCase("silver")) {
            total *= 0.95; // 5% discount
        }
        return total;
    }

    // Get money input from user
    private static double getMoneyInput(Scanner scanner, double total) {
        double money = -1;
        while (money < total) {
            System.out.print("Please input your money: ");
            money = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (money < total) {
                System.out.println("Insufficient money! Please enter an amount greater than or equal to the total price.");
            }
        }
        return money;
    }
}
