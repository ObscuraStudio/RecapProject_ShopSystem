import java.util.List;

public interface OrderRepo {
    void addOrder(Order order);

    void removeOrder(int id);

    Order getOrderById(int id);

    List<Order> getAllOrders();
}


