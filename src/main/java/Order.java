import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

@Getter
@AllArgsConstructor
public class Order {

    private final int id;
    private final List<OrderItem> products;
    @With
    private OrderStatus status;

    public double getTotal() {
        double total = 0;
        for (OrderItem product : products) {
            total += product.getQuantity() * product.getProduct().price();
        }
        return total;
    }
}
