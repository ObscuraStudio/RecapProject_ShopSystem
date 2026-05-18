import java.util.List;

public class ShopService {

    private final ProductRepo productRepo;
    private final OrderMapRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderMapRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void placeOrder(Order order) {
        List<OrderItem> orderedProducts = order.getProducts();

        for (OrderItem product : orderedProducts) {
            if (productRepo.getProductById(product.getProduct().id()).isEmpty()) {
                throw new IllegalArgumentException("Product with ID " + product.getProduct().id() + " does not exist.");
            }
        }
        orderRepo.addOrder(order);
        System.out.println(ConsoleColors.success("Order placed successfully."));
    }

    public void updateOrder(int orderId, OrderStatus newStatus) {
        Order existing = orderRepo.getOrderById(orderId);
        if (existing == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist.");
        }
        Order updated = existing.withStatus(newStatus);
        orderRepo.addOrder(updated);

    }

    public List<Order> getOrderByStatus(OrderStatus status) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.getStatus() == status)
                .toList();

    }
}
