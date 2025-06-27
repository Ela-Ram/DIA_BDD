package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
    }

    // Get String property
    public static String getProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }

    // Get Integer property
    public static int getIntProperty(String key) {
        loadProperties();
        String value = properties.getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Value for key '" + key + "' is not a valid integer: " + value);
        }
    }

    // Set property safely
    public static void setProperty(String key, String value) {
        loadProperties();
        properties.setProperty(key, value);
    }
}
