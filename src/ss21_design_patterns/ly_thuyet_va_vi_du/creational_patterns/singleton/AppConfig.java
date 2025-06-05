package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.singleton;

import java.util.Properties;

public class AppConfig {
    private static volatile AppConfig instance;
    private Properties properties;

    private AppConfig() {
        loadConfiguration();
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    private void loadConfiguration() {
        properties = new Properties();
        // Load từ file config
        System.out.println("Loaded application configuration");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
