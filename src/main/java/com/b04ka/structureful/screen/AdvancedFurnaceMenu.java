package com.b04ka.structureful.screen;

import com.b04ka.structureful.recipe.ModRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;

public class AdvancedFurnaceMenu extends AbstractFurnaceMenu {

    public AdvancedFurnaceMenu( int pContainerId, Inventory pPlayerInventory) {
        super(ModMenuTypes.ADVANCED_FURNACE_MENU.get(), ModRecipes.ADVANCED_FURNACE_TYPE.get(), RecipeBookType.SMOKER, pContainerId, pPlayerInventory);
    }

    public AdvancedFurnaceMenu(int pContainerId, Inventory pPlayerInventory, Container pContainer, ContainerData pData) {
        super(ModMenuTypes.ADVANCED_FURNACE_MENU.get(), ModRecipes.ADVANCED_FURNACE_TYPE.get(), RecipeBookType.SMOKER, pContainerId, pPlayerInventory, pContainer, pData);
    }
}
