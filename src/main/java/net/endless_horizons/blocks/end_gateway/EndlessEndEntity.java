package net.endless_horizons.blocks.end_gateway;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import static net.endless_horizons.EndlessHorizons.EndlessEndEntity;


public class EndlessEndEntity extends BlockEntity {
    public EndlessEndEntity(BlockPos pos, BlockState state) {
        super(EndlessEndEntity, pos, state);
    }
}
