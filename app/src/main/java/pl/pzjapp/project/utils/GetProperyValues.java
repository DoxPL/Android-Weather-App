package pl.pzjapp.project.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperyValues {
    public GetProperyValues() {
    }

    public Properties getPropertyValues(String value) throws IOException {

        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("Property file " + propFileName + "not for");
        }
        return prop;//todo finish the properties
    }

}
