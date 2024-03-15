package com.b04ka.structureful.tab;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Structureful.MODID);

    public static final RegistryObject<CreativeModeTab> MODERNIZATION_TAB= CREATIVE_MODE_TABS.register("structureful_tab",
            ()-> CreativeModeTab.builder().icon(()-> new  ItemStack(ModItems.METEORIC_IRON_INGOT.get()))
                    .title(Component.translatable("creativetab.structureful_tab"))
                    .displayItems(((p_270258_, p_259752_) -> {
                        p_259752_.accept(ModItems.RAW_METEORIC_IRON.get());
                        p_259752_.accept(ModItems.METEORIC_IRON_INGOT.get());
                        p_259752_.accept(ModItems.HEAVY_SWORD.get());
                        p_259752_.accept(ModBlocks.METEORIC_IRON_ORE.get());
                        p_259752_.accept(ModBlocks.METEORIC_IRON_BLOCK.get());
                        p_259752_.accept(ModBlocks.ADVANCED_FURNACE.get());
                        p_259752_.accept(ModBlocks.VOLCANO.get());


                    }))
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
