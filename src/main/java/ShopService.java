import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Order order = new Order(idService.generateId(), orderedProducts, Instant.now(), OrderStatus.PROCESSING);
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

    public Map<OrderStatus, Order> getOldestOrderPerStatus() {
        // Step 1: Group all orders by their status
        // Result: { PROCESSING -> [order1, order3], IN_DELIVERY -> [order2] }
        Map<OrderStatus, List<Order>> grouped = orderRepo.getAllOrders().stream()
                .collect(Collectors.groupingBy(Order::getStatus));

        // Step 2: For each status, find the order with the earliest timestamp
        Map<OrderStatus, Order> oldest = new HashMap<>();
        for (Map.Entry<OrderStatus, List<Order>> entry : grouped.entrySet()) {
            entry.getValue().stream()
                    .min(Comparator.comparing(Order::getTimestamp))
                    .ifPresent(order -> oldest.put(entry.getKey(), order));
        }

        return oldest;
    }
}
