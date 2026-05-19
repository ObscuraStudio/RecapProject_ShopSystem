import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OrderListRepoTest {

    @Test
    void addOrder_shouldAddOrderToList() {
        OrderListRepo repo = new OrderListRepo();
        UUID orderId = UUID.randomUUID();
        Order testOrder = new Order(orderId, Collections.singletonList
                (new OrderItem
                        (new Product(1, "Iron Sword", 25.0), 3
                        )), Instant.now(), OrderStatus.PROCESSING);

        repo.addOrder(testOrder);
        assertThat(repo.getAllOrders()).hasSize(1);
        assertThat(repo.getAllOrders().get(0).getId()).isEqualTo(orderId);
    }

    @Test
    void removeOrder_shouldRemoveOrderFromList() {
        OrderListRepo repo = new OrderListRepo();
        UUID orderId = UUID.randomUUID();
        Order testOrder = new Order(orderId, Collections.singletonList
                (new OrderItem
                        (new Product(1, "Iron Sword", 25.0), 3
                        )), Instant.now(), OrderStatus.PROCESSING);

        repo.addOrder(testOrder);
        repo.removeOrder(orderId);
        assertThat(repo.getAllOrders()).isEmpty();
    }

    @Test
    void getOrderById_shouldReturnOrder_IfOrderExists() {
        OrderListRepo repo = new OrderListRepo();
        UUID orderId = UUID.randomUUID();
        Order testOrder = new Order(orderId, Collections.singletonList
                (new OrderItem
                        (new Product(1, "Iron Sword", 25.0), 3
                        )), Instant.now(), OrderStatus.PROCESSING);
        repo.addOrder(testOrder);
        Order result = repo.getOrderById(orderId);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(orderId);
    }

    @Test
    void getOrderById_shouldReturnNull_IfOrderDoesNotExist() {
        OrderListRepo repo = new OrderListRepo();
        Order result = repo.getOrderById(UUID.randomUUID());
        assertThat(result).isNull();
    }

}
