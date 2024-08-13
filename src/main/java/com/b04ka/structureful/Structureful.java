package com.b04ka.structureful;

import com.b04ka.structureful.block.ModBlocks;
//import com.b04ka.structureful.block.entity.ModBlockEntities;
import com.b04ka.structureful.block.entity.ModBlockEntities;
import com.b04ka.structureful.item.ModItems;
//import com.b04ka.structureful.recipe.ModRecipes;
//import com.b04ka.structureful.screen.AdvancedFurnaceScreen;
//import com.b04ka.structureful.screen.ModMenuTypes;
import com.b04ka.structureful.recipe.ModRecipes;
import com.b04ka.structureful.screen.ModMenuTypes;
import com.b04ka.structureful.tab.ModTabs;
import com.b04ka.structureful.worldgen.structures.ModStructurePieceTypes;
import com.b04ka.structureful.worldgen.structures.ModStructureTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Structureful.MODID)
public class Structureful {

    // Define mod id in a common place for everything to reference

    public static final String MODID = "structureful";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Structureful(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModStructureTypes.STRUCTURE_TYPE.register(modEventBus);
        ModStructurePieceTypes.STRUCTURE_PIECE_TYPE.register(modEventBus);
        ModRecipes.RECIPE_TYPES.register(modEventBus);
        ModRecipes.RECIPE_SERIALIZER.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");

        }
    }
}
