package com.frontiermilitary.mod.block.entity;

import com.frontiermilitary.mod.FrontierMilitaryMod;
import com.frontiermilitary.mod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FrontierMilitaryMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MilitaryCrateBlockEntity>> MILITARY_CRATE =
            BLOCK_ENTITIES.register("military_crate", () -> BlockEntityType.Builder.of(
                    MilitaryCrateBlockEntity::new, ModBlocks.MILITARY_CRATE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    private ModBlockEntities() {
    }
}
