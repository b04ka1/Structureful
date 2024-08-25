package com.b04ka.structureful.datagen;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Structureful.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.METEORIC_IRON_INGOT.get());
        basicItem(ModItems.RAW_METEORIC_IRON.get());
        basicItem(ModItems.VOLCANIC_SHARD.get());
        basicItem(ModItems.VOLCANIC_CORE.get());
//        basicItem(ModBlocks.VOLCANIC_LANTERN.get().asItem());
    }
}
