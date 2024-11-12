import java.util.Scanner;

// VendingMachineState interface to define actions in each state
interface VendingMachineState {
    void insertMoney(VendingMachine machine);
    void pickIceCream(VendingMachine machine);
    void generateIceCream(VendingMachine machine);
    void takeIceCream(VendingMachine machine);
}

// IdleState implementation
class IdleState implements VendingMachineState {
    public void insertMoney(VendingMachine machine) {
        System.out.println("Inserting money...");
        machine.setState(new SelectingIceCreamState());
    }

    public void pickIceCream(VendingMachine machine) {
        System.out.println("Please insert money first.");
    }

    public void generateIceCream(VendingMachine machine) {
        System.out.println("Please insert money first.");
    }

    public void takeIceCream(VendingMachine machine) {
        System.out.println("There's no ice cream to take.");
    }
}

// SelectingIceCreamState implementation
class SelectingIceCreamState implements VendingMachineState {
    public void insertMoney(VendingMachine machine) {
        System.out.println("Money has already been inserted.");
    }

    public void pickIceCream(VendingMachine machine) {
        System.out.println("Please select an ice cream type.");
    }

    public void generateIceCream(VendingMachine machine) {
        System.out.println("Please pick your ice cream before generating.");
    }

    public void takeIceCream(VendingMachine machine) {
        System.out.println("No ice cream to take yet.");
    }
}

// GenerateIceCreamState implementation
class GenerateIceCreamState implements VendingMachineState {
    public void insertMoney(VendingMachine machine) {
        System.out.println("Can't insert money now. Please generate your ice cream.");
    }

    public void pickIceCream(VendingMachine machine) {
        System.out.println("Ice cream type has already been selected.");
    }

    public void generateIceCream(VendingMachine machine) {
        System.out.println("Generating ice cream...");
        machine.setState(new IceCreamReadyState());
    }

    public void takeIceCream(VendingMachine machine) {
        System.out.println("No ice cream to take yet.");
    }
}

// IceCreamReadyState implementation
class IceCreamReadyState implements VendingMachineState {
    public void insertMoney(VendingMachine machine) {
        System.out.println("Please take your ice cream first.");
    }

    public void pickIceCream(VendingMachine machine) {
        System.out.println("Please take your ice cream first.");
    }

    public void generateIceCream(VendingMachine machine) {
        System.out.println("Please take your ice cream first.");
    }

    public void takeIceCream(VendingMachine machine) {
        System.out.println("Enjoy your ice cream!");
        machine.setState(new IdleState());
    }
}

// Abstract class for Template Method Pattern for ice cream preparation
abstract class IceCreamPreparationTemplate {
    public final void prepareIceCream() {
        prepareContainer();
        String flavor = chooseFlavor();
        System.out.println("The ice cream is ready: " + flavor);
    }

    protected abstract void prepareContainer();
    protected abstract String chooseFlavor();
}

// Concrete class for BucketIceCream
class BucketIceCream extends IceCreamPreparationTemplate {
    protected void prepareContainer() {
        System.out.println("Prepare a bucket.");
    }

    protected String chooseFlavor() {
        return "Strawberry"; // Example flavor
    }
}

// Concrete class for ConeIceCream
class ConeIceCream extends IceCreamPreparationTemplate {
    protected void prepareContainer() {
        System.out.println("Prepare a cone.");
    }

    protected String chooseFlavor() {
        return "Chocolate"; // Example flavor
    }
}

// VendingMachine class that maintains state and uses the Template Method Pattern
class VendingMachine {
    private VendingMachineState state;
    private IceCreamPreparationTemplate iceCream;

    public VendingMachine() {
        this.state = new IdleState();
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public String getStateName() {
        return state.getClass().getSimpleName();
    }

    public void insertMoney() {
        state.insertMoney(this);
    }

    public void pickIceCream(String type) {
        if (state instanceof SelectingIceCreamState) {
            if ("bucket".equalsIgnoreCase(type)) {
                iceCream = new BucketIceCream();
                setState(new GenerateIceCreamState());
            } else if ("cone".equalsIgnoreCase(type)) {
                iceCream = new ConeIceCream();
                setState(new GenerateIceCreamState());
            }
            System.out.println("Ice Cream Type not available.");
        } else {
            System.out.println("Invalid action for the current state.");
        }
    }

    public void generateIceCream() {
        if (state instanceof GenerateIceCreamState && iceCream != null) {
            state.generateIceCream(this);
            iceCream.prepareIceCream();
        } else {
            state.generateIceCream(this);
        }
    }

    public void takeIceCream() {
        state.takeIceCream(this);
    }
}

// Main class to test the VendingMachine
public class AngelicIcyCream {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nCurrent State: " + machine.getStateName());
            System.out.println("1. Insert Money to Vending");
            System.out.println("2. Picking ice cream (bucket or cone)");
            System.out.println("3. Generate ice cream");
            System.out.println("4. Take the ice cream");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    machine.insertMoney();
                    break;
                case 2:
                    System.out.print("Choose ice cream type (bucket or cone): ");
                    String type = scanner.next();
                    machine.pickIceCream(type);
                    break;
                case 3:
                    machine.generateIceCream();
                    break;
                case 4:
                    machine.takeIceCream();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
