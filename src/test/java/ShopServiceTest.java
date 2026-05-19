import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShopServiceTest {

    @Test
    void placeOrder_shouldPlaceOrder_IfProductsExist() {
        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        IdService idService = new IdServiceImpl();
        ShopService shopService = new ShopService(productRepo, orderRepo, idService);
        productRepo.addProduct(new Product(1, "Iron Sword", 25.00));
        productRepo.addProduct(new Product(5, "Iron Boots", 20.00));
        productRepo.addProduct(new Product(3, "Iron Armor", 40.00));

        List<OrderItem> items = List.of(
                new OrderItem(new Product(1, "Iron Sword", 25.0), 3),
                new OrderItem(new Product(3, "Iron Armor", 40.0), 1),
                new OrderItem(new Product(5, "Iron Boots", 20.0), 2)
        );
        shopService.placeOrder(items);

        assertThat(orderRepo.getAllOrders()).hasSize(1);

        Order placed = orderRepo.getAllOrders().getFirst();
        assertThat(placed.getProducts()).hasSize(3);
        assertThat(placed.getProducts().get(0).getQuantity()).isEqualTo(3);
        assertThat(placed.getProducts().get(1).getQuantity()).isEqualTo(1);
        assertThat(placed.getProducts().get(2).getQuantity()).isEqualTo(2);
        assertThat(placed.getTotal()).isEqualTo(155.0);
    }

    @Test
    void placeOrder_shouldThrowException_IfProductDoesNotExist() {
        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        IdService idService = new IdServiceImpl();
        ShopService shopService = new ShopService(productRepo, orderRepo, idService);

        List<OrderItem> items = List.of(
                new OrderItem(new Product(99, "Fake Item", 100.0), 1)
        );

        assertThatThrownBy(() -> shopService.placeOrder(items))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void updateOrder_shouldUpdateStatus_toInDelivery() {
        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        IdService idService = new IdServiceImpl();
        ShopService shopService = new ShopService(productRepo, orderRepo, idService);
        productRepo.addProduct(new Product(1, "Iron Sword", 25.00));

        List<OrderItem> items = List.of(
                new OrderItem(new Product(1, "Iron Sword", 25.0), 1)
        );
        shopService.placeOrder(items);

        UUID orderId = orderRepo.getAllOrders().getFirst().getId();
        shopService.updateOrder(orderId, OrderStatus.IN_DELIVERY);

        Order updated = orderRepo.getOrderById(orderId);
        assertThat(updated.getStatus()).isEqualTo(OrderStatus.IN_DELIVERY);
    }

    @Test
    void updateOrder_shouldThrowException_IfOrderDoesNotExist() {
        ProductRepo productRepo = new ProductRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        IdService idService = new IdServiceImpl();
        ShopService shopService = new ShopService(productRepo, orderRepo, idService);

        assertThatThrownBy(() -> shopService.updateOrder(UUID.randomUUID(), OrderStatus.IN_DELIVERY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not exist");
    }
}
