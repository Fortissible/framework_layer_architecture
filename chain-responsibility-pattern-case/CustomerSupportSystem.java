enum IssueType {
    ACCOUNT_HELP,
    REFUND,
    TECHNICAL
}

// Currency kalo di use-case sebelumnya
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

// Interface/Abstract Chain Handler
abstract class SupportHandler {
    private SupportHandler nextHandler;

    //#region Concrete Function
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
    //#endregion

    //#region Abstract Function (Need to be overided)
    protected abstract boolean canHandle(SupportTicket ticket);

    protected abstract void processTicket(SupportTicket ticket);
    //#endregion
}

//#region Concrete/Child Chain Handler
class Level1Support extends SupportHandler {
    //#region Overrided Abstract Function 
    // LEVEL-1 SUPPORT CAN ONLY HANDLE ISSUE TYPE ACCOUNT HELP
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getIssueType() == IssueType.ACCOUNT_HELP;
    }

    @Override
    protected void processTicket(SupportTicket ticket) {
        System.out.println("Level 1 Support: Resolving account issue - " + ticket.getDescription());
    }
    //#endregion
  }
  
class Level2Support extends SupportHandler {
    //#region Overrided Abstract Function 
    // LEVEL-2 SUPPORT CAN ONLY HANDLE ISSUE TYPE REFUND
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getIssueType() == IssueType.REFUND;
    }
    
    @Override
    protected void processTicket(SupportTicket ticket) {
        System.out.println("Level 2 Support: Resolving refund issue - " + ticket.getDescription());
    }
    //#endregion
}
  
class Level3Support extends SupportHandler {
    //#region Overrided Abstract Function 
    // LEVEL-3 SUPPORT CAN ONLY HANDLE ISSUE TYPE TECHNICAL
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getIssueType() == IssueType.TECHNICAL;
    }
  
    @Override
    protected void processTicket(SupportTicket ticket) {
        System.out.println("Level 3 Support: Resolving technical issue - " + ticket.getDescription());
    }
    //#endregion
} 
//#endregion 
  
//#region MAIN APPLICATION
public class CustomerSupportSystem {
    public static void main(String[] args) {
        // Create Handlers
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();

        // Chain Handlers
        level1.setNextHandler(level2);
        level2.setNextHandler(level3);

        // Create Tickets
        // Tiket 1 untuk issue Account Help dengan deskripsi "lupa password"
        SupportTicket ticket1 = new SupportTicket(IssueType.ACCOUNT_HELP, "Forgot password");
        // Tiket 2 untuk issue Refund dengan deskripsi "meminta refund untuk transaksi dengan id #12345"
        SupportTicket ticket2 = new SupportTicket(IssueType.REFUND, "Requesting refund for order #12345");
        // Tiket 3 untuk issue Technical dengan deskripsi "website tidak bisa loading"
        SupportTicket ticket3 = new SupportTicket(IssueType.TECHNICAL, "Website not loading properly");
        // Tiket 4 untuk issue Technical dengan deskripsi "website crash ketika login"
        SupportTicket ticket4 = new SupportTicket(IssueType.TECHNICAL, "App crashing on login");

        // Process Tickets
        System.out.println("Processing Support Tickets:");
        level1.handleTicket(ticket1);
        level1.handleTicket(ticket2);
        level1.handleTicket(ticket3);
        level1.handleTicket(ticket4);
    }
}
