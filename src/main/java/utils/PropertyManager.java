package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyManager {
    private final String propertyFilePath;
    private Properties prop;

    public PropertyManager() {
        propertyFilePath = System.getProperty("user.dir") + "/src/main/resources/application.properties";
        log.debug(String.format("Reading properties from %s", propertyFilePath));
        loadData();
    }

    private void loadData() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String propertyName) {
        String finalProperty;
        if(StringUtils.isEmpty(System.getProperty(propertyName))) {
            log.debug(String.format("Getting property from FILE by name %s. Value: %s", propertyName, prop.getProperty(propertyName)));
            finalProperty = prop.getProperty(propertyName);
        } else {
            log.debug(String.format("Getting property from SYSTEM by name %s. Value: %s", propertyName, System.getProperty(propertyName)));
            finalProperty = System.getProperty(propertyName);
        }
        return finalProperty;
    }
}