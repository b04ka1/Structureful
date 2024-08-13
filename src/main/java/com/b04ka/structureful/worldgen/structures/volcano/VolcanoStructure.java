package com.b04ka.structureful.worldgen.structures.volcano;

import com.b04ka.structureful.Structureful;
import com.b04ka.structureful.worldgen.structures.ModStructureTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

import java.util.Optional;

public class VolcanoStructure extends Structure {

    public final HeightProvider height;

    public static final MapCodec<VolcanoStructure> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(settingsCodec(i), HeightProvider.CODEC.fieldOf("height").forGetter(v -> v.height))
                    .apply(i, VolcanoStructure::new)
    );

    protected VolcanoStructure(StructureSettings pSettings, HeightProvider height) {
        super(pSettings);
        this.height = height;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        WorldgenRandom worldgenrandom = pContext.random();
        int i = pContext.chunkPos().getMinBlockX() + worldgenrandom.nextInt(16);
        int j = pContext.chunkPos().getMinBlockZ() + worldgenrandom.nextInt(16);
        int k = pContext.chunkGenerator().getSeaLevel();
        WorldGenerationContext worldgenerationcontext = new WorldGenerationContext(pContext.chunkGenerator(), pContext.heightAccessor());
        int l = this.height.sample(worldgenrandom, worldgenerationcontext);
        NoiseColumn noisecolumn = pContext.chunkGenerator().getBaseColumn(i, j, pContext.heightAccessor(), pContext.randomState());
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(i, l, j);

        while (l > k) {
            BlockState blockstate = noisecolumn.getBlock(l);
            BlockState blockstate1 = noisecolumn.getBlock(--l);
            if (blockstate.isAir()
                    && (blockstate1.is(Blocks.NETHERRACK) || blockstate1.isFaceSturdy(EmptyBlockGetter.INSTANCE, blockpos$mutableblockpos.setY(l), Direction.UP))) {
                break;
            }
        }
        if (l <= k) {
            return Optional.empty();
        } else {
            ResourceLocation res = ResourceLocation.fromNamespaceAndPath(Structureful.MODID, "volcano");
            BlockPos blockpos = new BlockPos(i, l, j);
            return Optional.of(new Structure.GenerationStub(blockpos, (piecesBuilder -> piecesBuilder.addPiece(new VolcanoPiece(pContext.structureTemplateManager(), res, blockpos)))));
        }
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.VOLCANO.get();
    }

}
