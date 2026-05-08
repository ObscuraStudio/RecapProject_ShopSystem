import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OrderMapRepo implements OrderRepo {

    private final Map<Integer, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.id(), order);
    }

    public void removeOrder(int id) {
        orders.remove(id);
    }

    public Order getOrderById(int id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
        return List.copyOf(orders.values());
    }
}
