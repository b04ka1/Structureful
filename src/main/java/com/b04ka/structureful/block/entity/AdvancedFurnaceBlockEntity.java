package com.b04ka.structureful.block.entity;

import com.b04ka.structureful.block.custom.AdvancedFurnaceBlock;
import com.b04ka.structureful.recipe.AdvancedFurnaceRecipe;
import com.b04ka.structureful.screen.AdvancedFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AdvancedFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 300;
    private int burnTime = 0;
    private int itemBurnTime = 0;
    private boolean isLavaBucket = false;



    public AdvancedFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ADVANCED_FURNACE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AdvancedFurnaceBlockEntity.this.progress;
                    case 1 -> AdvancedFurnaceBlockEntity.this.maxProgress;
                    case 2 -> AdvancedFurnaceBlockEntity.this.burnTime;
                    case 3 -> AdvancedFurnaceBlockEntity.this.itemBurnTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AdvancedFurnaceBlockEntity.this.progress = pValue;
                    case 1 -> AdvancedFurnaceBlockEntity.this.maxProgress = pValue;
                    case 2 -> AdvancedFurnaceBlockEntity.this.burnTime = pValue;
                    case 3 -> AdvancedFurnaceBlockEntity.this.itemBurnTime= pValue;

                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.structureful.advanced_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AdvancedFurnaceMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("advanced_furnace.progress", progress);
        pTag.putInt("advanced_furnace.burningTime", burnTime);
        pTag.putInt("advanced_furnace.itemBurnTime", itemBurnTime);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("advanced_furnace.progress");
        burnTime = pTag.getInt("advanced_furnace.burningTime");
        itemBurnTime = pTag.getInt("advanced_furnace.itemBurnTime");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if(hasBurnTime()){
            decreaseBurningTime();
        }

        if(hasRecipe()) {
            if (hasBurnTime()){
                increaseCraftingProgress();

                setChanged(pLevel, pPos, pState);

                if(hasProgressFinished()) {
                    craftItem();
                    resetProgress();
                }
                if (hasFuelBurned()){
                    burnFuel();
                }
            }
            else {
                if (hasFuelItem()){
                    burnFuel();
                }
            }

        }
        else {
            resetProgress();
        }
        litBlockState(pState, pPos, pLevel);
    }
    private boolean hasFuelItem(){
        boolean hasFuelItemCoal = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.COAL;
        if (hasFuelItemCoal){
            itemBurnTime=320;
            isLavaBucket=false;
        }
        boolean hasFuelItemCoalBlock = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.COAL_BLOCK;
        if (hasFuelItemCoalBlock){
            itemBurnTime=3200;
            isLavaBucket=false;
        }
        boolean hasFuelItemBlazeRod = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.BLAZE_ROD;
        if (hasFuelItemBlazeRod){
            itemBurnTime=480;
            isLavaBucket=false;
        }
        boolean hasFuelItemLavaBucket = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.LAVA_BUCKET;
        if (hasFuelItemLavaBucket){
            itemBurnTime=4000;
            isLavaBucket=true;
        }

        return hasFuelItemCoal||hasFuelItemBlazeRod||hasFuelItemLavaBucket||hasFuelItemCoalBlock;
    }
    private boolean hasBurnTime() {
        boolean hasBurnTime = burnTime > 0;
        return hasBurnTime;
    }


    private void litBlockState(BlockState blockState, BlockPos  blockPos, Level level){
        if (burnTime>0){
            blockState = blockState.setValue(AdvancedFurnaceBlock.LIT, Boolean.valueOf(true));
            level.setBlock(blockPos, blockState, 3);

        }
        else {
            blockState = blockState.setValue(AdvancedFurnaceBlock.LIT, Boolean.valueOf(false));
            level.setBlock(blockPos, blockState, 3);
        }
    }

    private void resetBurnTime() {
       burnTime = 0;
    }

    private void decreaseBurningTime() {
        burnTime--;
    }

    private boolean hasFuelBurned() {
        return burnTime <= 0;
    }

    private void burnFuel() {
        this.itemHandler.extractItem(FUEL_SLOT, 1, false);
        if (isLavaBucket){
            this.itemHandler.setStackInSlot(FUEL_SLOT,new  ItemStack(Items.BUCKET,1));
        }
        burnTime = itemBurnTime;
    }


    private void craftItem() {
        Optional<AdvancedFurnaceRecipe> recipe = getCurrentRecipe();
        ItemStack result =recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasRecipe() {
        Optional<AdvancedFurnaceRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());


        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<AdvancedFurnaceRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(AdvancedFurnaceRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;

    }


}
