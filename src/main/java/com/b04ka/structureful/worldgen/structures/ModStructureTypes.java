package com.b04ka.structureful.worldgen.structures;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.worldgen.structures.volcano.VolcanoStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModStructureTypes {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registries.STRUCTURE_TYPE, Structureful.MODID);

    public static final Supplier<StructureType<VolcanoStructure>> VOLCANO = STRUCTURE_TYPE.register("volcano",()->()-> VolcanoStructure.CODEC);

}
