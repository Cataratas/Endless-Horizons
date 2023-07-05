package net.endless_horizons.client;

import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import ladysnake.satin.api.event.PostWorldRenderCallbackV2;
import ladysnake.satin.api.event.ResolutionChangeCallback;
import ladysnake.satin.impl.FramebufferWrapper;
import net.endless_horizons.ModelProvider;
import net.endless_horizons.blocks.sky.EndlessSkyEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.endless_horizons.blocks.end_gateway.EndlessEndEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.RenderPhase.TextureBase;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.TextureStitcher;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

import static net.endless_horizons.EndlessHorizons.*;


@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class EndlessHorizonsClient implements ClientModInitializer {
    public static ShaderProgram skyShaderProgram;
    //public static VertexBuffer skyBuffer;
    public static SimpleFramebuffer skyTexture;

    public static ShaderProgram getSkyShader() { return skyShaderProgram; }

    private static void setSkyTexture() { RenderSystem.setShaderTexture(0, skyTexture != null ? skyTexture.getColorAttachment() : 0); }
    //texture(RenderPhase.Textures.create().add(EndPortalBlockEntityRenderer.SKY_TEXTURE, false, false)
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



        PostWorldRenderCallbackV2.EVENT.register(RenderSky::render);
        ResolutionChangeCallback.EVENT.register(RenderSky::resetFramebuffer);

        CoreShaderRegistrationCallback.EVENT.register(context -> {
            Identifier id = new Identifier(MOD_ID, "sky");
            context.register(id, VertexFormats.POSITION, program -> skyShaderProgram = program);
        });
    }
}
