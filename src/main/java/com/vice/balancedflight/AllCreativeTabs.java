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

    // NeoForge's own dispatch collects each build's stacks into a fresh, deduped set (see
    // EventHooks#onCreativeModeTabBuildContents) before writing them into the tab's backing
    // store - it's that later write that throws on a duplicate, and it happens outside any
    // code of ours we could wrap in a try/catch. Some other installed mod forces a second
    // rebuild of every tab on server start (CC: Tweaked, via CreativeModeTabs#tryRebuildTabContents)
    // without clearing that backing store first, so this set has to live outside the lambda
    // and persist across invocations to know what's already been reported.
    private static final Set<Item> ALREADY_REPORTED = new HashSet<>();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = TAB_REGISTER.register("base",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.balancedflight.base"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(BalancedFlight.FLIGHT_ANCHOR_BLOCK::asStack)
                    .displayItems(((pParameters, pOutput) -> {
                        for (var item : BalancedFlight.CREATE_REGISTRATE.getAll(Registries.ITEM))
                            if (ALREADY_REPORTED.add(item.get()))
                                pOutput.accept(new ItemStack(item.get()));
                    }))
                    .build());

    public static void register(IEventBus modEventBus) {
        TAB_REGISTER.register(modEventBus);
    }
}
