import java.util.ArrayList;
import java.util.List;

public class ProductRepo {

    private final List<Product> products = new ArrayList<>();

    // Methods for adding, removing, and finding products

    public void addProduct(Product product) {
        Product existing = getProductById(product.id());
        if (existing != null) {
            System.out.println(ConsoleColors.error("ID " + product.id() + " is already taken by " + existing.name()));
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

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.id() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        System.out.println(products);
        return new ArrayList<>(products);
    }

}
