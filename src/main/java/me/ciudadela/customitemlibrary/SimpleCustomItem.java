package me.ciudadela.customitemlibrary;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class SimpleCustomItem {
    final String itemID;
    final int CustomModelData;

    public SimpleCustomItem(String itemID) {
        this.itemID = itemID;
        this.CustomModelData = 0;
    }
    public SimpleCustomItem(String itemID, int CustomModelData) {
        this.itemID = itemID;
        this.CustomModelData = CustomModelData;
    }
    public ItemStack createCustomItemStack(int amount) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemID));
        if (item == null) {
            System.out.println("This item: " + itemID + " does not exist.");
            return ItemStack.EMPTY;
        }

        ItemStack stack = new ItemStack(item, amount);
        if (CustomModelData != 0) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putInt("CustomModelData", CustomModelData);
        }
        return stack;
    }
}
