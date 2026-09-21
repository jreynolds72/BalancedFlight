package com.vice.balancedflight.foundation.compat;

import com.vice.balancedflight.BalancedFlight;
import com.vice.balancedflight.content.angelRing.FlightRing;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class AscendedRingCurio implements ICurio
{
    private final FlightRing parent;

    public AscendedRingCurio(FlightRing parent)
    {
        this.parent = parent;
    }

    public static boolean HasAscendedRing(Player player) { return CuriosApi.getCuriosHelper().findEquippedCurio(BalancedFlight.ASCENDED_FLIGHT_RING.get(), player).isPresent(); }


    @Override public boolean canEquip(SlotContext slotContext) { return !HasAscendedRing((Player) slotContext.entity());}

    @Override public boolean canEquipFromUse(SlotContext slotContext) {
        return true;
    }

    @Override public ItemStack getStack()
    {
        return new ItemStack(parent);
    }

    @Override public SoundInfo getEquipSound(SlotContext slotContext) {
        return new SoundInfo(SoundEvents.ARMOR_EQUIP_ELYTRA.value(), 1.0F, 1.0F);
    }

}
