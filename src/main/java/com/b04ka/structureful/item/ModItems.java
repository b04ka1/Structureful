package com.b04ka.structureful.item;

import com.b04ka.structureful.Structureful;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, Structureful.MODID);

    public static final RegistryObject<Item> METEORIC_IRON_INGOT = ITEMS.register("meteoric_iron_ingot",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_METEORIC_IRON = ITEMS.register("raw_meteoric_iron",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEAVY_SWORD = ITEMS.register("heavy_sword",
            ()-> new SwordItem(Tiers.IRON,5,-3F, new  Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);

    }
}
