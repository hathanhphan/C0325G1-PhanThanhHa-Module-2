package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.singleton;

/*
 * Singleton đảm bảo rằng một class chỉ có duy nhất một instance trong toàn bộ ứng dụng
 * và cung cấp một điểm truy cập toàn cục đến instance đó.
 * Pattern này rất hữu ích cho các tài nguyên chia sẻ như kết nối database, logger, hoặc configuration manager.
 * */

/*
*
*   1. Singleton Pattern - ⭐⭐⭐⭐⭐
    Tần suất sử dụng: Rất cao (90% dự án)

    Singleton xuất hiện ở khắp nơi trong lập trình thực tế. Bạn sẽ gặp nó trong:

    Spring Framework: ApplicationContext, Bean Factory
    Android Development: Application class, SharedPreferences
    Database Connection: Connection pools, DAO managers
    Logging Systems: Logger instances (Log4j, Logback)
    Configuration Management: Properties readers, Settings managers
*
* */

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        System.out.println("db1 == db2: " + (db1 == db2)); // true

        db1.executeQuery("SELECT * FROM users");
        db2.executeQuery("SELECT * FROM products");
    }
}
