import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Item> items = new ArrayList<>(); // EMPTY LIST STATE WHEN INIT

    public void addItem(Item item) {
        items.add(item);
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}
