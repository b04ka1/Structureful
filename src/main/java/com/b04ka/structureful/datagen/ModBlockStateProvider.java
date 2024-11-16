package com.b04ka.structureful.datagen;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
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
        plant(ModBlocks.ENDEROAK_SAPLING);
        woodBlock(ModBlocks.ENDEROAK_WOOD.get(), ModBlocks.ENDEROAK_LOG.get());
        woodBlock(ModBlocks.STRIPPED_ENDEROAK_WOOD.get(), ModBlocks.STRIPPED_ENDEROAK_LOG.get());
        blockWithItem(ModBlocks.ENDEROAK_PLANKS);
        stairsBlockWithItem(ModBlocks.ENDEROAK_STAIRS.get(), ModBlocks.ENDEROAK_PLANKS.get());
        slabBlockWithItem(ModBlocks.ENDEROAK_SLAB.get(), ModBlocks.ENDEROAK_PLANKS.get());
        fenceBlockWithItem(ModBlocks.ENDEROAK_FENCE.get(), ModBlocks.ENDEROAK_PLANKS.get());
        fenceGateBlockWithItem(ModBlocks.ENDEROAK_FENCE_GATE.get(), ModBlocks.ENDEROAK_PLANKS.get());
    }

    private void fenceBlockWithItem(Block block, Block planksBlock) {
        fenceBlock((FenceBlock) block, blockTexture(planksBlock));
        fenceItem(block, planksBlock);
    }

    private void fenceGateBlockWithItem(Block block, Block planksBlock) {
        fenceGateBlock((FenceGateBlock) block, blockTexture(planksBlock));
        blockItem(block);
    }

    private void stairsBlockWithItem(Block block, Block planksBlock) {
        stairsBlock((StairBlock) block, blockTexture(planksBlock));
        blockItem(block);
    }

    private void slabBlockWithItem(Block block, Block planksBlock) {
        slabBlock((SlabBlock) block, blockTexture(planksBlock), blockTexture(planksBlock));
        blockItem(block);
    }

    private void woodBlock(RotatedPillarBlock block, Block logBlock) {
        axisBlock(block, blockTexture(logBlock), blockTexture(logBlock));
        blockItem(block);
    }

    public void blockItem(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(Structureful.MODID + ":block/" + BuiltInRegistries.BLOCK.getKey(block).getPath()));
    }

    public void blockItem(Block block, String suffix) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(Structureful.MODID + ":block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + suffix));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void leavesBlockWithItem(DeferredBlock<LeavesBlock> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void logBlockItem(DeferredBlock<RotatedPillarBlock> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Structureful.MODID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath()));
    }

    private void plant(DeferredBlock<Block> deferredBlock) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(deferredBlock.get());
        BlockModelBuilder model = models().cross(key.getPath(),
                ResourceLocation.fromNamespaceAndPath(key.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + key.getPath())).renderType("cutout");
        simpleBlock(deferredBlock.get(), model);
        itemModels().getBuilder(key.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(key.getNamespace(), "block/" + key.getPath()));
    }

    private void fenceItem(Block fence, Block material) {
        itemModels().fenceInventory(BuiltInRegistries.BLOCK.getKey(fence).getPath(), modLoc("block/" + BuiltInRegistries.BLOCK.getKey(material).getPath()));
    }
}
