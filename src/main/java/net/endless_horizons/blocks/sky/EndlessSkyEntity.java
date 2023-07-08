package net.endless_horizons.blocks.sky;

import net.endless_horizons.blocks.EndlessEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.block.Block;

import java.util.Objects;

import static net.endless_horizons.EndlessHorizons.EndlessSkyEntity;

public class EndlessSkyEntity extends BlockEntity implements EndlessEntity {

    public EndlessSkyEntity(BlockPos pos, BlockState state) {
        super(EndlessSkyEntity, pos, state);
    }

    @Override
    public boolean shouldDrawSide(Direction direction) {
        BlockPos.Mutable mutable = getPos().mutableCopy();
        mutable.set(pos, direction);
        return Block.shouldDrawSide(getCachedState(), Objects.requireNonNull(getWorld()), getPos(), direction, mutable);
    }
}
