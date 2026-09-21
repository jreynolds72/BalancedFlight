package com.vice.balancedflight.content.flightAnchor;

import com.vice.balancedflight.foundation.render.AnimatedBlockItem;
import com.vice.balancedflight.AllGeckoRenderers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class FlightAnchorItem extends AnimatedBlockItem<FlightAnchorItem>
{
    public FlightAnchorItem(Block block, Properties props) { super(block, props, () -> AllGeckoRenderers.FlightAnchorGeckoRenderer.ItemRenderer); }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, context, tooltip, flag);

        tooltip.add(Component.literal("Allows flight around it based on how much RPM is powering it.").withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.literal("Only works in the overworld.").withStyle(ChatFormatting.RED));
    }
}
