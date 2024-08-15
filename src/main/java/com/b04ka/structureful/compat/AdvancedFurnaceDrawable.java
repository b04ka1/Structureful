package com.b04ka.structureful.compat;

import com.b04ka.structureful.Structureful;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class AdvancedFurnaceDrawable implements IDrawable {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Structureful.MODID,"textures/gui/advanced_furnace.png");

    @Override
    public int getHeight() {
        return 54;
    }

    @Override
    public int getWidth() {
        return 82;
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        guiGraphics.blit(TEXTURE, xOffset, yOffset, 55, 16, getWidth(), getHeight(), 256, 256);
        guiGraphics.blit(TEXTURE, xOffset + 25, yOffset + 18, 176, 0, (int) Math.ceil(24 * (((double) Minecraft.getInstance().player.tickCount/15)%20/20F)), 16);
        guiGraphics.blit(TEXTURE, xOffset + 2, yOffset + 21, 176, 16, 14, 13);
    }
}
