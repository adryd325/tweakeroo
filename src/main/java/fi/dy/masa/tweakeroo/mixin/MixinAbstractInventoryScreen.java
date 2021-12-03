package fi.dy.masa.tweakeroo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.util.math.MatrixStack;
import fi.dy.masa.tweakeroo.config.Configs;

@Mixin(net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen.class)
public abstract class MixinAbstractInventoryScreen<T extends net.minecraft.screen.ScreenHandler>
       extends net.minecraft.client.gui.screen.ingame.HandledScreen<T>
{

    public MixinAbstractInventoryScreen(
            T container,
            net.minecraft.entity.player.PlayerInventory playerInventory,
            net.minecraft.text.Text textComponent)
    {
        super(container, playerInventory, textComponent);
    }

    @Inject(method = "drawStatusEffects", at = @At("HEAD"), cancellable = true)
    private void disableStatusEffectRendering2(MatrixStack matrices, int mouseX, int mouseY, CallbackInfo ci)
    {
        if (Configs.Disable.DISABLE_INVENTORY_EFFECTS.getBooleanValue())
        {
            ci.cancel();
        }
    }
}
