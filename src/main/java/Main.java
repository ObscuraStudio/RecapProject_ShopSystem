public class Main {

    public static void main(String[] args) {

        ProductRepo repo = new ProductRepo();

        System.out.println();
        repo.addProduct(new Product(1, "Iron Sword"));
        repo.addProduct(new Product(2, "Iron Shield"));
        repo.addProduct(new Product(3, "Iron Armor"));
        System.out.println();
        repo.removeProduct(2);
        System.out.println(repo.getAllProducts());
        ;
    }
}
