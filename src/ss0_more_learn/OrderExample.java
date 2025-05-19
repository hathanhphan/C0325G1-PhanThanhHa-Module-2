package ss0_more_learn;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product{name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}

class Order {
    private int id;
    private List<Product> products;
    private LocalDate orderDate;

    public Order(int id, List<Product> products, LocalDate orderDate) {
        this.id = id;
        this.products = products;
        this.orderDate = orderDate;
    }

    public int getId() { return id; }
    public List<Product> getProducts() { return products; }
    public LocalDate getOrderDate() { return orderDate; }
    public double getTotalAmount() { return products.stream().mapToDouble(Product::getPrice).sum(); }

    @Override
    public String toString() {
        return "Order{id=" + id + ", products=" + products.size() + ", orderDate=" + orderDate + "}";
    }
}

public class OrderExample {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Phone", "Electronics", 800.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Shirt", "Clothing", 35.0),
                new Product("Jeans", "Clothing", 60.0),
                new Product("Book", "Books", 25.0),
                new Product("Tablet", "Electronics", 500.0)
        );

        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(products.get(0), products.get(2)), LocalDate.of(2023, 1, 15)),
                new Order(2, Arrays.asList(products.get(1), products.get(4), products.get(5)), LocalDate.of(2023, 2, 2)),
                new Order(3, Arrays.asList(products.get(3), products.get(4)), LocalDate.of(2023, 2, 10)),
                new Order(4, Arrays.asList(products.get(0), products.get(6)), LocalDate.of(2023, 3, 5)),
                new Order(5, Arrays.asList(products.get(2), products.get(5)), LocalDate.of(2023, 3, 20))
        );

        // 1. Tính tổng doanh thu
        double totalRevenue = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
        System.out.println("Total revenue: $" + totalRevenue);

        // 2. Tìm sản phẩm đắt nhất mỗi danh mục
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))
                ));
        System.out.println("Most expensive product by category:");
        mostExpensiveByCategory.forEach((category, product) ->
                product.ifPresent(p -> System.out.println(category + ": " + p.getName() + " - $" + p.getPrice()))
        );

        // 3. Tính doanh thu theo tháng
        Map<Month, Double> revenueByMonth = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().getMonth(),
                        Collectors.summingDouble(Order::getTotalAmount)
                ));
        System.out.println("Revenue by month:");
        revenueByMonth.forEach((month, revenue) ->
                System.out.println(month + ": $" + revenue)
        );

        // 4. Tìm đơn hàng có giá trị cao nhất
        orders.stream()
                .max(Comparator.comparing(Order::getTotalAmount))
                .ifPresent(order -> System.out.println("Highest value order: " + order + ", Amount: $" + order.getTotalAmount()));

        // 5. Đếm số lượng sản phẩm đã bán theo danh mục
        Map<String, Long> productCountByCategory = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()
                ));
        System.out.println("Product count by category: " + productCountByCategory);

        // 6. Tính giá trung bình của sản phẩm theo danh mục
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)
                ));
        System.out.println("Average price by category:");
        averagePriceByCategory.forEach((category, avgPrice) ->
                System.out.println(category + ": $" + avgPrice)
        );

        // 7. Tìm những đơn hàng trong tháng 2
        List<Order> februaryOrders = orders.stream()
                .filter(order -> order.getOrderDate().getMonth() == Month.FEBRUARY)
                .collect(Collectors.toList());
        System.out.println("February orders:");
        februaryOrders.forEach(order ->
                System.out.println("Order " + order.getId() + ": $" + order.getTotalAmount())
        );

        // 8. Phân loại đơn hàng theo giá trị (cao, trung bình, thấp)
        Map<String, List<Order>> ordersByValueCategory = orders.stream()
                .collect(Collectors.groupingBy(order -> {
                    double amount = order.getTotalAmount();
                    if (amount > 1000) return "High";
                    else if (amount > 500) return "Medium";
                    else return "Low";
                }));
        System.out.println("Orders by value category:");
        ordersByValueCategory.forEach((category, orderList) -> {
            System.out.println(category + " value orders:");
            orderList.forEach(order -> System.out.println("  Order " + order.getId() + ": $" + order.getTotalAmount()));
        });
    }
}

