package com.b04ka.structureful.worldgen.structures;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.worldgen.structures.laboratory.EndLaboratoryStructurePiece;
import com.b04ka.structureful.worldgen.structures.volcano.VolcanoPiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModStructurePieceTypes {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPE = DeferredRegister.create(Registries.STRUCTURE_PIECE, Structureful.MODID);

    public static final Supplier<StructurePieceType> VOLCANO = STRUCTURE_PIECE_TYPE.register("volcano", () -> VolcanoPiece::new);

    public static final Supplier<StructurePieceType> END_LABORATORY = STRUCTURE_PIECE_TYPE.register("end_laboratory", () -> EndLaboratoryStructurePiece::new);

}
