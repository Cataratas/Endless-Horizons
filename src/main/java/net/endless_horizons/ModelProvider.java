package net.endless_horizons;

import net.endless_horizons.blocks.models.*;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import java.util.Arrays;

import static net.endless_horizons.EndlessHorizons.MOD_ID;


public class ModelProvider implements ModelResourceProvider {

    @Override
    public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) {
        for (var color : Arrays.stream(DyeColor.values()).filter(x -> x != DyeColor.BLACK).toList()) {
            if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + color + "_model")) || identifier.equals(new Identifier(MOD_ID + ":item/endless_" + color))) {
                return new BlockModel(color);
            }
            else if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + color + "_door_model")))
                return new DoorModel(color);
            else if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + color + "_trapdoor_model")))
                return new TrapdoorModel(color);
            else if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + color + "_button_model")))
                return new ButtonModel(color);
            else if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + color + "_pressure_plate_model")))
                return new PressurePlateModel(color);
        }
        return null;
    }
}
