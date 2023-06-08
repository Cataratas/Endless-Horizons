package net.endless_horizons.blocks.sky;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import static net.endless_horizons.EndlessHorizons.EndlessSkyEntity;


public class EndlessSkyEntity extends BlockEntity {
    public EndlessSkyEntity(BlockPos pos, BlockState state) {
        super(EndlessSkyEntity, pos, state);
    }
}
