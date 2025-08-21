package framework.core;

import framework.config.FrameworkConfig;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private static FrameworkConfig CFG = ConfigFactory.create(FrameworkConfig.class, System.getProperties(), System.getenv());

    public static FrameworkConfig cfg() {
        return CFG;
    }

    // Helpful helper for masking secrets in logs
    public static String mask(String value) {
        if (value == null) return "";
        if (value.length() <= 4) return "****";
        return value.substring(0, 2) + "****" + value.substring(value.length()-2);
    }
}
