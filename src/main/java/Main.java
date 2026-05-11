import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // constructing the repositories

        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();

        // adding and removing products, then getting all products

        productRepo.addProduct(new Product(1, "Iron Sword", 25.00));
        productRepo.addProduct(new Product(2, "Iron Shield", 20.00));
        productRepo.addProduct(new Product(3, "Iron Armor", 40.00));
        productRepo.addProduct(new Product(4, "Iron Gloves", 15.00));
        productRepo.addProduct(new Product(5, "Iron Boots", 20.00));
        productRepo.getAllProducts();
        System.out.println();

        // constructing the service

        ShopService shopService = new ShopService(productRepo, orderRepo);

        // placing an order

        Order firstOrder =
                (new Order
                        (1, Collections.singletonList
                                (new OrderItem
                                        (new Product(1, "Iron Sword", 25.0), 3
                                        ))));

        shopService.placeOrder(firstOrder);
        System.out.println("Order total: " + firstOrder.getTotal());

        // getting all orders

        orderRepo.getAllOrders();
    }
}
