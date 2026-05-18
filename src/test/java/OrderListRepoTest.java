import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class OrderListRepoTest {

    @Test
    void addOrder_shouldAddOrderToList() {
        OrderListRepo repo = new OrderListRepo();
        Order testOrder = new Order(1, Collections.singletonList
                (new OrderItem
                        (new Product(1, "Iron Sword", 25.0), 3
                        )), OrderStatus.PROCESSING);

        repo.addOrder(testOrder);
        assertThat(repo.getAllOrders()).hasSize(1);
        assertThat(repo.getAllOrders().get(0).id()).isEqualTo(1);
    }

    @Test
    void removeOrder_shouldRemoveOrderFromList() {
        OrderListRepo repo = new OrderListRepo();
        Order testOrder = new Order(1, Collections.singletonList
                (new OrderItem
                        (new Product(1, "Iron Sword", 25.0), 3
                        )), OrderStatus.PROCESSING);

        repo.addOrder(testOrder);
        repo.removeOrder(1);
        assertThat(repo.getAllOrders()).isEmpty();
    }

    @Test
    void getOrderById_shouldReturnOrder_IfOrderExists() {
        OrderListRepo repo = new OrderListRepo();
        Order testOrder = new Order(1, Collections.singletonList
                (new OrderItem
                        (new Product(1, "Iron Sword", 25.0), 3
                        )), OrderStatus.PROCESSING);
        repo.addOrder(testOrder);
        Order result = repo.getOrderById(1);
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1);
    }

    @Test
    void getOrderById_shouldReturnNull_IfOrderDoesNotExist() {
        OrderListRepo repo = new OrderListRepo();
        Order result = repo.getOrderById(99);
        assertThat(result).isNull();
    }

}