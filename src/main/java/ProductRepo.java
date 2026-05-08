import java.util.ArrayList;
import java.util.List;

public class ProductRepo {

    private final List<Product> products = new ArrayList<>();

    // Methods for adding, removing, and finding products

    public void addProduct(Product product) {
        System.out.println(product.name() + " added to inventory.");
        products.add(product);
    }

    public void removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id() == id) {
                Product product = products.get(i);
                products.remove(i);
                System.out.println(product.name() + " removed from inventory.");
                return;
            }
        }
        System.out.println("Product not found");
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
        System.out.println("\nAll products in inventory:\n");
        System.out.println(products);
        return new ArrayList<>(products);
    }

}
