package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.singleton;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;

    // Constructor private để ngăn việc tạo instance từ bên ngoài
    private DatabaseConnection() {
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Khởi tạo kết nối database: " + connectionString);
    }

    // Thread-safe singleton implementation
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Thực thi query: " + query + " trên " + connectionString);
    }
}
