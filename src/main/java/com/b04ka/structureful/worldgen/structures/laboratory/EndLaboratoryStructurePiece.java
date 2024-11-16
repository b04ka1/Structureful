package com.b04ka.structureful.worldgen.structures.laboratory;

import com.b04ka.structureful.worldgen.structures.ModStructurePieceTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class EndLaboratoryStructurePiece extends TemplateStructurePiece {

    public EndLaboratoryStructurePiece(Structure.GenerationContext context, ResourceLocation pLocation, BlockPos pTemplatePosition) {
        super(ModStructurePieceTypes.END_LABORATORY.get(), 0, context.structureTemplateManager(), pLocation, pLocation.toString(), makeSettings(Rotation.getRandom(context.random())), pTemplatePosition);
    }

    public EndLaboratoryStructurePiece(StructureTemplateManager pStructureManager, CompoundTag pTag) {
        super(ModStructurePieceTypes.END_LABORATORY.get(), pTag, pStructureManager, settings -> makeSettings(Rotation.valueOf(pTag.getString("Rot"))));
    }

    public EndLaboratoryStructurePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        this(context.structureTemplateManager(), tag);
    }

    private static StructurePlaceSettings makeSettings(Rotation rotation) {
        return new StructurePlaceSettings()
                .setRotation(rotation)
                .setMirror(Mirror.NONE)
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK)
                .setIgnoreEntities(true);
    }

    @Override
    protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom, BoundingBox pBox) {
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext pContext, CompoundTag pTag) {
        super.addAdditionalSaveData(pContext, pTag);
        pTag.putString("Rot", this.placeSettings.getRotation().name());
    }
}
