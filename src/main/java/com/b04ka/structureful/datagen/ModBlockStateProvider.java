package com.b04ka.structureful.datagen;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Structureful.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.METEORIC_IRON_BLOCK);
        blockWithItem(ModBlocks.METEORIC_IRON_ORE);
        logBlock(ModBlocks.STRIPPED_ENDEROAK_LOG.get());
        logBlockItem(ModBlocks.STRIPPED_ENDEROAK_LOG);
        leavesBlockWithItem(ModBlocks.ENDEROAK_LEAVES);

    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void leavesBlockWithItem(DeferredBlock<LeavesBlock> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void logBlockItem(DeferredBlock<RotatedPillarBlock> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Structureful.MODID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath()));
    }
}
