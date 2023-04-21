package me.ciudadela.customitemlibrary;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CustomItemLibrary.MODID)
public class CustomItemLibrary {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "customitemlibrary";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public CustomItemLibrary() {
        // Registra el evento de inicialización del mod
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Carga los elementos personalizados desde el archivo customitems.yml
        loadCustomItems();
    }
    private void loadCustomItems() {
        // Ruta del archivo customitems.yml
        Path configPath = Paths.get("ItemLibrary/customitems.yml");

        Yaml yaml = new Yaml();
        try (InputStream input = Files.newInputStream(configPath)) {
            Map<String, Map<String, Object>> data = yaml.load(input);

            for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
                String key = entry.getKey();
                Map<String, Object> itemData = entry.getValue();

                String itemID = (String) itemData.get("itemID");
                int amount = (Integer) itemData.get("amount");
                int customModelData = (Integer) itemData.get("customModelData");

                SimpleCustomItem customItem = new SimpleCustomItem(itemID, amount, customModelData);
                CustomItemRegistry.getInstance().registerCustomItem(key, customItem);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar el archivo customitems.yml");
            e.printStackTrace();
        }
    }
}
