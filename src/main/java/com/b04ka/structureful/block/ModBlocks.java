package com.b04ka.structureful.block;

import com.b04ka.structureful.Structureful;
//import com.b04ka.structureful.block.custom.AdvancedFurnaceBlock;
//import com.b04ka.structureful.block.custom.VolcanoBlock;
import com.b04ka.structureful.block.custom.AdvancedFurnaceBlock;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Structureful.MODID);


    public static final DeferredBlock<Block> METEORIC_IRON_BLOCK= BLOCKS.register("meteoric_iron_block",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));

    public static final DeferredBlock<Block> METEORIC_IRON_ORE= BLOCKS.register("meteoric_iron_ore",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_FURNACE = BLOCKS.register("advanced_furnace",
            () -> new AdvancedFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion().lightLevel(BlockState->BlockState.getValue(AdvancedFurnaceBlock.LIT) ? 13:0)));
//
//    public static final DeferredBlock<Block> VOLCANO = BLOCKS.register("volcanic_netherrack",
//            ()-> new VolcanoBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).sound(SoundType.NETHERRACK).noOcclusion().randomTicks().lightLevel(BlockState->BlockState.getValue(VolcanoBlock.ACTIVE) ? 15:0)));

}
