package me.ciudadela.customitemlibraryfabric;

import net.fabricmc.api.ModInitializer;

public class CustomItemLibrary implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        CustomItemRegistry.getInstance();
    }
}
