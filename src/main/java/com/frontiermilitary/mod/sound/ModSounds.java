package com.frontiermilitary.mod.sound;

import com.frontiermilitary.mod.FrontierMilitaryMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Custom sound events; definitions live in assets/frontiermilitary/sounds.json.
 */
public final class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FrontierMilitaryMod.MOD_ID);

    public static final RegistryObject<SoundEvent> WEAPON_FIRE = register("weapon_fire");
    public static final RegistryObject<SoundEvent> WEAPON_RELOAD = register("weapon_reload");
    public static final RegistryObject<SoundEvent> WEAPON_DRY_FIRE = register("weapon_dry_fire");
    public static final RegistryObject<SoundEvent> CRATE_OPEN = register("crate_open");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () ->
                SoundEvent.createVariableRangeEvent(new ResourceLocation(FrontierMilitaryMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    private ModSounds() {
    }
}
