package com.frontiermilitary.mod.block;

import com.frontiermilitary.mod.FrontierMilitaryMod;
import com.frontiermilitary.mod.block.entity.MilitaryCrateBlock;
import net.minecraft.sounds.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * All block registrations for Frontier Military.
 * <p>
 * Fence / wall / bars behaviour is reused from vanilla ({@link FenceBlock},
 * {@link WallBlock}, {@link IronBarsBlock}) so connection logic (posts, N/S/E/W
 * sides) works for free via the matching blockstate/model files; only
 * textures, models and block properties are custom.
 */
public final class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FrontierMilitaryMod.MOD_ID);

    public static final RegistryObject<Block> MILITARY_FENCE = BLOCKS.register("military_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(6.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SECURITY_BARS = BLOCKS.register("security_bars",
            () -> new IronBarsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(6.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final RegistryObject<Block> CONCRETE_BARRIER = BLOCKS.register("concrete_barrier",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(10.0F, 20.0F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BARBED_WIRE = BLOCKS.register("barbed_wire",
            () -> new IronBarsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final RegistryObject<Block> MILITARY_CRATE = BLOCKS.register("military_crate",
            () -> new MilitaryCrateBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2.5F, 6.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private ModBlocks() {
    }
}
