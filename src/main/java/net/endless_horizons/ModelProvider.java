package net.endless_horizons;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.endless_horizons.blocks.models.BlockModel;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static net.endless_horizons.EndlessHorizons.MOD_ID;

public class ModelProvider implements ModelResourceProvider {
    public ArrayList<String> colors = new ArrayList<>();

    @Override
    public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) {
        colors.add("white"); colors.add("orange"); colors.add("magenta"); colors.add("light_blue");
        colors.add("yellow"); colors.add("lime"); colors.add("pink"); colors.add("gray");
        colors.add("light_gray"); colors.add("cyan"); colors.add("purple"); colors.add("blue");
        colors.add("brown"); colors.add("green"); colors.add("red"); colors.add("black");

        for (int i = 0; i < 16; i++) {
            if (identifier.equals(new Identifier(MOD_ID + ":block/endless_" + colors.get(i) + "_model")) ||
            identifier.equals(new Identifier(MOD_ID + ":item/endless_" + colors.get(i)))) {
                return new BlockModel(colors.get(i));
            }
        }
        return null;
    }
}
