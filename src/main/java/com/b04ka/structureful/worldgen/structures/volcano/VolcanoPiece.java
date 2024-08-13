package com.b04ka.structureful.worldgen.structures.volcano;

import com.b04ka.structureful.worldgen.structures.ModStructurePieceTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class VolcanoPiece extends TemplateStructurePiece {

    public VolcanoPiece(StructureTemplateManager pStructureTemplateManager, ResourceLocation pLocation, BlockPos pTemplatePosition) {
        super(ModStructurePieceTypes.VOLCANO.get(), 0, pStructureTemplateManager, pLocation, pLocation.toString(), makeSettings(), pTemplatePosition);
    }

    public VolcanoPiece(StructureTemplateManager pStructureManager, CompoundTag pTag) {
        super(ModStructurePieceTypes.VOLCANO.get(), pTag, pStructureManager, p_228568_ -> makeSettings());
    }

    public VolcanoPiece(StructurePieceSerializationContext context, CompoundTag tag) {
        this(context.structureTemplateManager(), tag);
    }

    private static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings().setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).setIgnoreEntities(true);
    }


    @Override
    protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom, BoundingBox pBox) {


    }
}
