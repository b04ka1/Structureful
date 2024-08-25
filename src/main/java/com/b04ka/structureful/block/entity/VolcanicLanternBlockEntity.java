package com.b04ka.structureful.block.entity;

import com.b04ka.structureful.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class VolcanicLanternBlockEntity extends BlockEntity {
    public VolcanicLanternBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VOLCANIC_LANTERN_BE.get(), pPos, pBlockState);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if(!pLevel.isClientSide){
            AABB area = new AABB(pPos).deflate(15);
            List<Player> players = level.getEntitiesOfClass(Player.class, area);
            for (Player player:players) {
                player.addEffect(new MobEffectInstance(ModEffects.FURY, 200));
            }
        }
    }
}
