package GenericLibraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static Properties getProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/Config.properties");
            properties.load(file);
        }
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
