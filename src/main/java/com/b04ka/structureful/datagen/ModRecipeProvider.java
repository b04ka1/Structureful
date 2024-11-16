package com.b04ka.structureful.datagen;


import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.item.ModItems;
import com.b04ka.structureful.recipe.AdvancedFurnaceRecipe;
import com.b04ka.structureful.recipe.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    List<ItemLike> METEORIC_IRON_SMELTABLES = List.of(
            ModItems.RAW_METEORIC_IRON, ModBlocks.METEORIC_IRON_ORE);

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HEAVY_SWORD.get())
                .pattern(" ##")
                .pattern("###")
                .pattern("0# ")
                .define('0', Items.STICK)
                .define('#', ModItems.METEORIC_IRON_INGOT.get())
                .unlockedBy("has_meteoric_iron_ingot", has(ModItems.METEORIC_IRON_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.METEORIC_IRON_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.METEORIC_IRON_INGOT.get())
                .unlockedBy("has_meteoric_iron_ingot", has(ModItems.METEORIC_IRON_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ADVANCED_FURNACE.get())
                .pattern("#0#")
                .pattern("###")
                .define('#', ModItems.METEORIC_IRON_INGOT.get())
                .define('0', Items.BLAST_FURNACE)
                .unlockedBy("has_meteoric_iron_ingot", has(ModItems.METEORIC_IRON_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOLCANIC_CORE.get())
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.VOLCANIC_SHARD.get())
                .unlockedBy("has_volcanic_shard", has(ModItems.VOLCANIC_SHARD.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VOLCANO.get())
                .pattern("###")
                .pattern("#0#")
                .pattern("###")
                .define('0', ModItems.VOLCANIC_CORE)
                .define('#', Blocks.NETHERRACK)
                .unlockedBy("has_volcanic_core", has(ModItems.VOLCANIC_CORE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLAZE_STAFF.get())
                .pattern(" #1")
                .pattern(" 0#")
                .pattern("0  ")
                .define('1', ModItems.VOLCANIC_CORE)
                .define('0', Items.BLAZE_ROD)
                .define('#', Items.FIRE_CHARGE)
                .unlockedBy("has_volcanic_core", has(ModItems.VOLCANIC_CORE.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VOLCANIC_LANTERN.get())
                .pattern("01#")
                .define('1', ModItems.VOLCANIC_SHARD)
                .define('0', Items.NETHERITE_SCRAP)
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_volcanic_shard", has(ModItems.VOLCANIC_SHARD.get()))
                .save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.METEORIC_IRON_INGOT.get(), 9)
                .requires(ModBlocks.METEORIC_IRON_BLOCK.get())
                .unlockedBy("has_meteoric_iron_ingot", has(ModItems.METEORIC_IRON_INGOT.get()))
                .save(pRecipeOutput);


        oreBlasting(pRecipeOutput, METEORIC_IRON_SMELTABLES, RecipeCategory.MISC, ModItems.METEORIC_IRON_INGOT, 10f, 100, "meteoric_iron");

        woodFromLogs(pRecipeOutput, ModBlocks.ENDEROAK_WOOD, ModBlocks.ENDEROAK_LOG);

        woodFromLogs(pRecipeOutput, ModBlocks.STRIPPED_ENDEROAK_WOOD, ModBlocks.STRIPPED_ENDEROAK_LOG);

        planksFromLog(pRecipeOutput, ModBlocks.ENDEROAK_PLANKS, ModItems.ENDEROAK_LOGS_TAG, 4);

        stairBuilder(ModBlocks.ENDEROAK_STAIRS, Ingredient.of(ModBlocks.ENDEROAK_PLANKS))
                .unlockedBy("has_enderoak_planks", has(ModBlocks.ENDEROAK_PLANKS))
                .save(pRecipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDEROAK_SLAB, Ingredient.of(ModBlocks.ENDEROAK_PLANKS))
                .unlockedBy("has_enderoak_planks", has(ModBlocks.ENDEROAK_PLANKS))
                .save(pRecipeOutput);

        fenceBuilder(ModBlocks.ENDEROAK_FENCE, Ingredient.of(ModBlocks.ENDEROAK_PLANKS))
                .unlockedBy("has_enderoak_planks", has(ModBlocks.ENDEROAK_PLANKS))
                .save(pRecipeOutput);

        fenceGateBuilder(ModBlocks.ENDEROAK_FENCE_GATE, Ingredient.of(ModBlocks.ENDEROAK_PLANKS))
                .unlockedBy("has_enderoak_planks", has(ModBlocks.ENDEROAK_PLANKS))
                .save(pRecipeOutput);
    }

    private static void advancedSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime) {
        oreCooking(
                pRecipeOutput,
                ModRecipes.ADVANCED_FURNACE.get(),
                AdvancedFurnaceRecipe::new,
                pIngredients,
                RecipeCategory.MISC,
                pResult,
                pExperience,
                pCookingTime,
                BuiltInRegistries.ITEM.getKey(pResult.asItem()).getPath(),
                "_from_advanced_smelting"
        );
    }
}
