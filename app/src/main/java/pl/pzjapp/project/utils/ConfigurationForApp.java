package pl.pzjapp.project.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationForApp {

    Properties properties;

    public ConfigurationForApp(Properties properties) {
        this.properties = properties;
    }

    /**
     * @param propertyName
     * @return
     */
    public String get(String propertyName) {
        return properties.getProperty(propertyName);
    }

    /**
     * @param propertyName
     * @param defa
     * @return
     */
    public String getDefault(String propertyName, String defa) {
        return properties.getProperty(propertyName, defa);
    }

    /**
     * Fabricate method for getting standard test config.
     *
     * @return
     */
    public static ConfigurationForApp fromTestConfigFile() {
        return new ConfigurationForApp(readProperties("/config.properties"));
    }

    public static Properties readProperties(String filePath) {
        Properties propertiesFromFile = new Properties();
        try {
            propertiesFromFile.load(ConfigurationForApp.class.getResourceAsStream(filePath));
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot read test configuration file. Error: " + ex.getMessage(), ex);
        }

        propertiesFromFile.putAll(System.getProperties());
        return propertiesFromFile;
    }
}
