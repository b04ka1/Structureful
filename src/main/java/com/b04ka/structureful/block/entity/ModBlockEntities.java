package com.b04ka.structureful.block.entity;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Structureful.MODID);

    public static final RegistryObject<BlockEntityType<AdvancedFurnaceBlockEntity>> ADVANCED_FURNACE_BE =
            BLOCK_ENTITIES.register("advanced_furnace_be", () ->
                    BlockEntityType.Builder.of(AdvancedFurnaceBlockEntity::new,
                            ModBlocks.ADVANCED_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<VolcanoBlockEntity>> VOLCANO_BE =
            BLOCK_ENTITIES.register("volcano_be", () ->
                    BlockEntityType.Builder.of(VolcanoBlockEntity::new,
                            ModBlocks.VOLCANO.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
