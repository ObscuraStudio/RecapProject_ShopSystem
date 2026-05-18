import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo {

    private final List<Order> orders = new ArrayList<>();

    // Methods for adding and retrieving orders

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                Order order = orders.get(i);
                orders.remove(i);
                return;
            }
        }
        System.out.println("Order not found.");
    }

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getAllOrders() {
        System.out.println();
        System.out.println(orders);
        return new ArrayList<>(orders);
    }


}
