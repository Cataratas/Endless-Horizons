package net.endless_horizons.blocks.end_gateway;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class EndlessEndBlock extends Block implements BlockEntityProvider {
    public EndlessEndBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EndlessEndEntity(pos, state);
    }
}
