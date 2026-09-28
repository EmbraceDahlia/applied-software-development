package edu.miu.cs.cs489appsd.lab1.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1.productmgmtapp.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

@SpringBootApplication
public class ProductMgmtApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductMgmtApp.class, args);
        printProducts(existingProducts());
    }

    public static void printProducts(Product[] products) {
        System.out.printf("%-10s %-10s %-15s %-18s %s%n",
                "Product ID", "Name", "Date Supplied", "Quantity in Stock", "Unit Price");
        System.out.println("-----------------------------------------------------------------------");

        Arrays.stream(products)
                .sorted(Comparator.comparing(Product::getDateSupplied)
                        .thenComparingLong(Product::getProductId))
                .forEach(product -> System.out.printf("%-10d %-10s %-15s %-18d $%.2f%n",
                        product.getProductId(), product.getName(), product.getDateSupplied(),
                        product.getQuantityInStock(), product.getUnitPrice()));
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