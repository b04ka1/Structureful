package com.b04ka.structureful.item;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModItems {


    public static final DeferredRegister.Items ITEMS= DeferredRegister.createItems(Structureful.MODID);

    public static final DeferredItem<Item> METEORIC_IRON_INGOT = ITEMS.registerSimpleItem("meteoric_iron_ingot", new Item.Properties());
    public static final DeferredItem<Item> RAW_METEORIC_IRON = ITEMS.registerSimpleItem("raw_meteoric_iron", new Item.Properties());
    public static final Supplier<SwordItem> HEAVY_SWORD = ITEMS.register("heavy_sword",
            ()-> new SwordItem(Tiers.IRON, new  Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 5, -3F))));


    public static final DeferredItem<BlockItem> METEORIC_IRON_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("meteoric_iron_block",ModBlocks.METEORIC_IRON_BLOCK);
    public static final DeferredItem<BlockItem> METEORIC_IRON_ORE = ITEMS.registerSimpleBlockItem("meteoric_iron_ore",ModBlocks.METEORIC_IRON_ORE);

    }


