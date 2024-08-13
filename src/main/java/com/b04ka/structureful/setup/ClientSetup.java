package com.b04ka.structureful.setup;


import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.screen.AdvancedFurnaceScreen;
import com.b04ka.structureful.screen.ModMenuTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@Mod(value = Structureful.MODID, dist = Dist.CLIENT)
public class ClientSetup {

    public ClientSetup(IEventBus bus, ModContainer modContainer) {

        bus.addListener(this::menuScreens);
    }
    private void menuScreens(final RegisterMenuScreensEvent event){
            event.register(ModMenuTypes.ADVANCED_FURNACE_MENU.get(), AdvancedFurnaceScreen::new);
    }
}
