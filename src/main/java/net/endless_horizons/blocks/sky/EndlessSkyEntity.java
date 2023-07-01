package net.endless_horizons.blocks.sky;

import net.endless_horizons.blocks.EndlessEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import net.minecraft.block.Block;

import java.util.Arrays;
import java.util.Objects;

import static net.endless_horizons.EndlessHorizons.EndlessSkyEntity;

public class EndlessSkyEntity extends BlockEntity implements EndlessEntity {
    private final Boolean[] visibleFaces = new Boolean[6];

    public EndlessSkyEntity(BlockPos pos, BlockState state) {
        super(EndlessSkyEntity, pos, state);
    }

    @Override
    public boolean shouldDrawSide(Direction direction) {
        return true;
    }
}
