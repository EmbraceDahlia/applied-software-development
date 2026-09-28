package edu.miu.cs.cs489appsd.lab1.productmgmtapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private final long productId;
    private final String name;
    private final LocalDate dateSupplied;
    private final int quantityInStock;
    private final BigDecimal unitPrice;

    public Product(long productId, String name, LocalDate dateSupplied,
                   int quantityInStock, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateSupplied() {
        return dateSupplied;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}