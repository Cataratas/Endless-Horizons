package net.endless_horizons.blocks.sky;

import net.endless_horizons.blocks.EndlessRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

import static net.endless_horizons.client.EndlessHorizonsClient.skyRenderLayer;


@Environment(EnvType.CLIENT)
public class EndlessSkyEntityRenderer extends EndlessRenderer<EndlessSkyEntity> implements BlockEntityRenderer<EndlessSkyEntity> {

    @Override
    public void render(EndlessSkyEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        renderSides(entity, matrices.peek().getPositionMatrix(), vertexConsumers.getBuffer(skyRenderLayer));
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }
}
