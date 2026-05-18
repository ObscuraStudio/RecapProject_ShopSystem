import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {

    private final List<Product> products = new ArrayList<>();

    // Methods for adding, removing, and finding products

    public void addProduct(Product product) {
        Optional<Product> existing = getProductById(product.id());
        if (existing.isPresent()) {
            System.out.println(ConsoleColors.error("ID " + product.id() + " is already taken by " + existing.get().name()));
            return;
        }
        products.add(product);
        System.out.println(ConsoleColors.success(product.name() + " added to inventory."));
    }

    public void removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id() == id) {
                Product product = products.get(i);
                products.remove(i);
                return;
            }
        }
        System.out.println(ConsoleColors.error("Product not found"));
    }

    public Optional<Product> getProductById(int id) {
        for (Product product : products) {
            if (product.id() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public List<Product> getAllProducts() {
        for (Product product : products) {
            System.out.println("  [" + product.id() + "] " + product.name() + " - " + product.price() + " Gil");
        }
        return new ArrayList<>(products);
    }

}
