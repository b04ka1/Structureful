package com.b04ka.structureful.item;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.item.custom.BlazeStaffItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;


public class ModItems {


    public static final DeferredRegister.Items ITEMS= DeferredRegister.createItems(Structureful.MODID);

    public static final DeferredItem<Item> METEORIC_IRON_INGOT = ITEMS.registerSimpleItem("meteoric_iron_ingot", new Item.Properties());
    public static final DeferredItem<Item> RAW_METEORIC_IRON = ITEMS.registerSimpleItem("raw_meteoric_iron", new Item.Properties());
    public static final Supplier<SwordItem> HEAVY_SWORD = ITEMS.register("heavy_sword",
            ()-> new SwordItem(Tiers.IRON, new  Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 6F, -3F))){
                @Override
                public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
                    if (entity instanceof LivingEntity livingEntity){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100));
                    }
                    return super.onLeftClickEntity(stack, player, entity);
                }
            });
    public static final DeferredItem<Item> VOLCANIC_SHARD = ITEMS.registerItem("volcanic_shard", properties -> new Item(properties){
        @Override
        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            if(Screen.hasShiftDown()) {
                pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information_pressed"));
                pTooltipComponents.add(Component.translatable("tooltips.structureful.volcanic_shard"));
            } else {
                pTooltipComponents.add(Component.translatable("tooltips.structureful.more_information"));
            }
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            }
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {return 3200;}
    }, new Item.Properties().fireResistant());
    public static final DeferredItem<Item> VOLCANIC_CORE = ITEMS.registerItem("volcanic_core", properties -> new Item(properties){
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {return 25600;}
    }, new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON));
    public static final DeferredItem<Item> BLAZE_STAFF = ITEMS.register("blaze_staff", ()-> new BlazeStaffItem(new Item.Properties().fireResistant().stacksTo(1).rarity(Rarity.UNCOMMON)));

}


