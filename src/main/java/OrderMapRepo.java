import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

public class OrderMapRepo implements OrderRepo {

    private final Map<UUID, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void removeOrder(UUID id) {
        orders.remove(id);
    }

    public Order getOrderById(UUID id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
        for (Order order : orders.values()) {
            String shortId = order.getId().toString().substring(0, 8);
            System.out.println("  [Order #" + shortId + "]");
            for (OrderItem item : order.getProducts()) {
                System.out.println("    - " + item.getProduct().name() + " x" + item.getQuantity() + " (" + item.getProduct().price() + " Gil each)");
            }
            System.out.println("    Ordered: " + order.getTimestamp());
            System.out.println("    Total: " + order.getTotal() + " Gil");
        }
        return List.copyOf(orders.values());
    }
}
