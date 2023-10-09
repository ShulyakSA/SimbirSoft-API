package com.simbirsoft.config;
/**
 * Класс ConfigProvider реализует получение переменных из файла config.properties через метод getProperty
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ConfigProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigProvider.class);
    public static Properties properties;

    /**
     * @param key название переменной тип String
     * @return значение переменной
     */
    public static String getProperty(String key) {
        String value = null;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("target/test-classes/config.properties"));
            value = properties.getProperty(key);
        } catch (IOException e) {
            LOGGER.error("Файл свойств отсутствует!");
            e.printStackTrace();
        }
        return value;
    }
}
