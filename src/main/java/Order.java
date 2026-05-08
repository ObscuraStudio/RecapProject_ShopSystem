import java.util.List;
import java.util.ArrayList;

public record Order(int id, List<Product> products) {
}
