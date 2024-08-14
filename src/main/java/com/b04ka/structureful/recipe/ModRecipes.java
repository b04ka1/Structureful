package com.b04ka.structureful.recipe;

import com.b04ka.structureful.Structureful;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Structureful.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Structureful.MODID);


    public static final Supplier<RecipeType<AdvancedFurnaceRecipe>> ADVANCED_FURNACE_TYPE = RECIPE_TYPES.register("advanced_smelting", ()-> new RecipeType<>(){});
    public static final Supplier<RecipeSerializer<?>> ADVANCED_FURNACE = RECIPE_SERIALIZER.register("advanced_smelting", ()-> new SimpleCookingSerializer<>(AdvancedFurnaceRecipe::new, 100));
}
