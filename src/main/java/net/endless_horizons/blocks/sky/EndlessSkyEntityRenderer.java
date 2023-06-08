package net.endless_horizons.blocks.sky;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.WorldRenderer;
import org.joml.Matrix4f;


@Environment(EnvType.CLIENT)
public class EndlessSkyEntityRenderer implements BlockEntityRenderer<EndlessSkyEntity> {
    @Override
    public void render(EndlessSkyEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        renderSides(matrix4f, vertexConsumers.getBuffer(RenderLayer.getWaterMask()));
    }

    public static void renderSides(Matrix4f matrix, VertexConsumer vertexConsumer) {
        WorldRenderer.starsBuffer;
        renderSide(matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F);
        renderSide(matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        renderSide(matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        renderSide(matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        renderSide(matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F);
        renderSide(matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F);
    }

    public static void renderSide(Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4) {
        vertices.vertex(model, x1, y1, z1).next();
        vertices.vertex(model, x2, y1, z2).next();
        vertices.vertex(model, x2, y2, z3).next();
        vertices.vertex(model, x1, y2, z4).next();
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }
}
