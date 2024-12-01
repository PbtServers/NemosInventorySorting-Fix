package com.nemonotfound.nemos.inventory.sorting.mixin;

import com.nemonotfound.nemos.inventory.sorting.client.gui.components.AbstractSortAlphabeticallyButton;
import com.nemonotfound.nemos.inventory.sorting.client.gui.components.SortAlphabeticallyButton;
import com.nemonotfound.nemos.inventory.sorting.client.gui.components.SortAlphabeticallyReversedButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractRecipeBookScreen<InventoryMenu> {

    @Unique
    private SortAlphabeticallyButton nemosInventorySorting$sortAlphabeticallyButton;
    @Unique
    private SortAlphabeticallyReversedButton nemosInventorySorting$sortAlphabeticallyReversedButton;

    public InventoryScreenMixin(InventoryMenu menu, RecipeBookComponent<?> recipeBookComponent, Inventory inventory, Component component) {
        super(menu, recipeBookComponent, inventory, component);
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        int y = this.topPos + 70;
        int size = 12;

        //TODO: Change Component
        nemosInventorySorting$sortAlphabeticallyButton = new SortAlphabeticallyButton(nemosInventorySorting$getLeftPosWithOffset(40), y, size, size, Component.literal("S"), this);
        nemosInventorySorting$sortAlphabeticallyReversedButton = new SortAlphabeticallyReversedButton(nemosInventorySorting$getLeftPosWithOffset(22), y, size, size, Component.literal("S"), this);

        this.addRenderableWidget(nemosInventorySorting$sortAlphabeticallyButton);
        this.addRenderableWidget(nemosInventorySorting$sortAlphabeticallyReversedButton);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (nemosInventorySorting$sortAlphabeticallyButton != null) {
            nemosInventorySorting$setX(nemosInventorySorting$sortAlphabeticallyButton, 40);

            nemosInventorySorting$sortAlphabeticallyButton.render(guiGraphics, mouseX, mouseY, delta);
        }

        if (nemosInventorySorting$sortAlphabeticallyReversedButton != null) {
            nemosInventorySorting$setX(nemosInventorySorting$sortAlphabeticallyReversedButton, 22);

            nemosInventorySorting$sortAlphabeticallyReversedButton.render(guiGraphics, mouseX, mouseY, delta);
        }
    }

    @Unique
    private int nemosInventorySorting$getLeftPosWithOffset(int offset) {
        return this.leftPos + this.imageWidth - offset;
    }

    @Unique
    private void nemosInventorySorting$setX(AbstractSortAlphabeticallyButton sortAlphabeticallyButton, int offset) {
        sortAlphabeticallyButton.setX(this.leftPos + this.imageWidth - offset);
    }
}