import java.util.List;
import java.util.UUID;

public class ShopService {

    private final ProductRepo productRepo;
    private final OrderMapRepo orderRepo;
    private final IdService idService;

    public ShopService(ProductRepo productRepo, OrderMapRepo orderRepo, IdService idService) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.idService = idService;
    }

    public void placeOrder(List<OrderItem> orderedProducts) {
        for (OrderItem product : orderedProducts) {
            if (productRepo.getProductById(product.getProduct().id()).isEmpty()) {
                throw new IllegalArgumentException("Product with ID " + product.getProduct().id() + " does not exist.");
            }
        }
        Order order = new Order(idService.generateId(), orderedProducts, OrderStatus.PROCESSING);
        orderRepo.addOrder(order);
        System.out.println(ConsoleColors.success("Order placed successfully."));
    }

    public void updateOrder(UUID id, OrderStatus newStatus) {
        Order existing = orderRepo.getOrderById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
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
