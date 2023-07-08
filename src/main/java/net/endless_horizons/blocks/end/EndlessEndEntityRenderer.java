package net.endless_horizons.blocks.end;

import net.endless_horizons.blocks.EndlessRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;


@Environment(EnvType.CLIENT)
public class EndlessEndEntityRenderer extends EndlessRenderer<EndlessEndEntity> implements BlockEntityRenderer<EndlessEndEntity> {
    @Override
    public void render(EndlessEndEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        renderSides(entity, matrices.peek().getPositionMatrix(), vertexConsumers.getBuffer(RenderLayer.getEndGateway()));
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }
}
