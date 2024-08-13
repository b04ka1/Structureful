package com.b04ka.structureful.datagen;

import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.METEORIC_IRON_BLOCK.get());
        this.dropSelf(ModBlocks.ADVANCED_FURNACE.get());
        this.add(ModBlocks.METEORIC_IRON_ORE.get(),
                block -> createOreDrop(ModBlocks.METEORIC_IRON_ORE.get(), ModItems.RAW_METEORIC_IRON.get()));


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .filter( b-> b != ModBlocks.VOLCANO)
                .map(Holder::value)::iterator;
    }
}
