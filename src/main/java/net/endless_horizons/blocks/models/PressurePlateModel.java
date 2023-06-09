package net.endless_horizons.blocks.models;


import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.block.PressurePlateBlock.POWERED;

public class PressurePlateModel extends BlockModel {

    public PressurePlateModel(DyeColor color) {
        super(color);
    }

    @Override
    public BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        SPRITES[0] = textureGetter.apply(SPRITE_IDS[0]);
        return this;
    }

    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();

        assert renderer != null;
        MeshBuilder builder = renderer.meshBuilder();
        RenderMaterial myMaterial = renderer.materialFinder().disableDiffuse(0, true).find();
        QuadEmitter emitter = context.getEmitter();

        for(Direction direction : Direction.values()) {
            emitter.material(myMaterial);
            if (direction == Direction.UP && state.get(POWERED)) {
                emitter.square(direction, 0.9375f, 0.9375f, 0.0625f, 0.0625f, 0.96875f);
            } else if (direction == Direction.UP) {
                emitter.square(direction, 0.9375f, 0.9375f, 0.0625f, 0.0625f, 0.9375f);
            } else if ((direction == Direction.NORTH && state.get(POWERED)) || ((direction == Direction.SOUTH && state.get(POWERED)))
                    || (direction == Direction.WEST && state.get(POWERED)) || (direction == Direction.EAST && state.get(POWERED))) {
                emitter.square(direction, 0.9375f, 0.03125f, 0.0625f, 0.0f, 0.0625f);
            } else if (direction == Direction.NORTH || direction == Direction.SOUTH || direction == Direction.WEST || direction == Direction.EAST) {
                emitter.square(direction, 0.9375f, 0.0625f, 0.0625f, 0.0f, 0.0625f);
            }

            emitter.spriteBake(0, SPRITES[0], MutableQuadView.BAKE_LOCK_UV);
            emitter.spriteColor(0, -1, -1, -1, -1);
            emitter.emit();
        }
        mesh = builder.build();

        context.meshConsumer().accept(mesh);
    }
}
