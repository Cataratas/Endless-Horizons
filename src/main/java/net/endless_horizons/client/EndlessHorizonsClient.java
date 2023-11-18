package net.endless_horizons.client;

import com.mojang.blaze3d.systems.RenderSystem;
import ladysnake.satin.api.event.ResolutionChangeCallback;
import net.endless_horizons.ModelProvider;
import net.endless_horizons.blocks.sky.EndlessSkyEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.endless_horizons.blocks.end.EndlessEndEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;

import static net.endless_horizons.EndlessHorizons.*;


@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class EndlessHorizonsClient implements ClientModInitializer {
    public static ShaderProgram skyShaderProgram;
    public static SimpleFramebuffer skyFramebuffer;

    public static ShaderProgram getSkyShader() { return skyShaderProgram; }

    private static void setSkyTexture() { RenderSystem.setShaderTexture(0, skyFramebuffer != null ? skyFramebuffer.getColorAttachment() : 0); }

    public static final RenderLayer skyRenderLayer = RenderLayer.of(MOD_ID + "_sky", VertexFormats.POSITION, VertexFormat.DrawMode.QUADS, 256, false, false,
            RenderLayer.MultiPhaseParameters.builder()
                    .program(new RenderPhase.ShaderProgram(EndlessHorizonsClient::getSkyShader))
                    .texture(new RenderPhase.TextureBase(EndlessHorizonsClient::setSkyTexture, () -> {}))
                    .build(false)
    );

    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModelProvider());
        BlockEntityRendererFactories.register(EndlessEndEntity, ctx -> new EndlessEndEntityRenderer());
        BlockEntityRendererFactories.register(EndlessSkyEntity, ctx -> new EndlessSkyEntityRenderer());

        ClientBlockEntityEvents.BLOCK_ENTITY_LOAD.register(RenderSky::onLoad);
        ClientBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register(RenderSky::onUnload);
        WorldRenderEvents.LAST.register(RenderSky::render);
        ResolutionChangeCallback.EVENT.register(RenderSky::resetFramebuffer);

        CoreShaderRegistrationCallback.EVENT.register(context -> {
            Identifier id = new Identifier(MOD_ID, "sky");
            context.register(id, VertexFormats.POSITION, program -> skyShaderProgram = program);
        });
    }
}
