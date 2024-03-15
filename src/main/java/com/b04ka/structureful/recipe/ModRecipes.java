package com.b04ka.structureful.recipe;

import com.b04ka.structureful.Structureful;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Structureful.MODID);

    public static final RegistryObject<RecipeSerializer<AdvancedFurnaceRecipe>> ADVANCED_FURNACE_SERIALIZER =
            SERIALIZERS.register("advanced_smelting", () -> AdvancedFurnaceRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
