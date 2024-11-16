package com.b04ka.structureful.worldgen.structures.laboratory;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.worldgen.structures.ModStructureTypes;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;

import java.util.List;
import java.util.Optional;

public class EndLaboratoryStructure extends Structure {
    public static final MapCodec<EndLaboratoryStructure> CODEC = simpleCodec(EndLaboratoryStructure::new);

    protected EndLaboratoryStructure(StructureSettings pSettings) {
        super(pSettings);
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext pContext) {
        Rotation rotation = Rotation.getRandom(pContext.random());
        BlockPos blockpos = this.getLowestYIn5by5BoxOffset7Blocks(pContext, rotation);
        ResourceLocation res = ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "end_laboratory");
        return blockpos.getY() < 60
                ? Optional.empty()
                : Optional.of(new Structure.GenerationStub(blockpos, builder -> builder.addPiece(new EndLaboratoryStructurePiece(pContext, res, blockpos.offset(0, -11, 0)))));
    }

    private void generatePieces(StructurePiecesBuilder pBuilder, BlockPos pStartPos, Rotation pRotation, Structure.GenerationContext pContext) {
        List<StructurePiece> list = Lists.newArrayList();
        EndCityPieces.startHouseTower(pContext.structureTemplateManager(), pStartPos, pRotation, list, pContext.random());
        list.forEach(pBuilder::addPiece);
    }


    @Override
    public StructureType<?> type() {
        return ModStructureTypes.END_LABORATORY.get();
    }
}
