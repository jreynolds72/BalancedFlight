package com.vice.balancedflight.foundation.network;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class BalancedFlightNetwork
{
    public static final String VERSION = "1";

    public static void registerMessage(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(VERSION);

        registrar.playToServer(FireRocketPacket.TYPE, FireRocketPacket.STREAM_CODEC, FireRocketPacket::handle);
    }

    public static void sendFireRocket() {
        PacketDistributor.sendToServer(FireRocketPacket.INSTANCE);
    }
}
