package net.endless_horizons.blocks.sky;

import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.endless_horizons.blocks.EndlessEntity;
import net.endless_horizons.blocks.EndlessRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import java.util.Objects;

import static net.endless_horizons.client.EndlessHorizonsClient.skyRenderLayer;
import static net.endless_horizons.client.EndlessHorizonsClient.skyShaderProgram;


@Environment(EnvType.CLIENT)
public class EndlessSkyEntityRenderer extends EndlessRenderer<EndlessSkyEntity> implements BlockEntityRenderer<EndlessSkyEntity> {

    @Override
    public void render(EndlessSkyEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        renderSides(entity, matrices.peek().getPositionMatrix(), vertexConsumers.getBuffer(skyRenderLayer));

        //RenderSystem.setShader(() -> skyShaderProgram);
        //RenderSystem.clear(GlConst.GL_DEPTH_BUFFER_BIT | GlConst.GL_COLOR_BUFFER_BIT, MinecraftClient.IS_SYSTEM_MAC);
//
        //MinecraftClient client = MinecraftClient.getInstance();
        //Camera camera = client.gameRenderer.getCamera();
        //assert client.world != null;
        //Vec3d cameraPos = camera.getPos();
        //boolean bl32 = client.world.getDimensionEffects().useThickFog(MathHelper.floor(cameraPos.getX()), MathHelper.floor(cameraPos.getY())) || client.inGameHud.getBossBarHud().shouldThickenFog();
//
        //Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        //BackgroundRenderer.render(camera, tickDelta, client.world, client.options.getClampedViewDistance(), client.gameRenderer.getSkyDarkness(tickDelta));
        //BackgroundRenderer.setFogBlack();
        //client.worldRenderer.renderSky(matrices, matrix4f, client.getTickDelta(), camera, false, () ->
        //        BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_SKY, client.gameRenderer.getViewDistance(), bl32, tickDelta));
//
        //MatrixStack modelViewStack = RenderSystem.getModelViewStack();
        //modelViewStack.push();
        //modelViewStack.multiplyPositionMatrix(matrix4f);
        //RenderSystem.applyModelViewMatrix();
//
        //BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        //buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
        //renderSides(entity, matrix4f, buffer);
        //BufferRenderer.drawWithGlobalProgram(buffer.end());
//
        //modelViewStack.pop();
        //RenderSystem.applyModelViewMatrix();
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }
}
