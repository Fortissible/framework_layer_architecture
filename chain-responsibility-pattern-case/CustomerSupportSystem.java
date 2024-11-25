import java.util.Scanner;

enum IssueType {
    ACCOUNT_HELP,
    REFUND, 
    TECHNICAL,
    // TAMBAH ISSUE TYPE
    COMPLAINT,
    HACKED,
    NOT_AVAILABLE
}

class SupportTicket {
    private final IssueType issueType;
    private final String description;

    public SupportTicket(IssueType issueType, String description) {
        this.issueType = issueType;
        this.description = description;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public String getDescription() {
        return description;
    }
}

abstract class SupportHandler {
    private SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleTicket(SupportTicket ticket) {
        if (canHandle(ticket)) {
            processTicket(ticket);
        } else if (nextHandler != null) {
            nextHandler.handleTicket(ticket);
        } else {
            System.out.println("No support available for this issue: " + ticket.getDescription());
        }
    }

    protected abstract boolean canHandle(SupportTicket ticket);

    protected abstract void processTicket(SupportTicket ticket);
}

class Level1Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getIssueType() == IssueType.ACCOUNT_HELP;
    }

    @Override
    protected void processTicket(SupportTicket ticket) {
        System.out.println("Level 1 Support: Resolving account issue - " + ticket.getDescription());
    }
}

class Level2Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getIssueType() == IssueType.REFUND || ticket.getIssueType() == IssueType.COMPLAINT;
    }

    @Override
    protected void processTicket(SupportTicket ticket) {
        System.out.println("Level 2 Support: Resolving refund/complaint issue - " + ticket.getDescription());
    }
}

class Level3Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getIssueType() == IssueType.TECHNICAL;
    }

    @Override
    protected void processTicket(SupportTicket ticket) {
        System.out.println("Level 3 Support: Resolving technical issue - " + ticket.getDescription());
    }
}

class Level4Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getIssueType() == IssueType.HACKED;
    }

    @Override
    protected void processTicket(SupportTicket ticket) {
        System.out.println("Level 4 Support: Resolving hacked issue - " + ticket.getDescription());
    }
}

public class CustomerSupportSystem {
    public static IssueType getIssueType(String serviceType) {
        switch (serviceType.toLowerCase()) {
            case "account":
                return IssueType.ACCOUNT_HELP;
            case "refund":
                return IssueType.REFUND;
            case "complaint":
                return IssueType.COMPLAINT;
            case "technical":
                return IssueType.TECHNICAL;
            case "hacked":
                return IssueType.HACKED;
            default:
                return IssueType.NOT_AVAILABLE;
        }
    }

    public static void main(String[] args) {
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();
        SupportHandler level4 = new Level4Support();

        level1.setNextHandler(level2);
        level2.setNextHandler(level3);
        level3.setNextHandler(level4);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ticketing System ===");
        System.out.println("Do you want to create a new ticket? (1: yes, 0: no): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        while (choice == 1) {
            System.out.print("Enter the issue type (Account, Refund, Complaint, Technical, Hacked): ");
            String serviceType = scanner.nextLine();
            IssueType issueType = getIssueType(serviceType);

            if (issueType == IssueType.NOT_AVAILABLE) {
                System.out.println("Please insert a valid issue type!");
            } else {
                System.out.print("Enter ticket description: ");
                String desc = scanner.nextLine();

                SupportTicket ticket = new SupportTicket(issueType, desc);
                
                level1.handleTicket(ticket);
            }

            System.out.println("Do you want to create a new ticket? (1: yes, 0: no): ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Exiting Ticketing System. Goodbye!");
        scanner.close();
    }
}