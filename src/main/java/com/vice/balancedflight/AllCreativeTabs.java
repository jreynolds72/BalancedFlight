package com.vice.balancedflight;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


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
                        // Some other installed mods force a tab-content rebuild after the initial
                        // build (e.g. CC: Tweaked on server start) without the tab's item list
                        // having been cleared first, so re-adding an already-present item here is
                        // an expected, harmless no-op rather than a real duplicate registration.
                        for (var item : BalancedFlight.CREATE_REGISTRATE.getAll(Registries.ITEM)) {
                            try {
                                pOutput.accept(new ItemStack(item.get()));
                            } catch (IllegalArgumentException alreadyPresent) {
                                // already in the tab from an earlier build pass
                            }
                        }
                    }))
                    .build());

    public static void register(IEventBus modEventBus) {
        TAB_REGISTER.register(modEventBus);
    }
}
