package com.b04ka.structureful.recipe;

import com.b04ka.structureful.block.ModBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

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
