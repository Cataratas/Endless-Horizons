package net.endless_horizons.blocks.end;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.endless_horizons.blocks.EndlessEntity;
import java.util.Objects;

import static net.endless_horizons.EndlessHorizons.EndlessEndEntity;


public class EndlessEndEntity extends BlockEntity implements EndlessEntity {

    public EndlessEndEntity(BlockPos pos, BlockState state) {
        super(EndlessEndEntity, pos, state);
    }

    @Override
    public boolean shouldDrawSide(Direction direction) {
        BlockPos.Mutable mutable = getPos().mutableCopy();
        mutable.set(pos, direction);
        return Block.shouldDrawSide(getCachedState(), Objects.requireNonNull(getWorld()), getPos(), direction, mutable);
    }
}
