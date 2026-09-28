package edu.miu.cs.cs489appsd.lab1.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1.productmgmtapp.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProductMgmtApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductMgmtApp.class, args);
        Product[] products = existingProducts();
        printProducts(products);

                for (String format : new String[]{"json", "xml", "csv"}) {
                        System.out.printf("%n--- %s --- %n", format.toUpperCase(Locale.ROOT));
                        printProducts(products, format);
        }
    }

    public static void printProducts(Product[] products) {
        var sortedProducts = Arrays.stream(products)
                .sorted(Comparator.comparing(Product::getName)
                        .thenComparing(Product::getUnitPrice, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        System.out.printf("%-10s %-10s %-15s %-18s %s%n",
                "Product ID", "Name", "Date Supplied", "Quantity in Stock", "Unit Price");
        System.out.println("-----------------------------------------------------------------------");
        sortedProducts.forEach(product -> System.out.printf("%-10d %-10s %-15s %-18d $%.2f%n",
                product.getProductId(), product.getName(), product.getDateSupplied(),
                product.getQuantityInStock(), product.getUnitPrice()));
        }

        public static void printProducts(Product[] products, String format) {
                var sortedProducts = Arrays.stream(products)
                .sorted(Comparator.comparing(Product::getName)
                        .thenComparing(Product::getUnitPrice, Comparator.reverseOrder()))
                                .collect(Collectors.toList());

                switch (format.toLowerCase(Locale.ROOT)) {
                        case "json" -> printJson(sortedProducts);
                        case "xml" -> printXml(sortedProducts);
                        case "csv" -> printCsv(sortedProducts);
                        default -> throw new IllegalArgumentException("Format must be json, xml, or csv: " + format);
                }
        }

        private static void printJson(java.util.List<Product> products) {
                System.out.println("[");
                for (int i = 0; i < products.size(); i++) {
                        Product product = products.get(i);
                        System.out.printf("  {\"productId\": %d, \"name\": \"%s\", \"dateSupplied\": \"%s\", "
                                                        + "\"quantityInStock\": %d, \"unitPrice\": %s}%s%n",
                                        product.getProductId(), escapeJson(product.getName()), product.getDateSupplied(),
                                        product.getQuantityInStock(), product.getUnitPrice().toPlainString(),
                                        i < products.size() - 1 ? "," : "");
                }
                System.out.println("]");
        }

        private static void printXml(java.util.List<Product> products) {
                System.out.println("<products>");
                for (Product product : products) {
                        System.out.printf("  <product><productId>%d</productId><name>%s</name><dateSupplied>%s</dateSupplied>"
                                                        + "<quantityInStock>%d</quantityInStock><unitPrice>%s</unitPrice></product>%n",
                                        product.getProductId(), escapeXml(product.getName()), product.getDateSupplied(),
                                        product.getQuantityInStock(), product.getUnitPrice().toPlainString());
                }
                System.out.println("</products>");
        }

        private static void printCsv(java.util.List<Product> products) {
                System.out.println("productId,name,dateSupplied,quantityInStock,unitPrice");
                for (Product product : products) {
                        System.out.printf("%d,%s,%s,%d,%s%n",
                                        product.getProductId(), escapeCsv(product.getName()), product.getDateSupplied(),
                                        product.getQuantityInStock(), product.getUnitPrice().toPlainString());
                }
        }

        private static String escapeJson(String value) {
                return value.replace("\\", "\\\\").replace("\"", "\\\"")
                                .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
        }

        private static String escapeXml(String value) {
                return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                                .replace("\"", "&quot;").replace("'", "&apos;");
        }

        private static String escapeCsv(String value) {
                if (value.contains(",") || value.contains("\"") || value.contains("\n") || value.contains("\r")) {
                        return "\"" + value.replace("\"", "\"\"") + "\"";
                }
                return value;
    }

    private static Product[] existingProducts() {
        return new Product[]{
                new Product(31288, "Banana", LocalDate.parse("2026-01-24"), 124, new BigDecimal("0.55")),
                new Product(19274, "Apple", LocalDate.parse("2025-12-09"), 18, new BigDecimal("1.09")),
                new Product(91899, "Carrot", LocalDate.parse("2026-03-31"), 89, new BigDecimal("2.99")),
                new Product(12345, "Banana", LocalDate.parse("2026-02-13"), 240, new BigDecimal("0.65"))
        };
    }
}