package net.endless_horizons.client;

import net.endless_horizons.ModelProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.endless_horizons.blocks.end_gateway.EndlessEndEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static net.endless_horizons.EndlessHorizons.EndlessEndEntity;


@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class EndlessHorizonsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModelProvider());
        BlockEntityRendererFactories.register(EndlessEndEntity, ctx -> new EndlessEndEntityRenderer());
    }
}
