import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class CsvProductLoader {

    public static void loadProducts(ProductRepo productRepo, String fileName) {
        InputStream inputStream = CsvProductLoader.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            System.out.println(ConsoleColors.error("CSV file not found: " + fileName));
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line = reader.readLine(); // skip the header line

            line = reader.readLine(); // first data line
            while (line != null) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                productRepo.addProduct(new Product(id, name, price));

                line = reader.readLine(); // next line
            }

            reader.close();
            System.out.println(ConsoleColors.success("Products loaded from " + fileName));
        } catch (Exception e) {
            System.out.println(ConsoleColors.error("Error reading CSV: " + e.getMessage()));
        }
    }
}
