import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;

// Mediator Interface
interface Mediator {
    void notify(Component sender, String event);
}

// Abstract Component Class (Template Pattern)
abstract class Component {
    protected Mediator mediator;

    public Component(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}

// Concrete Components
class EmailTextBox extends Component {
    private String email;

    public EmailTextBox(Mediator mediator) {
        super(mediator);
    }

    public void inputEmail(String email) {
        this.email = email;
        mediator.notify(this, "emailInput");
    }

    public String getEmail() {
        return email;
    }
}

class PasswordTextBox extends Component {
    private String password;

    public PasswordTextBox(Mediator mediator) {
        super(mediator);
    }

    public void inputPassword(String password) {
        this.password = password;
        mediator.notify(this, "passwordInput");
    }

    public String getPassword() {
        return password;
    }
}

class AdminSecretTextBox extends Component {
    private String adminSecret;

    public AdminSecretTextBox(Mediator mediator) {
        super(mediator);
    }

    public void inputAdminSecret(String adminSecret) {
        this.adminSecret = adminSecret;
        mediator.notify(this, "adminSecretInput");
    }

    public String getAdminSecret() {
        return adminSecret;
    }
}

class CaptchaTextBox extends Component {
    private String captchaInput;
    private String generatedCaptcha;

    public CaptchaTextBox(Mediator mediator) {
        super(mediator);
        this.generatedCaptcha = generateCaptcha();
    }

    private String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            captcha.append((char) ('a' + random.nextInt(26)));
        }
        return captcha.toString();
    }

    public void inputCaptcha(String captchaInput) {
        this.captchaInput = captchaInput;
        mediator.notify(this, "captchaInput");
    }

    public String getCaptchaInput() {
        return captchaInput;
    }

    public String getGeneratedCaptcha() {
        return generatedCaptcha;
    }
}

class LoginOption extends Component {
    private boolean isAdmin;

    public LoginOption(Mediator mediator) {
        super(mediator);
    }

    public void selectOption(int option) {
        this.isAdmin = option == 2; // 1 = User, 2 = Admin
        mediator.notify(this, "loginOptionSelected");
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

class SubmitButton extends Component {
    public SubmitButton(Mediator mediator) {
        super(mediator);
    }

    public void submit() {
        mediator.notify(this, "submit");
    }
}

// Concrete Mediator
class LoginMediator implements Mediator {
    private EmailTextBox emailTextBox;
    private PasswordTextBox passwordTextBox;
    private AdminSecretTextBox adminSecretTextBox;
    private CaptchaTextBox captchaTextBox;
    private LoginOption loginOption;
    private SubmitButton submitButton;

    public LoginMediator() {
        emailTextBox = new EmailTextBox(this);
        passwordTextBox = new PasswordTextBox(this);
        adminSecretTextBox = new AdminSecretTextBox(this);
        captchaTextBox = new CaptchaTextBox(this);
        loginOption = new LoginOption(this);
        submitButton = new SubmitButton(this);
    }

    public void startLoginProcess() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input your Email >> ");
        emailTextBox.inputEmail(scanner.nextLine());

        System.out.print("Input your password >> ");
        passwordTextBox.inputPassword(scanner.nextLine());

        System.out.println("1. Login as user\n2. Login as admin");
        System.out.print("Please select your option >> ");
        loginOption.selectOption(scanner.nextInt());
        scanner.nextLine(); // consume newline

        if (loginOption.isAdmin()) {
            System.out.print("Input your admin's secret >> ");
            adminSecretTextBox.inputAdminSecret(scanner.nextLine());
        } else {
            System.out.println("Input the captcha to make sure you are not a robot(" + captchaTextBox.getGeneratedCaptcha() + ") >> ");
            captchaTextBox.inputCaptcha(scanner.nextLine());
        }

        System.out.println("Press enter to submit");
        scanner.nextLine(); // wait for submit
        submitButton.submit();
        scanner.close();
    }

    @Override
    public void notify(Component sender, String event) {
        if ("submit".equals(event)) {
            boolean isEmailValid = emailTextBox.getEmail().contains("@");
            boolean isPasswordValid = Pattern.matches("[a-zA-Z0-9]+", passwordTextBox.getPassword());

            if (!isEmailValid) {
                System.out.println("Invalid email.");
            } else if (!isPasswordValid) {
                System.out.println("Invalid password. Must be alphanumeric.");
            } else if (loginOption.isAdmin()) {
                if ("4dminS3cr3t".equals(adminSecretTextBox.getAdminSecret())) {
                    System.out.println("Redirect to Home page...");
                } else {
                    System.out.println("Fail to login.");
                }
            } else {
                if (captchaTextBox.getGeneratedCaptcha().equals(captchaTextBox.getCaptchaInput())) {
                    System.out.println("Redirect to Home page...");
                } else {
                    System.out.println("Fail to login.");
                }
            }
        }
    }
}

// Main Class to Execute the Program
public class AngelMaya {
    public static void main(String[] args) {
        LoginMediator mediator = new LoginMediator();
        mediator.startLoginProcess();
    }
}

// ITERATOR
// class LoginHistory implements Iterable<LoginAttempt> {
//   private List<LoginAttempt> attempts = new ArrayList<>();

//   public void addAttempt(LoginAttempt attempt) {
//       attempts.add(attempt);
//   }

//   @Override
//   public Iterator<LoginAttempt> iterator() {
//       return attempts.iterator();
//   }
// }

// public void displayLoginHistory() {
//   System.out.println("Login History:");
//   for (LoginAttempt attempt : loginHistory) {
//       System.out.println(attempt);
//   }
// }