package com.simbirsoft.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigProvider.class);
    public static Properties properties;

    public static String getProperty(String key) {
        String value = null;
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("src/test/resources/config.properties"));
                value = properties.getProperty(key);
            } catch (IOException e) {
                LOGGER.error("Файл свойств отсутствует!");
                e.printStackTrace();
            }
        }
        return value;
    }
}
