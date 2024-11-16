package com.b04ka.structureful.datagen;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Structureful.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.METEORIC_IRON_BLOCK.get())
                .add(ModBlocks.METEORIC_IRON_ORE.get())
                .add(ModBlocks.ADVANCED_FURNACE.get())
                .add(ModBlocks.VOLCANO.get())
                .add(ModBlocks.VOLCANIC_LANTERN.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.ENDEROAK_LOG.get())
                .add(ModBlocks.STRIPPED_ENDEROAK_LOG.get())
                .add(ModBlocks.ENDEROAK_WOOD.get())
                .add(ModBlocks.STRIPPED_ENDEROAK_WOOD.get())
                .add(ModBlocks.ENDEROAK_PLANKS.get())
                .add(ModBlocks.ENDEROAK_STAIRS.get())
                .add(ModBlocks.ENDEROAK_SLAB.get())
                .add(ModBlocks.ENDEROAK_FENCE.get())
                .add(ModBlocks.ENDEROAK_FENCE_GATE.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.ENDEROAK_LEAVES.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.METEORIC_IRON_BLOCK.get())
                .add(ModBlocks.ADVANCED_FURNACE.get())
                .add(ModBlocks.VOLCANO.get())
                .add(ModBlocks.VOLCANIC_LANTERN.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.METEORIC_IRON_ORE.get())
                .add(ModBlocks.ENDEROAK_LOG.get())
                .add(ModBlocks.STRIPPED_ENDEROAK_LOG.get())
                .add(ModBlocks.ENDEROAK_WOOD.get())
                .add(ModBlocks.STRIPPED_ENDEROAK_WOOD.get())
                .add(ModBlocks.ENDEROAK_PLANKS.get())
                .add(ModBlocks.ENDEROAK_STAIRS.get())
                .add(ModBlocks.ENDEROAK_SLAB.get())
                .add(ModBlocks.ENDEROAK_FENCE.get())
                .add(ModBlocks.ENDEROAK_FENCE_GATE.get());

        this.tag(BlockTags.LOGS)
                .add(ModBlocks.ENDEROAK_LOG.get())
                .add(ModBlocks.STRIPPED_ENDEROAK_LOG.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.ENDEROAK_LEAVES.get());

        this.tag(BlockTags.SAPLINGS)
                .add(ModBlocks.ENDEROAK_SAPLING.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.ENDEROAK_PLANKS.get());

        this.tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.ENDEROAK_STAIRS.get());

        this.tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.ENDEROAK_SLAB.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.ENDEROAK_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.ENDEROAK_FENCE_GATE.get());
    }
}
