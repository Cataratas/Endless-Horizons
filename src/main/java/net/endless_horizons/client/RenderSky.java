package net.endless_horizons.client;

import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.jna.platform.win32.OpenGL32;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.*;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import static net.endless_horizons.client.EndlessHorizonsClient.*;

public class RenderSky {
    public static void render(MatrixStack matrices, Camera camera, float tickDelta, long nanoTime) {
        MinecraftClient client = MinecraftClient.getInstance();
        Framebuffer mainFramebuffer = client.getFramebuffer();

        if (skyTexture == null) {
            Window window = MinecraftClient.getInstance().getWindow();
            skyTexture = new SimpleFramebuffer(window.getWidth(), window.getHeight(), true, MinecraftClient.IS_SYSTEM_MAC);
        }

        client.gameRenderer.setBlockOutlineEnabled(false);
        skyTexture.beginWrite(true);

        applySky(client, matrices, camera, tickDelta);

        skyTexture.endRead(); skyTexture.endWrite();
        client.gameRenderer.setBlockOutlineEnabled(true);
        mainFramebuffer.beginWrite(true);
    }

    public static void resetFramebuffer(int i, int i1) {
        skyTexture = new SimpleFramebuffer(i, i1, true, MinecraftClient.IS_SYSTEM_MAC);
    }

    private static void applySky(MinecraftClient client, MatrixStack matrices, Camera camera, float tickDelta) {
        assert client.world != null;
        Vec3d cameraPos = camera.getPos();
        final boolean bl32 = client.world.getDimensionEffects().useThickFog(MathHelper.floor(cameraPos.x), MathHelper.floor(cameraPos.y)) || client.inGameHud.getBossBarHud().shouldThickenFog();
        Matrix4f projectionMatrix = client.gameRenderer.getBasicProjectionMatrix(client.options.getFov().getValue());

        BackgroundRenderer.render(camera, tickDelta, client.world, client.options.getClampedViewDistance(), client.gameRenderer.getSkyDarkness(tickDelta));
        BackgroundRenderer.setFogBlack();
        RenderSystem.clear(GlConst.GL_DEPTH_BUFFER_BIT | GlConst.GL_COLOR_BUFFER_BIT, MinecraftClient.IS_SYSTEM_MAC);
        BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_SKY, client.gameRenderer.getViewDistance(), bl32, tickDelta);
        RenderSystem.setShader(GameRenderer::getPositionProgram);
        client.worldRenderer.renderSky(matrices, projectionMatrix, tickDelta, camera, false, () ->
                BackgroundRenderer.applyFog(camera, BackgroundRenderer.FogType.FOG_SKY, client.gameRenderer.getViewDistance(), bl32, tickDelta));

        MatrixStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.push();
        modelViewStack.multiplyPositionMatrix(matrices.peek().getPositionMatrix());
        RenderSystem.applyModelViewMatrix();

        if (client.options.getCloudRenderModeValue() != CloudRenderMode.OFF) {
            RenderSystem.setShader(GameRenderer::getPositionTexColorNormalProgram);
            RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
            client.worldRenderer.renderClouds(matrices, projectionMatrix, tickDelta, cameraPos.x, cameraPos.y, cameraPos.z);
        }

        RenderSystem.disableBlend();
        modelViewStack.pop();
        RenderSystem.applyModelViewMatrix();
        BackgroundRenderer.clearFog();
    }
}
