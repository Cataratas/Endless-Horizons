package net.endless_horizons.mixins;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.endless_horizons.blocks.Blocks.NO_OUTLINE;

@Mixin(GameRenderer.class)
public class NoOutline {
    @Final
    @Shadow
    MinecraftClient client;

    @Inject(method = "shouldRenderBlockOutline", at = @At(value = "HEAD", target = "Lnet/minecraft/client/render/GameRenderer;shouldRenderBlockOutline()Z"), cancellable = true)
    public void shouldRenderBlockOutline(CallbackInfoReturnable<Boolean> cir) {
        HitResult hitResult = this.client.crosshairTarget;
        if (hitResult != null) {
            assert this.client.world != null;
            if (this.client.world.getBlockState(((BlockHitResult) hitResult).getBlockPos()).isIn(NO_OUTLINE)) {
                cir.setReturnValue(false);
            }
        }
    }
}
