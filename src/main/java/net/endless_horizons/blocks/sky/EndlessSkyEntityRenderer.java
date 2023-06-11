package net.endless_horizons.blocks.sky;

import com.mojang.blaze3d.systems.RenderSystem;
import net.endless_horizons.blocks.EndlessRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

import static net.endless_horizons.client.EndlessHorizonsClient.skyShaderProgram;


@Environment(EnvType.CLIENT)
public class EndlessSkyEntityRenderer extends EndlessRenderer<EndlessSkyEntity> {

    @Override
    public void render(EndlessSkyEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        RenderSystem.setShader(() -> skyShaderProgram);

        //RenderSystem.enableBlend();
        //RenderSystem.blendFunc(GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ZERO);

        MinecraftClient client = MinecraftClient.getInstance();
        assert client.world != null;

        Vec3d colors = client.world.getSkyColor(Objects.requireNonNull(client.getCameraEntity()).getPos(), client.getTickDelta());
        RenderSystem.setShaderColor((float) colors.x, (float) colors.y, (float) colors.z, 1.0f);
        //RenderSystem.disableBlend();

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
        renderSides(matrices.peek().getPositionMatrix(), buffer);

        BufferRenderer.drawWithGlobalProgram(buffer.end());
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }
}
