package com.b04ka.structureful.item.custom;

import com.b04ka.structureful.misc.ModDataComponentTypes;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class DragonHeartItem extends Item {
    private final int TICKS_PER_BOTTLE = 600;
    private final int MAX_AMOUNT = TICKS_PER_BOTTLE * 3;
    private final DataComponentType<Integer> SAP_AMOUNT = ModDataComponentTypes.SAP_AMOUNT.get();

    public DragonHeartItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (getSapAmountValue(pStack) > 0 && pEntity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 1, false, true, true));
            pStack.set(SAP_AMOUNT, getSapAmountValue(pStack) - 1);
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        if (pAction == ClickAction.SECONDARY && !pOther.isEmpty() && pOther.getItem() == Items.HONEY_BOTTLE && getSapAmountValue(pStack) < MAX_AMOUNT - TICKS_PER_BOTTLE) {
            ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
            if (!pPlayer.addItem(bottle)) {
                pPlayer.drop(bottle, false);
            }
            pStack.set(SAP_AMOUNT, getSapAmountValue(pStack) + TICKS_PER_BOTTLE);
            this.playDrinkSound(pPlayer);
            pOther.shrink(1);
            return true;

        }
        return false;
    }

    private void playDrinkSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.HONEY_DRINK, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return getSapAmountValue(pStack) != 0;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Mth.color(0.43F, 0.23F, 0.46F);
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return getSapAmountValue(pStack) / (MAX_AMOUNT / 13);
    }


    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    private int getSapAmountValue(ItemStack pStack) {
        return pStack.get(SAP_AMOUNT) == null ? 0 : pStack.get(SAP_AMOUNT);
    }
}
