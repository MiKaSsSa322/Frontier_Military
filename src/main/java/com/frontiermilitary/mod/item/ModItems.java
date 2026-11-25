package com.frontiermilitary.mod.item;

import com.frontiermilitary.mod.FrontierMilitaryMod;
import com.frontiermilitary.mod.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Item and BlockItem registrations for Frontier Military.
 * <p>
 * Armor pieces reuse {@link ModArmorMaterials#MILITARY} so the full
 * helmet/vest/pants/boots set shares one protection/durability profile.
 */
public final class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FrontierMilitaryMod.MOD_ID);

    public static final RegistryObject<Item> MILITARY_FENCE = ITEMS.register("military_fence",
            () -> new BlockItem(ModBlocks.MILITARY_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SECURITY_BARS = ITEMS.register("security_bars",
            () -> new BlockItem(ModBlocks.SECURITY_BARS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONCRETE_BARRIER = ITEMS.register("concrete_barrier",
            () -> new BlockItem(ModBlocks.CONCRETE_BARRIER.get(), new Item.Properties()));
    public static final RegistryObject<Item> BARBED_WIRE = ITEMS.register("barbed_wire",
            () -> new BlockItem(ModBlocks.BARBED_WIRE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MILITARY_CRATE = ITEMS.register("military_crate",
            () -> new BlockItem(ModBlocks.MILITARY_CRATE.get(), new Item.Properties()));

    public static final RegistryObject<Item> FIELD_RATION = ITEMS.register("field_ration",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6).saturationMod(0.7F).build())));

    public static final RegistryObject<Item> FIRST_AID_KIT = ITEMS.register("first_aid_kit",
            () -> new FirstAidKitItem(new Item.Properties().stacksTo(8)));

    public static final RegistryObject<Item> DOG_TAG = ITEMS.register("dog_tag",
            () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> REINFORCED_FIBER = ITEMS.register("reinforced_fiber",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> RIFLE_AMMO = ITEMS.register("rifle_ammo",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SHOTGUN_SHELL = ITEMS.register("shotgun_shell",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> SERVICE_RIFLE = ITEMS.register("service_rifle",
            () -> new FirearmItem(new Item.Properties().stacksTo(1).durability(600),
                    6.0F, 40.0D, 6, RIFLE_AMMO.get()));

    public static final RegistryObject<Item> TACTICAL_SHOTGUN = ITEMS.register("tactical_shotgun",
            () -> new FirearmItem(new Item.Properties().stacksTo(1).durability(400),
                    9.0F, 14.0D, 16, SHOTGUN_SHELL.get()));

    public static final RegistryObject<Item> SERVICE_PISTOL = ITEMS.register("service_pistol",
            () -> new FirearmItem(new Item.Properties().stacksTo(1).durability(300),
                    4.0F, 24.0D, 4, RIFLE_AMMO.get()));

    public static final RegistryObject<Item> MILITARY_HELMET = ITEMS.register("military_helmet",
            () -> new ArmorItem(ModArmorMaterials.MILITARY, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MILITARY_VEST = ITEMS.register("military_vest",
            () -> new ArmorItem(ModArmorMaterials.MILITARY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MILITARY_PANTS = ITEMS.register("military_pants",
            () -> new ArmorItem(ModArmorMaterials.MILITARY, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MILITARY_BOOTS = ITEMS.register("military_boots",
            () -> new ArmorItem(ModArmorMaterials.MILITARY, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private ModItems() {
    }
}
