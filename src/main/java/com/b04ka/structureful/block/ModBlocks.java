package com.b04ka.structureful.block;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.custom.AdvancedFurnaceBlock;
import com.b04ka.structureful.block.custom.EnderoakLogBlock;
import com.b04ka.structureful.block.custom.VolcanicLanternBlock;
import com.b04ka.structureful.block.custom.VolcanoBlock;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.block.state.properties.WoodType;
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

    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDEROAK = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "enderoak"));


    public static final DeferredBlock<Block> METEORIC_IRON_BLOCK = registerBlock("meteoric_iron_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final DeferredBlock<Block> METEORIC_IRON_ORE = registerBlock("meteoric_iron_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE)) {
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
            () -> new AdvancedFurnaceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE).noOcclusion()
                    .lightLevel(BlockState -> BlockState.getValue(AdvancedFurnaceBlock.LIT) ? 13 : 0)));

    public static final DeferredBlock<Block> VOLCANO = registerBlock("volcanic_netherrack",
            () -> new VolcanoBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(4F).mapColor(MapColor.NETHER).sound(SoundType.NETHERRACK).noOcclusion().randomTicks()
                    .lightLevel(BlockState -> BlockState.getValue(VolcanoBlock.ACTIVE) ? 15 : 0)
            ));

    public static final DeferredBlock<Block> VOLCANIC_LANTERN = registerBlock("volcanic_lantern",
            () -> new VolcanicLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).mapColor(MapColor.NETHER).noOcclusion()));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ENDEROAK_LOG = registerBlock("stripped_enderoak_log",
            () -> new RotatedPillarBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.SAND)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(2.0F)
                            .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> ENDEROAK_LOG = registerBlock("enderoak_log",
            () -> new EnderoakLogBlock(BlockBehaviour.Properties.ofFullCopy(STRIPPED_ENDEROAK_LOG.get()).randomTicks()));

    public static final DeferredBlock<LeavesBlock> ENDEROAK_LEAVES = registerBlock("enderoak_leaves",
            () -> new LeavesBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_PURPLE)
                            .strength(0.2F)
                            .randomTicks()
                            .sound(SoundType.AZALEA_LEAVES)
                            .noOcclusion()
                            .isSuffocating((blockState, getter, pos) -> false)
                            .isViewBlocking((blockState, getter, pos) -> false)
                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> ENDEROAK_SAPLING = registerBlock("enderoak_sapling", () -> new SaplingBlock(
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

    public static final DeferredBlock<RotatedPillarBlock> ENDEROAK_WOOD = registerBlock("enderoak_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)) {
                @Override
                public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
                    if (!context.getItemInHand().canPerformAction(itemAbility))
                        return null;
                    if (ItemAbilities.AXE_STRIP == itemAbility) {
                        return STRIPPED_ENDEROAK_WOOD.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                    }
                    return super.getToolModifiedState(state, context, itemAbility, simulate);
                }
            });

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ENDEROAK_WOOD = registerBlock("stripped_enderoak_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<Block> ENDEROAK_PLANKS = registerBlock("enderoak_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final DeferredBlock<Block> ENDEROAK_STAIRS = registerBlock("enderoak_stairs",
            () -> new StairBlock(ENDEROAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));

    public static final DeferredBlock<Block> ENDEROAK_SLAB = registerBlock("enderoak_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    public static final DeferredBlock<Block> ENDEROAK_FENCE = registerBlock("enderoak_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final DeferredBlock<Block> ENDEROAK_FENCE_GATE = registerBlock("enderoak_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
