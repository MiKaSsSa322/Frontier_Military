package com.frontiermilitary.mod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MilitaryCrateBlockEntity extends RandomizableContainerBlockEntity {

    public static final int CONTAINER_SIZE = 27;

    private NonNullList<ItemStack> items =
            NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public MilitaryCrateBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MILITARY_CRATE.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(
                "container.frontiermilitary.military_crate"
        );
    }

    @Override
    protected AbstractContainerMenu createMenu(
            int containerId,
            Inventory inventory
    ) {
        return ChestMenu.threeRows(containerId, inventory, this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }
}
