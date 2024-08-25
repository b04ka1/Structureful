package com.b04ka.structureful.block;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.custom.AdvancedFurnaceBlock;
import com.b04ka.structureful.block.custom.VolcanicLanternBlock;
import com.b04ka.structureful.block.custom.VolcanoBlock;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Structureful.MODID);


    public static final DeferredBlock<Block> METEORIC_IRON_BLOCK= registerBlock("meteoric_iron_block",
            ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));

    public static final DeferredBlock<Block> METEORIC_IRON_ORE= registerBlock("meteoric_iron_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE).sound(SoundType.STONE)){
                @Override
                public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    if(Screen.hasShiftDown()) {
                        pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information_pressed"));
                        pTooltipComponents.add(Component.translatable("tooltips.structureful.meteoric_iron_ore"));
                    } else {
                        pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information"));
                    }

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }});

    public static final DeferredBlock<Block> ADVANCED_FURNACE = registerBlock("advanced_furnace",
               () -> new AdvancedFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()
                       .sound(SoundType.STONE).lightLevel(BlockState->BlockState.getValue(AdvancedFurnaceBlock.LIT) ? 13:0)));

    public static final DeferredBlock<Block> VOLCANO = registerBlock("volcanic_netherrack",
            ()-> new VolcanoBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(4F).mapColor(MapColor.NETHER).sound(SoundType.NETHERRACK).noOcclusion().randomTicks()
                    .lightLevel(BlockState->BlockState.getValue(VolcanoBlock.ACTIVE) ? 15:0)
            ));

    public static final DeferredBlock<Block> VOLCANIC_LANTERN = registerBlock("volcanic_lantern",
            ()-> new VolcanicLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).mapColor(MapColor.NETHER).noOcclusion()));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
