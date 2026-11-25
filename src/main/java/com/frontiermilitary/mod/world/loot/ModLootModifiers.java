package com.frontiermilitary.mod.world.loot;

/**
 * Placeholder for future global loot modifiers. Deliberately empty in this
 * pass: crate loot is driven entirely by
 * data/frontiermilitary/loot_table/blocks/military_crate.json, which needs
 * no code.
 */
public final class ModLootModifiers {

    public static void register(net.minecraftforge.eventbus.api.IEventBus eventBus) {
        // No global loot modifiers yet.
    }

    private ModLootModifiers() {
    }
}
