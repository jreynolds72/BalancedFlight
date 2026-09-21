package com.vice.balancedflight.mixins;


import com.vice.balancedflight.content.flightAnchor.FlightController;
import com.vice.balancedflight.foundation.config.BalancedFlightConfig;
import com.vice.balancedflight.foundation.network.BalancedFlightNetwork;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.Instant;

@Mixin(LocalPlayer.class)
public class ElytraRocketShiftKeyMixin
{
    private long LastUsedFireworkTime = 0;

    @Inject(at = @At(value = "HEAD"), method = "tick")
    private void setShiftKeyDown(CallbackInfo ci)
    {
        LocalPlayer player = (LocalPlayer) (Object) this;

        if (player.isFallFlying() && BalancedFlightConfig.infiniteRockets.get())
        {
            FlightController.FlightMode allowed = FlightController.AllowedFlightModes(player, true);
            if (!allowed.canElytraFly())
                return;

            if (player.isSprinting() && player.input.hasForwardImpulse())
            {
                long now = Instant.now().getEpochSecond();
                if (now - LastUsedFireworkTime > 1)
                {
                    BalancedFlightNetwork.sendFireRocket();
                    LastUsedFireworkTime = now;
                }
            }

        }
    }
}
