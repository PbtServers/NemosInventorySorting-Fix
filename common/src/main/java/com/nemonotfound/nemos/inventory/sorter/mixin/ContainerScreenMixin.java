package com.nemonotfound.nemos.inventory.sorter.mixin;

import com.nemonotfound.nemos.inventory.sorter.client.gui.components.SortAlphabeticallyButton;
import com.nemonotfound.nemos.inventory.sorter.client.gui.components.SortAlphabeticallyReversedButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ContainerScreen.class)
public abstract class ContainerScreenMixin extends AbstractContainerScreen<ChestMenu> {

    public ContainerScreenMixin(ChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    public void init() {
        super.init();

        int y = this.topPos + 4;
        int size = 12;

        //TODO: Change Component
        SortAlphabeticallyButton sortAlphabeticallyButton = new SortAlphabeticallyButton(nemosInventorySorter$getLeftPosWithOffset(40), y, size, size, Component.literal("S"), this);
        SortAlphabeticallyReversedButton sortAlphabeticallyReversedButton = new SortAlphabeticallyReversedButton(nemosInventorySorter$getLeftPosWithOffset(22), y, size, size, Component.literal("S"), this);

        this.addRenderableWidget(sortAlphabeticallyButton);
        this.addRenderableWidget(sortAlphabeticallyReversedButton);
    }

    @Unique
    private int nemosInventorySorter$getLeftPosWithOffset(int offset) {
        return this.leftPos + this.imageWidth - offset;
    }
}