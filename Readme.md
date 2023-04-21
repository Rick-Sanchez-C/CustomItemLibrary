# CustomItemLibrary

CustomItemLibrary is a Minecraft Forge library mod that provides an easy way to define and register custom items with specific properties, such as custom model data. This library allows server owners to manage custom items through a unique user-friendly YAML configuration file for all the plugins.
## Features

- Define custom items with item ID, amount, and custom model data through a YAML configuration file
- Automatically load custom items during mod initialization
- Expose custom items registry for access and usage by other mods

## Usage

1. Add SimpleCustomItem as a dependency in your mod's `build.gradle` file:

```gradle
dependencies {
    implementation fg.deobf("com.example:SimpleCustomItem:1.0.0")
}
```
Replace com.example and 1.0.0 with the correct group ID and version of SimpleCustomItem.

2. Create a customitems.yml file in your mod's config directory and define your custom items following this format:
    
    ```yaml
    custom_item_key_1:
        itemID: "minecraft:stone"
        amount: 5
        customModelData: 2000
    custom_item_key_2:
        itemID: "minecraft:diamond"
        amount: 2
        customModelData: 3000
   ```
   Replace custom_item_key_* with unique keys for your custom items, and set their properties accordingly.


3. Access the custom items registry from your mod's code and use the custom items:
    ```java
    CustomItemRegistry registry = CustomItemRegistry.getInstance();
    SimpleCustomItem customItem = registry.getCustomItem("custom_item_key_1");
    ```
    Replace custom_item_key_1 with the key of the custom item you want to get the item stack of.


4. Use the custom items in your mod as needed. For example, create an ItemStack with a custom item:
    ```java
    ItemStack customItemStack = customItem.createCustomItemStack();
    ```    
# License
   This project is licensed under the MIT License. See the LICENSE file for details.