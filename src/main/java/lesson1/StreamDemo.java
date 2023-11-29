package lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Product> products = generateRandomProducts();

        List<Product> electronics = products.stream()
                .filter(it -> it.getCategory().equals("Electronics"))
                .filter(it -> it.getCost() > 5000).toList();
        electronics.forEach(product -> System.out.println(product));

        System.out.println();

        products.stream()
                .filter(it -> it.getCategory().equals("Electronics"))
                .filter(it -> it.getCost() > 5000).collect(Collectors.toList())
                .forEach(System.out::println);

        products.stream()
                .filter(it -> it.getCategory().equals("Products"))
                .forEach(it -> {
                   it.setCost(it.getCost() * 1.05);
                });

        products.forEach(System.out::println);

        System.out.println();

        // Все элементы, начинающиеся на "М", собрать в список
        List<Product> m = products.stream()
                .filter(it -> it.getName().startsWith("M"))
                .collect(Collectors.toCollection(ArrayList::new));

        m.forEach(System.out::println);

        System.out.println();

        List<Product> existCollection = new ArrayList<>();

        products.stream()
                .filter(it -> it.getName().startsWith("M"))
                .collect(Collectors.toCollection(() -> existCollection));
        existCollection.forEach(System.out::println);
    }

    private static List<Product> generateRandomProducts() {
        return List.of(
            new Product("Milk", 70, "Products"),
            new Product("Bread", 53, "Products"),
            new Product("Water", 50, "Products"),
            new Product("Chips", 110, "Products"),
            new Product("Pasta", 80, "Products"),
            new Product("Bulugur", 73, "Products"),
            new Product("Milk", 70, "Products"),
            new Product("Computer", 7000, "Electronics"),
            new Product("Headphones", 7000, "Electronics"),
            new Product("Keyboard", 7000, "Electronics"),
            new Product("Glicyn", 100, "Medicines"),
            new Product("Bandage", 15, "Medicines"),
            new Product("Mask", 33, "Medicines")
        );
    }

    static class Product {
        private final String name;
        private double cost;
        private String category;

        public Product(String name, double cost, String category) {
            this.name = name;
            this.cost = cost;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("[%s] (cost = %s, category = %s)", name, String.valueOf(cost),
                    category);
        }
    }
}
