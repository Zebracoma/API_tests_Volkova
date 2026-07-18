package api_dadata.config;

import java.util.ResourceBundle;

public class ConfigProvider {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String get(String key) {
        return bundle.getString(key);
    }
}