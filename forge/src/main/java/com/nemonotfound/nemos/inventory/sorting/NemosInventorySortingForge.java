package com.nemonotfound.nemos.inventory.sorting;

import com.nemonotfound.nemos.inventory.sorting.client.gui.components.SortAlphabeticallyButton;
import com.nemonotfound.nemos.inventory.sorting.client.gui.components.SortAlphabeticallyReversedButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NemosInventorySortingForge {

    public NemosInventorySortingForge() {
        CommonClass.init();
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NemosInventorySorterClientForge {

        @SubscribeEvent
        public static void onScreenEvent(ScreenEvent.Init.Post event) {
            Screen screen = event.getScreen();

            if (screen instanceof ContainerScreen || screen instanceof ShulkerBoxScreen) {
                AbstractContainerScreen<?> abstractContainerScreen = (AbstractContainerScreen<?>) screen;
                int y = abstractContainerScreen.getGuiTop() + 4;
                int size = 12;
                int leftPos = abstractContainerScreen.getGuiLeft();
                int imageWidth = abstractContainerScreen.getXSize();

                //TODO: Change Component
                //TODO: menu as argument instead of abstractContainerScreen
                SortAlphabeticallyButton sortAlphabeticallyButton = new SortAlphabeticallyButton(getLeftPosWithOffset(leftPos, imageWidth, 40), y, size, size, Component.literal("S"), abstractContainerScreen);
                SortAlphabeticallyReversedButton sortAlphabeticallyReversedButton = new SortAlphabeticallyReversedButton(getLeftPosWithOffset(leftPos, imageWidth, 22), y, size, size, Component.literal("S"), abstractContainerScreen);

                event.addListener(sortAlphabeticallyButton);
                event.addListener(sortAlphabeticallyReversedButton);
            }
        }

        private static int getLeftPosWithOffset(int leftPos, int imageWidth, int offset) {
            return leftPos + imageWidth - offset;
        }
    }
}