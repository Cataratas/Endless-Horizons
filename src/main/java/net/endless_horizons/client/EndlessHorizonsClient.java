package net.endless_horizons.client;

import net.endless_horizons.ModelProvider;
import net.endless_horizons.blocks.sky.EndlessSkyEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.endless_horizons.blocks.end_gateway.EndlessEndEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import net.minecraft.util.Identifier;


import static net.endless_horizons.EndlessHorizons.*;


@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class EndlessHorizonsClient implements ClientModInitializer {
    public static ShaderProgram skyShaderProgram;

    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModelProvider());
        BlockEntityRendererFactories.register(EndlessEndEntity, ctx -> new EndlessEndEntityRenderer());
        BlockEntityRendererFactories.register(EndlessSkyEntity, ctx -> new EndlessSkyEntityRenderer());

        CoreShaderRegistrationCallback.EVENT.register(context -> {
            Identifier id = new Identifier(MOD_ID, "sky");
            context.register(id, VertexFormats.POSITION, program -> skyShaderProgram = program);
        });
    }
}
