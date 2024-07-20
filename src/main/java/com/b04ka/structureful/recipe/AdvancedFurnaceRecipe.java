package com.b04ka.structureful.recipe;

import com.b04ka.structureful.Structureful;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AdvancedFurnaceRecipe  implements Recipe<SimpleContainer> {
        private final NonNullList<Ingredient> inputItems;
        private final ItemStack output;
        private final ResourceLocation id;

    public AdvancedFurnaceRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }


    @Override
    public boolean matches(SimpleContainer p_44002_, Level p_44003_) {
    if (p_44003_.isClientSide){
    return false;
    }

        return inputItems.get(0).test(p_44002_.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer p_345149_, HolderLookup.Provider p_346030_) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output.copy();
    }


    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<AdvancedFurnaceRecipe>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "advanced_smelting";
    }

    public static class Serializer implements RecipeSerializer<AdvancedFurnaceRecipe> {
    public static final Serializer INSTANCE = new Serializer();
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "advanced_smelting");
        @Override
        public AdvancedFurnaceRecipe fromJson(ResourceLocation p_44103_, JsonObject p_44104_) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44104_, "output"));


            JsonArray ingredients = GsonHelper.getAsJsonArray(p_44104_, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AdvancedFurnaceRecipe(inputs, output, p_44103_);
        }

        @Override
        public @Nullable AdvancedFurnaceRecipe fromNetwork(ResourceLocation p_44105_, FriendlyByteBuf p_44106_) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(p_44106_));

            }
            ItemStack output = p_44106_.readItem();
            return new AdvancedFurnaceRecipe(inputs, output, p_44105_);
        }

        @Override
        public void toNetwork(FriendlyByteBuf p_44101_, AdvancedFurnaceRecipe p_44102_) {
            p_44101_.writeInt(p_44102_.inputItems.size());


            for (Ingredient ingredient: p_44102_.getIngredients()) {
                ingredient.toNetwork(p_44101_);
            }
            p_44101_.writeItemStack(p_44102_.getResultItem(null), false);
        }

        @Override
        public MapCodec<AdvancedFurnaceRecipe> codec() {
            return null;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AdvancedFurnaceRecipe> streamCodec() {
            return null;
        }
    }
}
