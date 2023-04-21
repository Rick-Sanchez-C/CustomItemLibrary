package me.ciudadela.customitemlibraryfabric;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SimpleCustomItem {
    final String itemID;
    final int amount;
    final int customModelData;

    public SimpleCustomItem(String itemID, int amount, int customModelData) {
        this.itemID = itemID;
        this.amount = amount;
        this.customModelData = customModelData;
    }

    public ItemStack createCustomItemStack() {
        Item item = Registry.ITEM.get(new Identifier(itemID));
        if (item == null) {
            System.out.println("El item con ID " + itemID + " no existe.");
            return ItemStack.EMPTY;
        }

        ItemStack stack = new ItemStack(item, amount);
        if (customModelData != 0) {
            NbtCompound tag = stack.getOrCreateNbt();
            tag.putInt("CustomModelData", customModelData);
        }
        return stack;
    }
}