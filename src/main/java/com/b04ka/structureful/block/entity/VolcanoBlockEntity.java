package com.b04ka.structureful.block.entity;

import com.b04ka.structureful.block.custom.VolcanoBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VolcanoBlockEntity extends BlockEntity {

    public int eruptionTime = 0;
    public int eruptionDelay = 300;


    public VolcanoBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VOLCANO_BE.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        this.eruptionTime = pTag.getInt("eruptionTime");
        this.eruptionDelay = pTag.getInt("eruptionDelay");
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        pTag.putInt("eruptionTime", this.eruptionTime);
        pTag.putInt("eruptionDelay", this.eruptionDelay);
    }

    public void tick (Level pLevel, BlockPos pPos, BlockState pState) {
        if (pState.getValue(VolcanoBlock.ACTIVE)){
            eruptionTime--;
            if (eruptionTime<=0){
                pLevel.setBlock(pPos, pState.setValue(VolcanoBlock.ACTIVE, Boolean.valueOf(false)), 2);
                eruptionDelay = 5000 + pLevel.random.nextInt(1000);
            }
        }
        else{
            eruptionDelay--;
            if (eruptionDelay<=0){
                pLevel.setBlock(pPos, pState.setValue(VolcanoBlock.ACTIVE, Boolean.valueOf(true)), 2);
                eruptionTime = 1000 + pLevel.random.nextInt(200);
            }
        }

    }

}
