void main() {

    // constructing the repositories and the service + scanner

    ProductRepo productRepo = new ProductRepo();
    CsvProductLoader.loadProducts(productRepo, "products.csv");
    OrderMapRepo orderRepo = new OrderMapRepo();
    IdService idService = new IdServiceImpl();
    ShopService shopService = new ShopService(productRepo, orderRepo, idService);
    Scanner scanner = new Scanner(System.in);

    // main menu loop

    while (true) {
        IO.println();
        IO.println(ConsoleColors.success("Welcome to the Shop, Adventurer!"));
        IO.println(ConsoleColors.header("=== SHOP SYSTEM v0.1 ==="));
        IO.println(ConsoleColors.info("1 - Add Product"));
        IO.println(ConsoleColors.info("2 - Remove Product"));
        IO.println(ConsoleColors.info("3 - Find Product"));
        IO.println(ConsoleColors.info("4 - List Products"));
        IO.println(ConsoleColors.info("5 - Place Order"));
        IO.println(ConsoleColors.info("6 - List Orders"));
        IO.println(ConsoleColors.error("0 - Exit"));
        IO.print(ConsoleColors.prompt("Choose: "));

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            IO.print(ConsoleColors.prompt("Product ID: "));
            int id = scanner.nextInt();
            scanner.nextLine();
            IO.print(ConsoleColors.prompt("Product Name: "));
            String name = scanner.nextLine();
            IO.print(ConsoleColors.prompt("Product Price: "));
            double price = scanner.nextDouble();
            scanner.nextLine();

            productRepo.addProduct(new Product(id, name, price));

        } else if (choice == 2) {
            IO.print(ConsoleColors.prompt("Product ID to remove: "));
            int id = scanner.nextInt();
            scanner.nextLine();

            productRepo.removeProduct(id);

        } else if (choice == 3) {
            IO.print(ConsoleColors.prompt("Product ID to find: "));
            int id = scanner.nextInt();
            scanner.nextLine();

            Optional<Product> found = productRepo.getProductById(id);
            if (found.isPresent()) {
                IO.println(ConsoleColors.success("Found: " + found));
            } else {
                IO.println(ConsoleColors.error("Product not found."));
            }

        } else if (choice == 4) {
            IO.println(ConsoleColors.header("--- All Products ---"));
            productRepo.getAllProducts();

        } else if (choice == 5) {
            List<OrderItem> items = new ArrayList<>();

            while (true) {
                IO.print(ConsoleColors.prompt("Product ID to add (0 to finish): "));
                int productId = scanner.nextInt();
                scanner.nextLine();

                if (productId == 0) {
                    break;
                }

                IO.print(ConsoleColors.prompt("Quantity: "));
                int quantity = scanner.nextInt();
                scanner.nextLine();
                Optional<Product> product = productRepo.getProductById(productId);
                if (product.isPresent()) {
                    items.add(new OrderItem(product.get(), quantity));
                    IO.println(ConsoleColors.success(product.get().name() + " x" + quantity + " added."));
                } else {
                    IO.println(ConsoleColors.error("Product with ID " + productId + " not found."));
                }
            }

            if (!items.isEmpty()) {
                try {
                    shopService.placeOrder(items);
                } catch (IllegalArgumentException e) {
                    IO.println(ConsoleColors.error(e.getMessage()));
                }
            } else {
                IO.println(ConsoleColors.warning("No valid items. Order cancelled."));
            }

        } else if (choice == 6) {
            IO.println(ConsoleColors.header("--- All Orders ---"));
            orderRepo.getAllOrders();

        } else if (choice == 0) {
            IO.println(ConsoleColors.warning("Goodbye!"));
            break;

        } else {
            IO.println(ConsoleColors.error("Invalid choice."));
        }
    }

    scanner.close();
}
