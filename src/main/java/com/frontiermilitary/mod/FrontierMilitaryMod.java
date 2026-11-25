package com.frontiermilitary.mod;

import com.frontiermilitary.mod.block.ModBlocks;
import com.frontiermilitary.mod.block.entity.ModBlockEntities;
import com.frontiermilitary.mod.config.FrontierMilitaryConfig;
import com.frontiermilitary.mod.entity.ModEntities;
import com.frontiermilitary.mod.item.ModCreativeTabs;
import com.frontiermilitary.mod.item.ModItems;
import com.frontiermilitary.mod.sound.ModSounds;
import com.frontiermilitary.mod.world.loot.ModLootModifiers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main mod entry point for Frontier Military.
 * <p>
 * This file wires up every registry (blocks, items, entities, block
 * entities, sounds) and the mod configuration. Keep this class thin;
 * actual logic lives in the dedicated packages next to this one.
 */
@Mod(FrontierMilitaryMod.MOD_ID)
public class FrontierMilitaryMod {

    public static final String MOD_ID = "frontiermilitary";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public FrontierMilitaryMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FrontierMilitaryConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Frontier Military: common setup complete.");
    }

    private void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.SOLDIER.get(),
                com.frontiermilitary.mod.entity.client.SoldierRenderer::new);
        LOGGER.info("Frontier Military: client setup complete.");
    }
}
