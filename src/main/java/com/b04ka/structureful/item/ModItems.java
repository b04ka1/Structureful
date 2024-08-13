package com.b04ka.structureful.item;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.item.custom.BlazeStaffItem;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModItems {


    public static final DeferredRegister.Items ITEMS= DeferredRegister.createItems(Structureful.MODID);

    public static final DeferredItem<Item> METEORIC_IRON_INGOT = ITEMS.registerSimpleItem("meteoric_iron_ingot", new Item.Properties());
    public static final DeferredItem<Item> RAW_METEORIC_IRON = ITEMS.registerSimpleItem("raw_meteoric_iron", new Item.Properties());
    public static final Supplier<SwordItem> HEAVY_SWORD = ITEMS.register("heavy_sword",
            ()-> new SwordItem(Tiers.IRON, new  Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 6, -3F))));
    public static final DeferredItem<Item> VOLCANIC_SHARD = ITEMS.registerSimpleItem("volcanic_shard", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> VOLCANIC_CORE = ITEMS.registerSimpleItem("volcanic_core", new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON));
    public static final DeferredItem<Item> BLAZE_STAFF = ITEMS.register("blaze_staff", ()-> new BlazeStaffItem(new Item.Properties().fireResistant().stacksTo(1).rarity(Rarity.UNCOMMON)));

}


