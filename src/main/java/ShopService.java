import java.util.ArrayList;
import java.util.List;

public class ShopService {

    private final ProductRepo productRepo;
    private final OrderListRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderListRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void placeOrder(Order order) {
        List<Product> orderedProducts = order.products();

        for (Product product : orderedProducts) {
            if (productRepo.getProduct(product.id()) == null) {
                System.out.println("Product with ID " + product.id() + " does not exist.");
                return;
            } else {
                System.out.println("Product with ID " + product.id() + " added to order.");
                orderRepo.addOrder(order);
            }
        }
    }


}
