package com.b04ka.structureful.screen;

import com.b04ka.structureful.Structureful;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Structureful.MODID);

    public static final Supplier<MenuType<AdvancedFurnaceMenu>> ADVANCED_FURNACE_MENU = MENUS.register("advanced_furnace_menu", ()-> IMenuTypeExtension.create(AdvancedFurnaceMenu::new));
}
