package net.endless_horizons;

import net.endless_horizons.blocks.models.DoorModel;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.endless_horizons.blocks.models.BlockModel;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static net.endless_horizons.EndlessHorizons.MOD_ID;

public class ModelProvider implements ModelResourceProvider {

    @Override
    public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) {
        for (var color : DyeColor.values()) {
            if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + color + "_model")) ||
                    identifier.equals(new Identifier(MOD_ID + ":item/endless_" + color))) {
                return new BlockModel(color);
            } else if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + color + "_door_model"))) {
                return new DoorModel(color);
            }
        }
        return null;
    }
}
