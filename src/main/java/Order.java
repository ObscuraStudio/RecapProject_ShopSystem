import java.util.List;
import java.util.ArrayList;

public record Order(int id, List<OrderItem> products) {

    public double getTotal() {
        double total = 0;
        for (OrderItem product : products) {
            total += product.getQuantity() * product.getProduct().price();
        }
        return total;
    }
}
