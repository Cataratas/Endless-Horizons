package net.endless_horizons.blocks.sky;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;

import static net.endless_horizons.client.EndlessHorizonsClient.skyRenderLayer;


@Environment(EnvType.CLIENT)
public class EndlessSkyEntityRenderer implements BlockEntityRenderer<EndlessSkyEntity> {

    @Override
    public void render(EndlessSkyEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(skyRenderLayer);

        MinecraftClient mc = MinecraftClient.getInstance();
        Camera camera = mc.gameRenderer.getCamera();
        WorldRenderer gameRenderer = mc.worldRenderer;

        assert mc.world != null;
        matrices.push();
        //BackgroundRenderer.render(camera, tickDelta, mc.world, mc.options.getClampedViewDistance(), mc.world.getSkyBrightness(tickDelta));
        //BackgroundRenderer.setFogBlack();
        //RenderSystem.clear(16640, MinecraftClient.IS_SYSTEM_MAC);


        //gameRenderer.renderSky(matrices, matrices.peek().getPositionMatrix(), tickDelta, camera, true, () -> {});
        matrices.pop();
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }
}
