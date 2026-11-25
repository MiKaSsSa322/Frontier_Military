package com.frontiermilitary.mod.entity;

import com.frontiermilitary.mod.FrontierMilitaryMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FrontierMilitaryMod.MOD_ID);

    public static final RegistryObject<EntityType<SoldierEntity>> SOLDIER = ENTITY_TYPES.register("soldier",
            () -> EntityType.Builder.of(SoldierEntity::new, MobCategory.MISC)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(10)
                    .build(FrontierMilitaryMod.MOD_ID + ":soldier"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
        eventBus.addListener(ModEntities::registerAttributes);
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(SOLDIER.get(), SoldierEntity.createAttributes().build());
    }

    private ModEntities() {
    }
}
