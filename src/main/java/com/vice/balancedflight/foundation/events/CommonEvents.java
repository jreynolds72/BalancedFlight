package com.vice.balancedflight.foundation.events;

import com.vice.balancedflight.content.flightAnchor.FlightController;
import com.vice.balancedflight.foundation.compat.AscendedRingCurio;
import com.vice.balancedflight.foundation.config.BalancedFlightConfig;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class CommonEvents
{
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event)
    {
        Player player = event.getEntity();
        FlightController.tick(player);
    }


    @SubscribeEvent
    public static void onLivingAttack(LivingIncomingDamageEvent event) {
        if (event.getSource().is(DamageTypes.FLY_INTO_WALL) && BalancedFlightConfig.disableElytraDamage.get()) {
            if (event.getEntity() instanceof Player player) {
                if (FlightController.AllowedFlightModes(player, true) != FlightController.FlightMode.None)
                    event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Pre event) {
        if (event.getSource().is(DamageTypes.FALL)) {
            if (event.getEntity() instanceof Player player) {
                if (AscendedRingCurio.HasAscendedRing(player) && BalancedFlightConfig.disableFallDamageWhenWearingRing.get())
                    event.setNewDamage(0);

                if (BalancedFlightConfig.disableFallDamageNearAnchor.get() && FlightController.AllowedFlightModes(player, false) != FlightController.FlightMode.None)
                    event.setNewDamage(0);
            }
        }
    }
}
