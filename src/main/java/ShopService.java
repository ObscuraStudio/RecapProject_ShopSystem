import java.util.List;

public class ShopService {

    private final ProductRepo productRepo;
    private final OrderMapRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderMapRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void placeOrder(Order order) {
        List<OrderItem> orderedProducts = order.products();

        for (OrderItem product : orderedProducts) {
            if (productRepo.getProductById(product.getProduct().id()) == null) {
                System.out.println(ConsoleColors.error("Product with ID " + product.getProduct().id() + " does not exist."));
                return;
            }

        }
        orderRepo.addOrder(order);
        System.out.println(ConsoleColors.success("Order placed successfully."));
    }
}
