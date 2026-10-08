package xyz.tryfle.freelookenabler.mixin;

import org.codeberg.chromatic.freelook.handler.FreelookHandler;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "org.codeberg.chromatic.freelook.handler.FreelookHandler", remap = false, priority = 999)
public class FreelookHandlerMixin {

    @Redirect(method = "tick",
            at = @At(value = "FIELD",
                    target = "Lorg/codeberg/chromatic/freelook/handler/FreelookHandler;enabledServer:Z",
                    opcode = Opcodes.GETFIELD))
    private boolean enabledServerHook(FreelookHandler self) {
        return true;
    }

    @Redirect(method = "tick",
            at = @At(value = "INVOKE",
                    target = "Lorg/polyfrost/oneconfig/api/hypixel/v1/HypixelUtils;isHypixel()Z"))
    private boolean hypixelCheckHook() {
        return false;
    }
}