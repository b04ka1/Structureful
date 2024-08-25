package com.b04ka.structureful.effect;

import com.b04ka.structureful.Structureful;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Structureful.MODID);

    public static final Holder<MobEffect> FURY = EFFECTS.register("fury", ()-> new FuryEffect(MobEffectCategory.BENEFICIAL, 0xFFF32D)
            .addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "fury"), 0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "fury"), 0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.FLYING_SPEED, ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "fury"), 0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.SNEAKING_SPEED, ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "fury"), 0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
}
