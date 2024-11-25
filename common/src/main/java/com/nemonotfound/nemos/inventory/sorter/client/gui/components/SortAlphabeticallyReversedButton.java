package com.nemonotfound.nemos.inventory.sorter.client.gui.components;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.Map;

import static com.nemonotfound.nemos.inventory.sorter.Constants.MOD_ID;

public class SortAlphabeticallyReversedButton extends AbstractSortAlphabeticallyButton {

    private final ResourceLocation buttonTexture = ResourceLocation.fromNamespaceAndPath(MOD_ID, "sort_button_alphabetically_dec");
    private final ResourceLocation buttonHoverTexture = ResourceLocation.fromNamespaceAndPath(MOD_ID, "sort_button_alphabetically_dec_highlighted");

    public SortAlphabeticallyReversedButton(int x, int y, int width, int height, Component message, AbstractContainerScreen<?> containerScreen) {
        super(x, y, width, height, message, containerScreen);
    }

    @Override
    protected ResourceLocation getButtonHoverTexture() {
        return buttonHoverTexture;
    }

    @Override
    protected ResourceLocation getButtonTexture() {
        return buttonTexture;
    }

    @Override
    protected Comparator<Map.Entry<Integer, ItemStack>> compare() {
        return super.compare().reversed();
    }
}
