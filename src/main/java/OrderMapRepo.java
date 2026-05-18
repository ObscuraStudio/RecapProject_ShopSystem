import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OrderMapRepo implements OrderRepo {

    private final Map<Integer, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void removeOrder(int id) {
        orders.remove(id);
    }

    public Order getOrderById(int id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
        for (Order order : orders.values()) {
            System.out.println("  [Order " + order.getId() + "]");
            for (OrderItem item : order.getProducts()) {
                System.out.println("    - " + item.getProduct().name() + " x" + item.getQuantity() + " (" + item.getProduct().price() + " Gil each)");
            }
            System.out.println("    Total: " + order.getTotal() + " Gil");
        }
        return List.copyOf(orders.values());
    }
}
