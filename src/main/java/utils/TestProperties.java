package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private TestProperties() {}

    private static FileInputStream fileInputStream;
    public static Properties PROPERTIES;

    static {
        try {
            PROPERTIES = new Properties();

            //load environment properties
            fileInputStream = new FileInputStream("target/classes/properties/consultant.properties");
            PROPERTIES.load(fileInputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String getTestProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("protocol") + "://" + PROPERTIES.getProperty("host");
    }

    public static void setTestProperty(String key, String value) {
        PROPERTIES.setProperty(key, value);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(PROPERTIES.getProperty(key));
    }
}