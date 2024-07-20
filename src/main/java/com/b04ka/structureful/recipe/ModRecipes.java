package com.b04ka.structureful.recipe;

import com.b04ka.structureful.Structureful;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Structureful.MODID);

    public static final Supplier<RecipeSerializer<AdvancedFurnaceRecipe>> ADVANCED_FURNACE_SERIALIZER = SERIALIZERS.register("advanced_smelting", () -> AdvancedFurnaceRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
