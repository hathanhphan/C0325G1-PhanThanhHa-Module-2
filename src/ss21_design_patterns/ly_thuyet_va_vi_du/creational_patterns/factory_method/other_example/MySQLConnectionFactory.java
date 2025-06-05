package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.other_example;

public class MySQLConnectionFactory extends DatabaseConnectionFactory {
    @Override
    public Connection createConnection() {
        return null;
    }
}
