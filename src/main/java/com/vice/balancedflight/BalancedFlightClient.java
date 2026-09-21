package com.vice.balancedflight;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = BalancedFlight.MODID, dist = Dist.CLIENT)
public class BalancedFlightClient
{
    public BalancedFlightClient(IEventBus modEventBus) {
        modEventBus.addListener(BalancedFlightClient::clientInit);
    }

    public static void clientInit(FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new BalancedFlightPonderPlugin());
    }
}
