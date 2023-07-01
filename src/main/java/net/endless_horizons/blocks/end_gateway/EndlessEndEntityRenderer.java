package net.endless_horizons.blocks.end_gateway;

import net.endless_horizons.blocks.EndlessEntity;
import net.endless_horizons.blocks.EndlessRenderer;
import net.endless_horizons.blocks.sky.EndlessSkyEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;


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
