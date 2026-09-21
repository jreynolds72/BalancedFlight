package com.vice.balancedflight.content.angelRing;

import com.vice.balancedflight.BalancedFlight;
import com.vice.balancedflight.foundation.compat.AscendedRingCurio;
import com.vice.balancedflight.foundation.compat.ExternalMods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;

import java.util.List;

@EventBusSubscriber(modid = BalancedFlight.MODID, bus = EventBusSubscriber.Bus.MOD)
public class FlightRing extends Item {

    public FlightRing(Item.Properties props) { super(props); }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        if (ExternalMods.CURIOS.isLoaded())
            event.registerItem(CuriosCapability.ITEM, (stack, context) -> new AscendedRingCurio((FlightRing) stack.getItem()), BalancedFlight.ASCENDED_FLIGHT_RING.get());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("An incredibly dense golden ring. Despite its weight, it allows you to fly anywhere (Angel Ring).").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.literal("Allows both creative and enhanced Elytra flight.").withStyle(ChatFormatting.WHITE));
    }
}
