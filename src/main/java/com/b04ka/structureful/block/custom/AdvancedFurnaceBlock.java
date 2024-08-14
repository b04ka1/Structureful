package com.b04ka.structureful.block.custom;


import com.b04ka.structureful.block.entity.AdvancedFurnaceBlockEntity;
import com.b04ka.structureful.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AdvancedFurnaceBlock extends AbstractFurnaceBlock {

    public static final MapCodec<AdvancedFurnaceBlock> CODEC = simpleCodec(AdvancedFurnaceBlock::new);

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec() {
        return CODEC;
    }

    public AdvancedFurnaceBlock(Properties pProperties) {
        super(pProperties);
    }


    @javax.annotation.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createFurnaceTicker(pLevel, pBlockEntityType, ModBlockEntities.ADVANCED_FURNACE_BE.get());
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(LIT)) {
            Direction direction = blockState.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d0 = (double)blockPos.getX() + 0.5D;
            double d1 = (double)blockPos.getY();
            double d2 = (double)blockPos.getZ() + 0.5D;
            double d4 = randomSource.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = randomSource.nextDouble() * 9.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d0, d1+1, d2, 0.0D, 0.1D, 0.0D);
            if (randomSource.nextInt(10) == 0) {
                level.playLocalSound(d0, d1, d2, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
        }
    }



    @Override
    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof AdvancedFurnaceBlockEntity) {
            pPlayer.openMenu((MenuProvider)blockentity);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AdvancedFurnaceBlockEntity(pPos, pState);
    }
}
