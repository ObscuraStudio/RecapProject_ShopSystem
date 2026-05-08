import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();
        OrderListRepo orderRepo = new OrderListRepo();

        System.out.println();
        productRepo.addProduct(new Product(1, "Iron Sword"));
        productRepo.addProduct(new Product(2, "Iron Shield"));
        productRepo.addProduct(new Product(3, "Iron Armor"));
        System.out.println();
        productRepo.removeProduct(2);
        productRepo.getAllProducts();
        System.out.println();
        orderRepo.addOrder(new Order(1, Collections.singletonList((new Product(1, "Iron Sword")))));
        System.out.println();
        orderRepo.getAllOrders();
    }
}
