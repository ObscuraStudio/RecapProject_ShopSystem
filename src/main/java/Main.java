import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // constructing the repositories and the service + scanner

        ProductRepo productRepo = new ProductRepo();
        CsvProductLoader.loadProducts(productRepo, "products.csv");
        OrderMapRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        Scanner scanner = new Scanner(System.in);

        // main menu loop

        while (true) {
            System.out.println();
            System.out.println(ConsoleColors.header("=== Shop System ==="));
            System.out.println(ConsoleColors.info("1 - Add Product"));
            System.out.println(ConsoleColors.info("2 - Remove Product"));
            System.out.println(ConsoleColors.info("3 - Find Product"));
            System.out.println(ConsoleColors.info("4 - List Products"));
            System.out.println(ConsoleColors.info("5 - Place Order"));
            System.out.println(ConsoleColors.info("6 - List Orders"));
            System.out.println(ConsoleColors.error("0 - Exit"));
            System.out.print(ConsoleColors.prompt("Choose: "));

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print(ConsoleColors.prompt("Product ID: "));
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print(ConsoleColors.prompt("Product Name: "));
                String name = scanner.nextLine();
                System.out.print(ConsoleColors.prompt("Product Price: "));
                double price = scanner.nextDouble();
                scanner.nextLine();

                productRepo.addProduct(new Product(id, name, price));

            } else if (choice == 2) {
                System.out.print(ConsoleColors.prompt("Product ID to remove: "));
                int id = scanner.nextInt();
                scanner.nextLine();

                productRepo.removeProduct(id);

            } else if (choice == 3) {
                System.out.print(ConsoleColors.prompt("Product ID to find: "));
                int id = scanner.nextInt();
                scanner.nextLine();

                Product found = productRepo.getProductById(id);
                if (found != null) {
                    System.out.println(ConsoleColors.success("Found: " + found));
                } else {
                    System.out.println(ConsoleColors.error("Product not found."));
                }

            } else if (choice == 4) {
                System.out.println(ConsoleColors.header("--- All Products ---"));
                productRepo.getAllProducts();

            } else if (choice == 5) {
                System.out.print(ConsoleColors.prompt("Order ID: "));
                int orderId = scanner.nextInt();
                scanner.nextLine();

                List<OrderItem> items = new ArrayList<>();

                while (true) {
                    System.out.print(ConsoleColors.prompt("Product ID to add (0 to finish): "));
                    int productId = scanner.nextInt();
                    scanner.nextLine();

                    if (productId == 0) {
                        break;
                    }

                    System.out.print(ConsoleColors.prompt("Quantity: "));
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    Product product = productRepo.getProductById(productId);
                    if (product != null) {
                        items.add(new OrderItem(product, quantity));
                        System.out.println(ConsoleColors.success(product.name() + " x" + quantity + " added."));
                    } else {
                        System.out.println(ConsoleColors.error("Product with ID " + productId + " not found."));
                    }
                }

                if (!items.isEmpty()) {
                    Order order = new Order(orderId, items);
                    shopService.placeOrder(order);
                    System.out.println(ConsoleColors.success("Order total: " + order.getTotal()));
                } else {
                    System.out.println(ConsoleColors.warning("No valid items. Order cancelled."));
                }

            } else if (choice == 6) {
                System.out.println(ConsoleColors.header("--- All Orders ---"));
                System.out.println(orderRepo.getAllOrders());

            } else if (choice == 0) {
                System.out.println(ConsoleColors.warning("Goodbye!"));
                break;

            } else {
                System.out.println(ConsoleColors.error("Invalid choice."));
            }
        }

        scanner.close();
    }
}
