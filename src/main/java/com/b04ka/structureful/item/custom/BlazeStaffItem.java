package com.b04ka.structureful.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class BlazeStaffItem extends Item {
    public BlazeStaffItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, 20);
        Random random = new Random();
        class SimpleThread extends Thread{
            @Override
            public void run() {
                if (!level.isClientSide){
                    for (int i = 0; i < 3; i++) {
                        SmallFireball smallfireball = new SmallFireball(level, player, new Vec3(0F, 0F, 0F));
                        smallfireball.setPos(smallfireball.getX(), player.getY(0.5) + 0.5, smallfireball.getZ());
                        smallfireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2F, 2F);
                        level.addFreshEntity(smallfireball);
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.NEUTRAL, 0.5F, random.nextFloat(0.4F)+0.8F);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        SimpleThread simpleThread = new SimpleThread();
        simpleThread.start();
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand),level.isClientSide);
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information_pressed"));
            pTooltipComponents.add(Component.translatable("tooltips.structureful.blaze_staff"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
