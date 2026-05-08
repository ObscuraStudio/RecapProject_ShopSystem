import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        // constructing the repositories

        ProductRepo productRepo = new ProductRepo();
        OrderListRepo orderRepo = new OrderListRepo();

        // adding and removing products, then getting all products

        productRepo.addProduct(new Product(1, "Iron Sword"));
        productRepo.addProduct(new Product(2, "Iron Shield"));
        productRepo.addProduct(new Product(3, "Iron Armor"));
        productRepo.addProduct(new Product(4, "Iron Gloves"));
        productRepo.addProduct(new Product(5, "Iron Boots"));
        productRepo.getAllProducts();

        // constructing the service

        ShopService shopService = new ShopService(productRepo, orderRepo);

        // placing an order

        shopService.placeOrder(new Order
                (1, Collections.singletonList(new Product
                        (1, "Iron Sword"))));

        // getting all orders

        orderRepo.getAllOrders();
    }
}
