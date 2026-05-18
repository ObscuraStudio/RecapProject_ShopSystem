import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProductRepoTest {

    @Test
    void addProduct_shouldAddProductToList() {
        // Arrange - set up what we need
        ProductRepo repo = new ProductRepo();
        Product sword = new Product(1, "Iron Sword", 25.0);

        // Act - do the thing we're testing
        repo.addProduct(sword);

        // Assert - check the result
        assertThat(repo.getAllProducts()).hasSize(1);
        assertThat(repo.getAllProducts().get(0).name()).isEqualTo("Iron Sword");
    }

    @Test
    void getProductById_shouldReturnProduct_whenProductExists() {
        ProductRepo repo = new ProductRepo();
        repo.addProduct(new Product(1, "Iron Sword", 25.0));

        Optional<Product> result = repo.getProductById(1);

        assertThat(result).isNotNull();
        assertThat(result.get().name()).isEqualTo("Iron Sword");
    }

    @Test
    void getProductById_shouldReturnEmpty_whenProductDoesNotExist() {
        ProductRepo repo = new ProductRepo();

        Optional<Product> result = repo.getProductById(99);

        assertThat(result).isEmpty();
    }

    @Test
    void removeProduct_shouldRemoveProductFromList() {
        ProductRepo repo = new ProductRepo();
        repo.addProduct(new Product(1, "Iron Sword", 25.0));
        repo.addProduct(new Product(2, "Bronze Sword", 15.0));
        repo.removeProduct(1);
        repo.removeProduct(2);
        assertThat(repo.getAllProducts()).isEmpty();
    }
}