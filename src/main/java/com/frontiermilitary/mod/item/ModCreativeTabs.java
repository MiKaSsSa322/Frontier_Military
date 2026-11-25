package com.frontiermilitary.mod.item;

import com.frontiermilitary.mod.FrontierMilitaryMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Single creative inventory tab holding every Frontier Military item and
 * block, in a sensible order (blocks, gear, weapons, ammo, misc).
 */
public final class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FrontierMilitaryMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FRONTIER_MILITARY_TAB = CREATIVE_MODE_TABS.register(
            "frontier_military", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.frontiermilitary"))
                    .icon(() -> new ItemStack(ModItems.SERVICE_RIFLE.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.MILITARY_FENCE.get());
                        output.accept(ModItems.SECURITY_BARS.get());
                        output.accept(ModItems.CONCRETE_BARRIER.get());
                        output.accept(ModItems.BARBED_WIRE.get());
                        output.accept(ModItems.MILITARY_CRATE.get());

                        output.accept(ModItems.MILITARY_HELMET.get());
                        output.accept(ModItems.MILITARY_VEST.get());
                        output.accept(ModItems.MILITARY_PANTS.get());
                        output.accept(ModItems.MILITARY_BOOTS.get());

                        output.accept(ModItems.SERVICE_RIFLE.get());
                        output.accept(ModItems.TACTICAL_SHOTGUN.get());
                        output.accept(ModItems.SERVICE_PISTOL.get());
                        output.accept(ModItems.RIFLE_AMMO.get());
                        output.accept(ModItems.SHOTGUN_SHELL.get());

                        output.accept(ModItems.FIELD_RATION.get());
                        output.accept(ModItems.FIRST_AID_KIT.get());
                        output.accept(ModItems.REINFORCED_FIBER.get());
                        output.accept(ModItems.DOG_TAG.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    private ModCreativeTabs() {
    }
}
