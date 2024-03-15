package com.b04ka.structureful.compat;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.recipe.AdvancedFurnaceRecipe;
import com.b04ka.structureful.screen.AdvancedFurnaceScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class StructurefulPlugin implements IModPlugin {
    public static final RecipeType<AdvancedFurnaceRecipe> ADVANCED_FURNACE_TYPE = RecipeType.create(Structureful.MODID, "advanced_smelting", AdvancedFurnaceRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Structureful.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AdvancedFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<AdvancedFurnaceRecipe> smeltingRecipes =recipeManager.getAllRecipesFor(AdvancedFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(AdvancedFurnaceCategory.ADVANCED_FURNACE_TYPE, smeltingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AdvancedFurnaceScreen.class,80,35,22,15,
                AdvancedFurnaceCategory.ADVANCED_FURNACE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ADVANCED_FURNACE.get()), ADVANCED_FURNACE_TYPE);
    }
}
