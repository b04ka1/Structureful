package com.b04ka.structureful.misc;

import com.b04ka.structureful.Structureful;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponentTypes {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPE = DeferredRegister.createDataComponents(Structureful.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SAP_AMOUNT = DATA_COMPONENT_TYPE.registerComponentType(
            "sap_amount", b -> b.persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT)

    );
}
