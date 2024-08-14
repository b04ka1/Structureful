package com.b04ka.structureful.recipe;

import com.b04ka.structureful.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

    public class AdvancedFurnaceRecipe extends AbstractCookingRecipe{


        public AdvancedFurnaceRecipe(String pGroup, CookingBookCategory pCategory, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
            super(ModRecipes.ADVANCED_FURNACE_TYPE.get(), pGroup, pCategory, pIngredient, pResult, pExperience, pCookingTime);
        }



        public ItemStack getToastSymbol() {
            return new ItemStack(ModBlocks.ADVANCED_FURNACE.get());
        }


        @Override
        public RecipeSerializer<?> getSerializer() {
            return ModRecipes.ADVANCED_FURNACE.get();
        }
    }
