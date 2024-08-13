package com.b04ka.structureful.block.entity;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Structureful.MODID);

    public static final Supplier<BlockEntityType<VolcanoBlockEntity>> VOLCANO_BE =
            BLOCK_ENTITIES.register("volcano_be", ()-> BlockEntityType.Builder.of(
                    VolcanoBlockEntity::new, ModBlocks.VOLCANO.get()).build(null));

    public static final Supplier<BlockEntityType<AdvancedFurnaceBlockEntity>> ADVANCED_FURNACE_BE =
            BLOCK_ENTITIES.register("advanced_furnace_be", ()-> BlockEntityType.Builder.of(
                    AdvancedFurnaceBlockEntity::new, ModBlocks.ADVANCED_FURNACE.get()).build(null));

}
