package com.b04ka.structureful.block.custom;

import com.b04ka.structureful.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class EnderoakLogBlock extends Block {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final IntegerProperty HAS_SAP = IntegerProperty.create("has_sap", 0, 2);

    public EnderoakLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HAS_SAP, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING, HAS_SAP);
    }

    @Override
    protected ItemInteractionResult useItemOn
            (ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pStack.is(Items.GLASS_BOTTLE) && pState.getValue(HAS_SAP).equals(2) && pHitResult.getDirection().equals(pState.getValue(FACING))) {
            pLevel.setBlock(pPos, pState.setValue(HAS_SAP, 1), 11);
            if (!pPlayer.isCreative()) {
                pStack.shrink(1);
            }
            pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (pStack.isEmpty()) {
                pPlayer.setItemInHand(pHand, new ItemStack(ModItems.ENDERSAP_BOTTLE.get()));
            } else if (!pPlayer.getInventory().add(new ItemStack(ModItems.ENDERSAP_BOTTLE.get()))) {
                pPlayer.drop(new ItemStack(ModItems.ENDERSAP_BOTTLE.get()), false);
            }
            return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
        }
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(HAS_SAP).equals(1) && pRandom.nextInt(3) == 0) {
            pLevel.setBlock(pPos, pState.setValue(HAS_SAP, 2), 11);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getClickedFace());
    }

    @Override
    protected BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
}
