package me.ciudadela.customitemlibrary;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class SimpleCustomItem {
    final String itemID;
    final int amount;
    final int CustomModelData;

    public SimpleCustomItem(String itemID, int amount, int CustomModelData) {
        this.itemID = itemID;
        this.amount = amount;
        this.CustomModelData = CustomModelData;
    }
    private ItemStack createCustomItemStack(String itemID, int amount, int customModelData) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemID));
        if (item == null) {
            System.out.println("El item con ID " + itemID + " no existe.");
            return ItemStack.EMPTY;
        }

        ItemStack stack = new ItemStack(item, amount);
        if (customModelData != 0) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putInt("CustomModelData", customModelData);
        }
        return stack;
    }
}
