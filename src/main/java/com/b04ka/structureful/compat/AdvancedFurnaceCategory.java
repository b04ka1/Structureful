package com.b04ka.structureful.compat;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.recipe.AdvancedFurnaceRecipe;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;

public class AdvancedFurnaceCategory implements IRecipeCategory<AbstractCookingRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "advanced_smelting");
    public static final ResourceLocation TEXTURE_VANILLA = ResourceLocation.fromNamespaceAndPath(ModIds.JEI_ID, "textures/jei/gui/gui_vanilla.png");


    public static final RecipeType<AbstractCookingRecipe> ADVANCED_FURNACE_TYPE=
            new RecipeType<>(UID, AdvancedFurnaceRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;



    public AdvancedFurnaceCategory(IGuiHelper helper) {
        this.background = new AdvancedFurnaceDrawable();
        this.icon = helper.createDrawableItemStack(new ItemStack(ModBlocks.ADVANCED_FURNACE.get()));
    }

    @Override
    public RecipeType<AbstractCookingRecipe> getRecipeType() {
        return ADVANCED_FURNACE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.structureful.advanced_furnace");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }


    @Override
    public void draw(AbstractCookingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        drawExperience(recipe, guiGraphics, 0);
        drawCookTime(recipe, guiGraphics, 45);

    }



    protected void drawExperience(AbstractCookingRecipe recipe, GuiGraphics guiGraphics, int y) {
        float experience = recipe.getExperience();
        if (experience > 0) {
            Component experienceString = Component.translatable("gui.jei.category.smelting.experience", experience);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(experienceString);
            guiGraphics.drawString(fontRenderer, experienceString, getWidth() - stringWidth, y, 0xFF808080, false);
        }
    }

    protected void drawCookTime(AbstractCookingRecipe recipe, GuiGraphics guiGraphics, int y) {
        int cookTime = (int) Math.ceil(recipe.getCookingTime());
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            Component timeString = Component.translatable("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            guiGraphics.drawString(fontRenderer, timeString, getWidth() - stringWidth, y, 0xFF808080, false);
        }
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AbstractCookingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,1,1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT,61,19).addItemStack(recipe.getResultItem(null));
    }

}
