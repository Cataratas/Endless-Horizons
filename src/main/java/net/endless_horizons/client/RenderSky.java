package net.endless_horizons.client;

import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;

import static net.endless_horizons.client.EndlessHorizonsClient.*;

public class RenderSky {
    public static void render(WorldRenderContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        Framebuffer mainFramebuffer = client.getFramebuffer();

        client.gameRenderer.setBlockOutlineEnabled(false);
        skyFramebuffer.beginWrite(true);

        renderSky(client, context.matrixStack(), context.projectionMatrix(), context.camera(), context.tickDelta(), context.lightmapTextureManager());

        skyFramebuffer.endRead(); skyFramebuffer.endWrite();
        client.gameRenderer.setBlockOutlineEnabled(true);
        mainFramebuffer.beginWrite(true);
    }

    public static void resetFramebuffer(int w, int h) {
        skyFramebuffer = new SimpleFramebuffer(w, h, true, MinecraftClient.IS_SYSTEM_MAC);
    }

    private static void renderSky(MinecraftClient client, MatrixStack matrices, Matrix4f projectionMatrix, Camera camera, float tickDelta, LightmapTextureManager lightmapTextureManager) {
        assert client.world != null;
        Vec3d cameraPos = camera.getPos();
        float viewDistance = client.gameRenderer.getViewDistance();
        boolean bl32 = client.world.getDimensionEffects().useThickFog(MathHelper.floor(cameraPos.x), MathHelper.floor(cameraPos.y)) || client.inGameHud.getBossBarHud().shouldThickenFog();

        BackgroundRenderer.render(camera, tickDelta, client.world, client.options.getClampedViewDistance(), client.gameRenderer.getSkyDarkness(tickDelta));
        BackgroundRenderer.setFogBlack();
        RenderSystem.clear(GlConst.GL_DEPTH_BUFFER_BIT | GlConst.GL_COLOR_BUFFER_BIT, MinecraftClient.IS_SYSTEM_MAC);

        BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_SKY, viewDistance, bl32, tickDelta);
        RenderSystem.setShader(GameRenderer::getPositionProgram);
        client.worldRenderer.renderSky(matrices, projectionMatrix, tickDelta, camera, bl32, () ->
                BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_SKY, viewDistance, bl32, tickDelta));
        BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_TERRAIN, Math.max(viewDistance, 32.0f), bl32, tickDelta);

        MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();
        matrixStack.multiplyPositionMatrix(matrices.peek().getPositionMatrix());
        RenderSystem.applyModelViewMatrix();

        if (client.options.getCloudRenderModeValue() != CloudRenderMode.OFF) {
            RenderSystem.setShader(GameRenderer::getPositionColorTexLightmapProgram);
            client.worldRenderer.renderClouds(matrices, projectionMatrix, tickDelta, cameraPos.x, cameraPos.y, cameraPos.z);
        }

        RenderSystem.depthMask(false);
        client.worldRenderer.renderWeather(lightmapTextureManager, tickDelta, cameraPos.x, cameraPos.y, cameraPos.z);
        RenderSystem.depthMask(true);

        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.disableBlend();
        BackgroundRenderer.clearFog();
    }
}
