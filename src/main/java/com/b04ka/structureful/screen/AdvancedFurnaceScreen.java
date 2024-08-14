package com.b04ka.structureful.screen;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmokingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.ContainerScreenEvent;
import net.neoforged.neoforge.common.NeoForge;

import javax.annotation.Nullable;

public class AdvancedFurnaceScreen extends AbstractFurnaceScreen<AdvancedFurnaceMenu> {
    private boolean widthTooNarrow;
    private ItemStack draggingItem = ItemStack.EMPTY;
    private ItemStack snapbackItem = ItemStack.EMPTY;
    @Nullable
    private Slot snapbackEnd;
    private int snapbackStartX;
    private int snapbackStartY;
    private long snapbackTime;
    private boolean isSplittingStack;
    private int quickCraftingRemainder;
    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/smoker/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("container/smoker/burn_progress");
    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/smoker.png");

    public AdvancedFurnaceScreen(AdvancedFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, new SmokingRecipeBookComponent(), pPlayerInventory, pTitle, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }

    @Override
    public void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
        this.widthTooNarrow = this.width < 379;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.render1(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    public void render1(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick){
        int i = this.leftPos;
        int j = this.topPos;
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        NeoForge.EVENT_BUS.post(new ContainerScreenEvent.Render.Background(this, pGuiGraphics, pMouseX, pMouseY));
        RenderSystem.disableDepthTest();
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate((float)i, (float)j, 0.0F);
        this.hoveredSlot = null;
        for (int k = 0; k < this.menu.slots.size(); k++) {
            Slot slot = this.menu.slots.get(k);
            if (slot.isActive()) {
                this.renderSlot(pGuiGraphics, slot);
            }

            if (this.isHovering(slot, (double)pMouseX, (double)pMouseY) && slot.isActive()) {
                this.hoveredSlot = slot;
                this.renderSlotHighlight(pGuiGraphics, slot, pMouseX, pMouseY, pPartialTick);
            }
        }
        this.renderLabels(pGuiGraphics, pMouseX, pMouseY);
        NeoForge.EVENT_BUS.post(new ContainerScreenEvent.Render.Foreground(this, pGuiGraphics, pMouseX, pMouseY));
        ItemStack itemstack = this.draggingItem.isEmpty() ? this.menu.getCarried() : this.draggingItem;
        if (!itemstack.isEmpty()) {
            int l1 = 8;
            int i2 = this.draggingItem.isEmpty() ? 8 : 16;
            String s = null;
            if (!this.draggingItem.isEmpty() && this.isSplittingStack) {
                itemstack = itemstack.copyWithCount(Mth.ceil((float)itemstack.getCount() / 2.0F));
            } else if (this.isQuickCrafting && this.quickCraftSlots.size() > 1) {
                itemstack = itemstack.copyWithCount(this.quickCraftingRemainder);
                if (itemstack.isEmpty()) {
                    s = ChatFormatting.YELLOW + "0";
                }
            }

            this.renderFloatingItem(pGuiGraphics, itemstack, pMouseX - i - 8, pMouseY - j - i2, s);
        }

        if (!this.snapbackItem.isEmpty()) {
            float f = (float)(Util.getMillis() - this.snapbackTime) / 100.0F;
            if (f >= 1.0F) {
                f = 1.0F;
                this.snapbackItem = ItemStack.EMPTY;
            }

            int j2 = this.snapbackEnd.x - this.snapbackStartX;
            int k2 = this.snapbackEnd.y - this.snapbackStartY;
            int j1 = this.snapbackStartX + (int)((float)j2 * f);
            int k1 = this.snapbackStartY + (int)((float)k2 * f);
            this.renderFloatingItem(pGuiGraphics, this.snapbackItem, j1, k1, null);
        }

        pGuiGraphics.pose().popPose();
        RenderSystem.enableDepthTest();
    }

    private boolean isHovering(Slot pSlot, double pMouseX, double pMouseY) {
        return this.isHovering(pSlot.x, pSlot.y, 16, 16, pMouseX, pMouseY);
    }

    private void renderFloatingItem(GuiGraphics pGuiGraphics, ItemStack pStack, int pX, int pY, String pText) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate(0.0F, 0.0F, 232.0F);
        pGuiGraphics.renderItem(pStack, pX, pY);
        var font = net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.of(pStack).getFont(pStack, net.neoforged.neoforge.client.extensions.common.IClientItemExtensions.FontContext.ITEM_COUNT);
        pGuiGraphics.renderItemDecorations(font == null ? this.font : font, pStack, pX, pY - (this.draggingItem.isEmpty() ? 0 : 8), pText);
        pGuiGraphics.pose().popPose();
    }

}

