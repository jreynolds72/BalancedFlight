package com.vice.balancedflight;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;
import java.util.Set;


public class AllCreativeTabs
{
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BalancedFlight.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = TAB_REGISTER.register("base",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.balancedflight.base"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(BalancedFlight.FLIGHT_ANCHOR_BLOCK::asStack)
                    .displayItems(((pParameters, pOutput) -> {
                        Set<Item> added = new HashSet<>();
                        for (var item : BalancedFlight.CREATE_REGISTRATE.getAll(Registries.ITEM))
                            if (added.add(item.get()))
                                pOutput.accept(new ItemStack(item.get()));
                    }))
                    .build());

    public static void register(IEventBus modEventBus) {
        TAB_REGISTER.register(modEventBus);
    }
}
