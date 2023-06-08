package net.endless_horizons;

import net.endless_horizons.blocks.Blocks;
import net.endless_horizons.blocks.end_gateway.EndlessEndEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.endless_horizons.blocks.Blocks.EndlessEndBlock;

public class EndlessHorizons implements ModInitializer {
    public static String MOD_ID = "endless_horizons";
    public static BlockEntityType<EndlessEndEntity> EndlessEndEntity;

    @Override
    public void onInitialize() {
        Blocks.register();

        EndlessEndEntity = Registry.register(Registries.BLOCK_ENTITY_TYPE, MOD_ID + ":endless_end_entity", FabricBlockEntityTypeBuilder.create(EndlessEndEntity::new, EndlessEndBlock).build(null));
    }
}