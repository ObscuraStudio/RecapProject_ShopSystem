import java.util.List;
import java.util.UUID;

public interface OrderRepo {
    void addOrder(Order order);

    void removeOrder(UUID id);

    Order getOrderById(UUID id);

    List<Order> getAllOrders();
}


