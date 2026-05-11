import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // constructing the repositories and the service + scanner

        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("=== Shop System ===");
            System.out.println("1 - Add Product");
            System.out.println("2 - Remove Product");
            System.out.println("3 - Find Product");
            System.out.println("4 - List Products");
            System.out.println("5 - Place Order");
            System.out.println("6 - List Orders");
            System.out.println("0 - Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Product ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Product Name: ");
                String name = scanner.nextLine();
                System.out.print("Product Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();

                productRepo.addProduct(new Product(id, name, price));

            } else if (choice == 2) {
                System.out.print("Product ID to remove: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                productRepo.removeProduct(id);

            } else if (choice == 3) {
                System.out.print("Product ID to find: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Product found = productRepo.getProductById(id);
                if (found != null) {
                    System.out.println("Found: " + found);
                } else {
                    System.out.println("Product not found.");
                }

            } else if (choice == 4) {
                productRepo.getAllProducts();

            } else if (choice == 5) {
                System.out.print("Order ID: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();

                List<OrderItem> items = new ArrayList<>();

                while (true) {
                    System.out.print("Product ID to add (0 to finish): ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();

                    if (productId == 0) {
                        break;
                    }

                    System.out.print("Quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    Product product = productRepo.getProductById(productId);
                    if (product != null) {
                        items.add(new OrderItem(product, quantity));
                    } else {
                        System.out.println("Product with ID " + productId + " not found.");
                    }
                }

                if (!items.isEmpty()) {
                    Order order = new Order(orderId, items);
                    shopService.placeOrder(order);
                    System.out.println("Order total: " + order.getTotal());
                } else {
                    System.out.println("No valid items. Order cancelled.");
                }

            } else if (choice == 6) {
                System.out.println(orderRepo.getAllOrders());

            } else if (choice == 0) {
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}