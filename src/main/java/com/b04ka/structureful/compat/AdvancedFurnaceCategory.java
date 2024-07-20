//package com.b04ka.structureful.compat;
//
//import com.b04ka.structureful.Structureful;
//import com.b04ka.structureful.block.ModBlocks;
//import com.b04ka.structureful.recipe.AdvancedFurnaceRecipe;
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
//import mezz.jei.api.gui.drawable.IDrawable;
//import mezz.jei.api.helpers.IGuiHelper;
//import mezz.jei.api.recipe.IFocusGroup;
//import mezz.jei.api.recipe.RecipeIngredientRole;
//import mezz.jei.api.recipe.RecipeType;
//import mezz.jei.api.recipe.category.IRecipeCategory;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//
//public class AdvancedFurnaceCategory implements IRecipeCategory<AdvancedFurnaceRecipe> {
//    public static final ResourceLocation UID = new ResourceLocation(Structureful.MODID, "advanced_smelting");
//    public static final ResourceLocation TEXTURE = new ResourceLocation(Structureful.MODID,
//            "textures/gui/advanced_furnace.png");
//
//    public static final RecipeType<AdvancedFurnaceRecipe> ADVANCED_FURNACE_TYPE=
//            new RecipeType<>(UID, AdvancedFurnaceRecipe.class);
//
//    private final IDrawable background;
//    private final IDrawable icon;
//
//    public AdvancedFurnaceCategory(IGuiHelper helper) {
//        this.background = helper.createDrawable(TEXTURE, 0 , 0, 176 , 83);
//        this.icon = helper.createDrawableItemStack(new ItemStack(ModBlocks.ADVANCED_FURNACE.get()));
//    }
//
//    @Override
//    public RecipeType<AdvancedFurnaceRecipe> getRecipeType() {
//        return ADVANCED_FURNACE_TYPE;
//    }
//
//    @Override
//    public Component getTitle() {
//        return Component.translatable("block.structureful.advanced_furnace");
//    }
//
//    @Override
//    public IDrawable getBackground() {
//        return this.background;
//    }
//
//    @Override
//    public IDrawable getIcon() {
//        return this.icon;
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayoutBuilder builder, AdvancedFurnaceRecipe recipe, IFocusGroup focuses) {
//        builder.addSlot(RecipeIngredientRole.INPUT,56,17).addIngredients(recipe.getIngredients().get(0));
//
//        builder.addSlot(RecipeIngredientRole.OUTPUT,116,35).addItemStack(recipe.getResultItem(null));
//    }
//}
