package com.b04ka.structureful.block.entity;

import com.b04ka.structureful.recipe.AdvancedFurnaceRecipe;
import com.b04ka.structureful.recipe.ModRecipes;
import com.b04ka.structureful.screen.AdvancedFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedFurnaceBlockEntity extends AbstractFurnaceBlockEntity {


    public AdvancedFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ADVANCED_FURNACE_BE.get(), pPos, pBlockState, ModRecipes.ADVANCED_FURNACE_TYPE.get());
    }

    public static RecipeType<AdvancedFurnaceRecipe> getRecipeType(){
        return ModRecipes.ADVANCED_FURNACE_TYPE.get();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.structureful.advanced_furnace");
    }

    @Override
    protected int getBurnDuration(ItemStack pFuel) {
        return super.getBurnDuration(pFuel) / 4;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new AdvancedFurnaceMenu(pContainerId, pInventory, this, this.dataAccess);
    }
}
