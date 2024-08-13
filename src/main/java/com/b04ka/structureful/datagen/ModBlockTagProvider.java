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
//                .add(ModBlocks.ADVANCED_FURNACE.get())
                .add(ModBlocks.VOLCANO.get());


        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.METEORIC_IRON_BLOCK.get())
//                .add(ModBlocks.ADVANCED_FURNACE.get())
                .add(ModBlocks.VOLCANO.get());


        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.METEORIC_IRON_ORE.get());

    }
}
