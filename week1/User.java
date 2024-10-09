import java.util.ArrayList;
import java.util.List;

// User class
public class User {
    private String username;
    private String password;
    private String memberType;
    private List<Tofu> cart;

    public User(String username, String password, String memberType) {
        this.username = username;
        this.password = password;
        this.memberType = memberType;
        this.cart = new ArrayList<>(); // Initialize an empty cart for the user
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMemberType() {
        return memberType;
    }

    public List<Tofu> getCart() {
        return cart;
    }
}