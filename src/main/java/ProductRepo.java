import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {

    private final List<Product> products = new ArrayList<>();

    // Methods for adding, removing, and finding products

    public void addProduct(Product product) {
        System.out.println("Product added");
        products.add(product);
    }

    public void removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id() == id) {
                products.remove(i);
                System.out.println("Product removed");
                return;
            }
        }
        System.out.println("Product not found");
    }

    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.id() == id) {
                System.out.println("Product found");
                return product;
            }
        }
        System.out.println("Product not found");
        return null;
    }

}
