package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.other_example;

/*
*
*   2. Factory Method Pattern - ⭐⭐⭐⭐⭐
    Tần suất sử dụng: Rất cao (85% dự án)

    Factory Method là backbone của nhiều framework lớn:

    Spring Framework: BeanFactory, FactoryBean
    JDBC: DriverManager.getConnection()
    Java Collections: Collections.synchronizedList(), Arrays.asList()
    Web Services: Service locators, DAO factories
*
* */

public abstract class DatabaseConnectionFactory {
    public abstract Connection createConnection();

    public static DatabaseConnectionFactory getFactory(String dbType) {
        return switch (dbType.toLowerCase()) {
            case "mysql" -> new MySQLConnectionFactory();
            case "postgresql" -> new PostgreSQLConnectionFactory();
            case "oracle" -> new OracleConnectionFactory();
            default -> throw new IllegalArgumentException("Unsupported database: " + dbType);
        };
    }
}
