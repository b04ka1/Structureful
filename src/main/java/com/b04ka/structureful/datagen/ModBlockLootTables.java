package com.b04ka.structureful.datagen;

import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
                block -> this.createEnderoakLeavesDrops());
        this.dropSelf(ModBlocks.ENDEROAK_SAPLING.get());
        this.dropSelf(ModBlocks.ENDEROAK_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ENDEROAK_WOOD.get());
        this.dropSelf(ModBlocks.ENDEROAK_PLANKS.get());
        this.dropSelf(ModBlocks.ENDEROAK_STAIRS.get());
        this.add(ModBlocks.ENDEROAK_SLAB.get(),
                this::createSlabItemTable);
        this.dropSelf(ModBlocks.ENDEROAK_FENCE.get());
        this.dropSelf(ModBlocks.ENDEROAK_FENCE_GATE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .filter(b -> b != ModBlocks.VOLCANO)
                .map(Holder::value)::iterator;
    }

    protected LootTable.Builder createEnderoakLeavesDrops() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(ModBlocks.ENDEROAK_LEAVES.get(), ModBlocks.ENDEROAK_SAPLING.get(), BlockLootSubProvider.NORMAL_LEAVES_SAPLING_CHANCES)
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(HAS_SHEARS.or(this.hasSilkTouch()).invert())
                                .add(
                                        ((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(ModBlocks.ENDEROAK_LEAVES.get(), LootItem.lootTableItem(ModItems.ENDERAPPLE)))
                                                .when(
                                                        BonusLevelTableCondition.bonusLevelFlatChance(
                                                                registrylookup.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F
                                                        )
                                                )
                                )
                );
    }
}
