package com.b04ka.structureful;

import com.b04ka.structureful.block.ModBlocks;
import com.b04ka.structureful.block.entity.ModBlockEntities;
import com.b04ka.structureful.effect.ModEffects;
import com.b04ka.structureful.item.ModItems;
import com.b04ka.structureful.misc.ModDataComponentTypes;
import com.b04ka.structureful.recipe.ModRecipes;
import com.b04ka.structureful.screen.ModMenuTypes;
import com.b04ka.structureful.tab.ModTabs;
import com.b04ka.structureful.worldgen.structures.ModStructurePieceTypes;
import com.b04ka.structureful.worldgen.structures.ModStructureTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Structureful.MODID)
public class Structureful {
    public static final String MODID = "structureful";
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
        ModEffects.EFFECTS.register(modEventBus);
        ModDataComponentTypes.DATA_COMPONENT_TYPE.register(modEventBus);
        modEventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemProperties.register(ModItems.DRAGON_HEART.get(), ResourceLocation.fromNamespaceAndPath(MODID, "is_active"), ((pStack, pLevel, pEntity, pSeed) -> {
            final DataComponentType<Integer> SAP_AMOUNT = ModDataComponentTypes.SAP_AMOUNT.get();
            if ((pStack.get(SAP_AMOUNT) == null ? 0 : pStack.get(SAP_AMOUNT)) > 0) {
                return 1;
            }
            return 0;
        }));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Hello from structureful on server starting!");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Hello from structureful!");

        }
    }
}
