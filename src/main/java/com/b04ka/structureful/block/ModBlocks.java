package com.b04ka.structureful.block;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.custom.AdvancedFurnaceBlock;
import com.b04ka.structureful.block.custom.EnderoakLogBlock;
import com.b04ka.structureful.block.custom.VolcanicLanternBlock;
import com.b04ka.structureful.block.custom.VolcanoBlock;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Structureful.MODID);

    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDEROAK = FeatureUtils.createKey("enderoak");


    public static final DeferredBlock<Block> METEORIC_IRON_BLOCK = registerBlock("meteoric_iron_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL)));

    public static final DeferredBlock<Block> METEORIC_IRON_ORE = registerBlock("meteoric_iron_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE).sound(SoundType.STONE)) {
                @Override
                public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    if (Screen.hasShiftDown()) {
                        pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information_pressed"));
                        pTooltipComponents.add(Component.translatable("tooltips.structureful.meteoric_iron_ore"));
                    } else {
                        pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information"));
                    }

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final DeferredBlock<Block> ADVANCED_FURNACE = registerBlock("advanced_furnace",
            () -> new AdvancedFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()
                    .sound(SoundType.STONE).lightLevel(BlockState -> BlockState.getValue(AdvancedFurnaceBlock.LIT) ? 13 : 0)));

    public static final DeferredBlock<Block> VOLCANO = registerBlock("volcanic_netherrack",
            () -> new VolcanoBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(4F).mapColor(MapColor.NETHER).sound(SoundType.NETHERRACK).noOcclusion().randomTicks()
                    .lightLevel(BlockState -> BlockState.getValue(VolcanoBlock.ACTIVE) ? 15 : 0)
            ));

    public static final DeferredBlock<Block> VOLCANIC_LANTERN = registerBlock("volcanic_lantern",
            () -> new VolcanicLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).mapColor(MapColor.NETHER).noOcclusion()));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ENDEROAK_LOG = registerBlock("stripped_enderoak_log",
            ModBlocks::enderoakLog);

    public static final DeferredBlock<Block> ENDEROAK_LOG = registerBlock("enderoak_log",
            ()-> new EnderoakLogBlock(BlockBehaviour.Properties.ofFullCopy(STRIPPED_ENDEROAK_LOG.get()).randomTicks()));

    public static final DeferredBlock<LeavesBlock> ENDEROAK_LEAVES = registerBlock("enderoak_leaves",
            ModBlocks::enderOakLeaves);

    public static final DeferredBlock<Block> ENDEROAK_SAPLING = registerBlock("enderoak_sapling", ()-> new SaplingBlock(
            new TreeGrower("enderoak",
                    Optional.empty(),
                    Optional.of(ENDEROAK),
                    Optional.empty()),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RotatedPillarBlock enderoakLog() {
        return new RotatedPillarBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.SAND)
                        .instrument(NoteBlockInstrument.BASS)
                        .strength(2.0F)
                        .sound(SoundType.NETHER_WOOD)
        ) {
            @Override
            public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                ItemStack itemStack = context.getItemInHand();
                if (!itemStack.canPerformAction(itemAbility))
                    return null;
                if (ItemAbilities.AXE_STRIP == itemAbility) {
                    if (this == ENDEROAK_LOG.get()) {
                        return STRIPPED_ENDEROAK_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                    }
                }
                return super.getToolModifiedState(state, context, itemAbility, simulate);
            }
        };
    }

    private static LeavesBlock enderOakLeaves() {
        return new LeavesBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_PURPLE)
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.AZALEA_LEAVES)
                .noOcclusion()
                .isSuffocating((blockState, getter, pos) -> false)
                .isViewBlocking((blockState, getter, pos) -> false)
                .pushReaction(PushReaction.DESTROY));
    }
}
