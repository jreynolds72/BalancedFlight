package com.vice.balancedflight.foundation.network;

import com.vice.balancedflight.BalancedFlight;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record FireRocketPacket() implements CustomPacketPayload {
    public static final FireRocketPacket INSTANCE = new FireRocketPacket();

    public static final Type<FireRocketPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(BalancedFlight.MODID, "fire_rocket"));

    public static final StreamCodec<ByteBuf, FireRocketPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(FireRocketPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            ItemStack itemstack = new ItemStack(Items.FIREWORK_ROCKET, 64);
            player.level().addFreshEntity(new FireworkRocketEntity(player.level(), itemstack, player));
        });
    }
}
