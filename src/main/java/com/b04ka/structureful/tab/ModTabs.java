package com.b04ka.structureful.tab;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Structureful.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STRUCTUREFUL_TAB= CREATIVE_MODE_TABS.register("structureful_tab",
            ()-> CreativeModeTab.builder().icon(()-> new  ItemStack(ModItems.METEORIC_IRON_INGOT.get()))
                    .title(Component.translatable("creative_tab.structureful.structureful"))
                    .displayItems(((displayParameters, output) -> {
                        output.accept(ModItems.RAW_METEORIC_IRON.get());
                        output.accept(ModItems.METEORIC_IRON_INGOT.get());
                        output.accept(ModItems.HEAVY_SWORD.get());
                        output.accept(ModBlocks.METEORIC_IRON_ORE.get());
                        output.accept(ModBlocks.METEORIC_IRON_BLOCK.get());
                        output.accept(ModBlocks.ADVANCED_FURNACE.get());
                        output.accept(ModItems.VOLCANIC_SHARD.get());
                        output.accept(ModItems.VOLCANIC_CORE.get());
                        output.accept(ModItems.BLAZE_STAFF.get());
                        output.accept(ModBlocks.VOLCANO.get());


                    }))
                    .build());
}
