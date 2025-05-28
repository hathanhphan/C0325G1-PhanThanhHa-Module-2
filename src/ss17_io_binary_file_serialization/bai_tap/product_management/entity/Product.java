package ss17_io_binary_file_serialization.bai_tap.product_management.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;
    private String brandName;
    private String description;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, String brandName, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brandName = brandName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brandName='" + brandName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
