package com.b04ka.structureful.block;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.custom.AdvancedFurnaceBlock;
import com.b04ka.structureful.block.custom.VolcanoBlock;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Structureful.MODID);



    public static final RegistryObject<Block> METEORIC_IRON_BLOCK= registerBlock("meteoric_iron_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));

    public static final RegistryObject<Block> METEORIC_IRON_ORE= registerBlock("meteoric_iron_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).sound(SoundType.STONE)));

    public static final RegistryObject<Block> ADVANCED_FURNACE = registerBlock("advanced_furnace",
            () -> new AdvancedFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().lightLevel(BlockState->BlockState.getValue(AdvancedFurnaceBlock.LIT) ? 13:0)));

    public static final RegistryObject<Block> VOLCANO = registerBlock("volcanic_netherrack",
            ()-> new VolcanoBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).sound(SoundType.NETHERRACK).noOcclusion().randomTicks().lightLevel(BlockState->BlockState.getValue(VolcanoBlock.ACTIVE) ? 15:0)));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),new Item.Properties()));
    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
