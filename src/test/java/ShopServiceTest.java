import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShopServiceTest {

    @Test
    void placeOrder_shouldPlaceOrder_IfProductsExist() {

        //also tests if the quantity of products is correct and the total price is correct

        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        productRepo.addProduct(new Product(1, "Iron Sword", 25.00));
        productRepo.addProduct(new Product(5, "Iron Boots", 20.00));
        productRepo.addProduct(new Product(3, "Iron Armor", 40.00));

        Order testOrder = new Order(1, List.of(
                new OrderItem(new Product(1, "Iron Sword", 25.0), 3),
                new OrderItem(new Product(3, "Iron Armor", 40.0), 1),
                new OrderItem(new Product(5, "Iron Boots", 20.0), 2)
        ), OrderStatus.PROCESSING);
        shopService.placeOrder(testOrder);

        assertThat(orderRepo.getAllOrders()).hasSize(1);
        assertThat(testOrder.products()).hasSize(3);
        assertThat(testOrder.products().get(0).getQuantity()).isEqualTo(3);  // 3 Iron Swords
        assertThat(testOrder.products().get(1).getQuantity()).isEqualTo(1);  // 1 Iron Armor
        assertThat(testOrder.products().get(2).getQuantity()).isEqualTo(2);  // 2 Iron Boots

        assertThat(testOrder.getTotal()).isEqualTo(155.0);  // (3*25) + (1*40) + (2*20)
    }

    @Test
    void placeOrder_shouldThrowException_IfProductDoesNotExist() {
        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        // intentionally NOT adding any products to the repo

        Order testOrder = new Order(1, List.of(
                new OrderItem(new Product(99, "Fake Item", 100.0), 1)
        ), OrderStatus.PROCESSING);

        assertThatThrownBy(() -> shopService.placeOrder(testOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not exist");
    }
}