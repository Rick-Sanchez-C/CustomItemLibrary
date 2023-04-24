package me.ciudadela.customitemlibrary;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CustomItemRegistry {
    private static CustomItemRegistry instance;

    private final Map<String, SimpleCustomItem> customItems;

    private CustomItemRegistry() {
        customItems = new HashMap<>();
        loadConfig();
    }

    public static CustomItemRegistry getInstance() {
        if (instance == null) {
            instance = new CustomItemRegistry();
        }
        return instance;
    }

    public void registerCustomItem(String key, SimpleCustomItem customItem) {
        customItems.put(key, customItem);
    }

    public SimpleCustomItem getCustomItem(String key) {
        if (customItems.containsKey(key))
            return customItems.get(key);
        return new SimpleCustomItem(key, 0);
    }

    private void loadConfig() {
        Path configDir = Paths.get("config");
        Path configPath = configDir.resolve("customitems.yml");

        createConfigFolderAndFile(configDir, configPath);

        Yaml yaml = new Yaml();
        try (InputStream input = Files.newInputStream(configPath)) {
            Map<String, Map<String, Object>> data = yaml.load(input);

            for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
                registerCustomItemFromData(entry);
            }
        } catch (IOException e) {
            System.err.println("Error loading customitems.yml.");
            e.printStackTrace();
        }
    }

    private void createConfigFolderAndFile(Path configDir, Path configPath) {
        try {
            Files.createDirectories(configDir);
            if (Files.notExists(configPath)) {
                String defaultConfig = "example_item:\n  itemID: \"minecraft:stone\"\n  customModelData: 1000\n";
                Files.write(configPath, defaultConfig.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            System.err.println("Error creating the config folder or customitems.yml file.");
            e.printStackTrace();
        }
    }

    private void registerCustomItemFromData(Map.Entry<String, Map<String, Object>> entry) {
        String key = entry.getKey();
        Map<String, Object> itemData = entry.getValue();

        String itemID = (String) itemData.get("itemID");
        int customModelData = (Integer) itemData.get("customModelData");

        SimpleCustomItem customItem = new SimpleCustomItem(itemID, customModelData);
        registerCustomItem(key, customItem);
    }
}
