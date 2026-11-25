package com.frontiermilitary.mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Common (server+client, synced) configuration.
 */
public final class FrontierMilitaryConfig {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue FRIENDLY_SOLDIERS_ENABLED;
    public static final ForgeConfigSpec.BooleanValue HOSTILE_SOLDIERS_ENABLED;
    public static final ForgeConfigSpec.IntValue MAX_SOLDIERS_PER_CAMP;

    public static final ForgeConfigSpec.DoubleValue SMALL_CAMP_FREQUENCY;
    public static final ForgeConfigSpec.DoubleValue LARGE_BASE_FREQUENCY;

    public static final ForgeConfigSpec.BooleanValue LOOT_ENABLED;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("npc");
        FRIENDLY_SOLDIERS_ENABLED = builder
                .comment("Allow friendly-faction soldiers to spawn with camps/bases.")
                .define("friendlySoldiersEnabled", true);
        HOSTILE_SOLDIERS_ENABLED = builder
                .comment("Allow hostile-faction soldiers to spawn with camps/bases.")
                .define("hostileSoldiersEnabled", true);
        MAX_SOLDIERS_PER_CAMP = builder
                .comment("Soft cap on how many soldiers a single camp/base tries to keep alive.")
                .defineInRange("maxSoldiersPerCamp", 6, 1, 32);
        builder.pop();

        builder.push("generation");
        SMALL_CAMP_FREQUENCY = builder
                .comment("Relative frequency multiplier for small military camps (1.0 = default).",
                        "Placeholder hook for the structure frequency once procedural camp generation ships.")
                .defineInRange("smallCampFrequency", 1.0D, 0.0D, 4.0D);
        LARGE_BASE_FREQUENCY = builder
                .comment("Relative frequency multiplier for large military bases (1.0 = default).",
                        "Kept very low by design: large bases are meant to be a rare discovery.")
                .defineInRange("largeBaseFrequency", 0.15D, 0.0D, 4.0D);
        builder.pop();

        builder.push("loot");
        LOOT_ENABLED = builder
                .comment("Enable Frontier Military loot tables for crates and camp containers.")
                .define("lootEnabled", true);
        builder.pop();

        SPEC = builder.build();
    }

    private FrontierMilitaryConfig() {
    }
}
