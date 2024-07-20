//package com.b04ka.structureful.block.custom;
//
//import com.b04ka.structureful.block.entity.AdvancedFurnaceBlockEntity;
//import com.b04ka.structureful.block.entity.ModBlockEntities;
//import com.b04ka.structureful.block.entity.VolcanoBlockEntity;
//import com.b04ka.structureful.item.ModItems;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.stats.Stats;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.monster.piglin.PiglinAi;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.BaseEntityBlock;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.RenderShape;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.StateDefinition;
//import net.minecraft.world.level.block.state.properties.BooleanProperty;
//import net.minecraftforge.common.extensions.IForgeIntrinsicHolderTagAppender;
//import net.minecraftforge.event.entity.player.PlayerEvent;
//import org.jetbrains.annotations.Nullable;
//
//public class VolcanoBlock extends BaseEntityBlock {
//
//    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
//
//    public VolcanoBlock(Properties pProperties) {
//        super(pProperties);
//        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, Boolean.valueOf(false)));
//    }
//
//    public RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.MODEL;
//    }
//
//
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(ACTIVE);
//    }
//
//    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
//        if(state.getValue(ACTIVE)){
//            double d0 = (double)pos.getX() + randomSource.nextDouble();
//            double d1 = (double)pos.getY() + 1.0D;
//            double d2 = (double)pos.getZ() + randomSource.nextDouble();
//            level.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
//            level.playLocalSound(d0, d1, d2, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.2F + randomSource.nextFloat() * 0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
//        }
//        }
//    @Nullable
//    @Override
//    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//        return new VolcanoBlockEntity(pPos, pState);
//    }
//
//
//
//    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
//        if(pState.getValue(VolcanoBlock.ACTIVE)){
//            if (pEntity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)pEntity)) {
//                pEntity.hurt(pLevel.damageSources().hotFloor(), 3.0F);
//         }
//        }
//
//        super.stepOn(pLevel, pPos, pState, pEntity);
//    }
//
//    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState state, @Nullable BlockEntity entity, ItemStack itemStack) {
//        super.playerDestroy(level, player, blockPos, state, entity, itemStack);
//        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, itemStack) == 0&&state.getValue(ACTIVE)) {
//            level.setBlockAndUpdate(blockPos, Blocks.LAVA.defaultBlockState());
//        }
//    }
//
//
//    @Nullable
//    @Override
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
//        if(pLevel.isClientSide()) {
//            return null;
//        }
//
//        return createTickerHelper(pBlockEntityType, ModBlockEntities.VOLCANO_BE.get(),
//                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
//    }
//    }
//
//
//
//
