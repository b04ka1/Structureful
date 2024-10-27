package com.b04ka.structureful.datagen;

import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.METEORIC_IRON_BLOCK.get());
        this.dropSelf(ModBlocks.ADVANCED_FURNACE.get());
        this.dropSelf(ModBlocks.VOLCANIC_LANTERN.get());
        this.add(ModBlocks.METEORIC_IRON_ORE.get(),
                block -> this.createOreDrop(block, ModItems.RAW_METEORIC_IRON.get()));
        this.dropSelf(ModBlocks.ENDEROAK_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ENDEROAK_LOG.get());
        this.add(ModBlocks.ENDEROAK_LEAVES.get(),
                block -> this.createLeavesDrops(block, Blocks.ACACIA_SAPLING, BlockLootSubProvider.NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.ENDEROAK_SAPLING.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .filter(b -> b != ModBlocks.VOLCANO)
                .map(Holder::value)::iterator;
    }
}
