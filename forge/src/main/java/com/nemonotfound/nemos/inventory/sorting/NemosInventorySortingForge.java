package com.nemonotfound.nemos.inventory.sorting;

import com.nemonotfound.nemos.inventory.sorting.client.gui.components.SortAlphabeticallyButton;
import com.nemonotfound.nemos.inventory.sorting.client.gui.components.SortAlphabeticallyReversedButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.*;
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
                createButtons(event, screen, 4);
            } else if (screen instanceof InventoryScreen) {
                createButtons(event, screen, 70);
            } else if (screen instanceof CreativeModeInventoryScreen) {
                createButtons(event, screen, 40, 18);
            }
        }

        private static void createButtons(ScreenEvent.Init.Post event, Screen screen, int yOffset) {
            createButtons(event, screen, yOffset, 0);
        }

        private static void createButtons(ScreenEvent.Init.Post event, Screen screen, int yOffset, int xOffset) {
            AbstractContainerScreen<?> abstractContainerScreen = (AbstractContainerScreen<?>) screen;
            int y = abstractContainerScreen.getGuiTop() + yOffset;
            int size = 12;
            int leftPos = abstractContainerScreen.getGuiLeft();
            int imageWidth = abstractContainerScreen.getXSize();

            //TODO: Change Component
            SortAlphabeticallyButton sortAlphabeticallyButton = new SortAlphabeticallyButton(getLeftPosWithOffset(leftPos, imageWidth, 40) - xOffset, y, 40, size, size, Component.literal("S"), abstractContainerScreen);
            SortAlphabeticallyReversedButton sortAlphabeticallyReversedButton = new SortAlphabeticallyReversedButton(getLeftPosWithOffset(leftPos, imageWidth, 22) - xOffset, y, 22, size, size, Component.literal("S"), abstractContainerScreen);

            event.addListener(sortAlphabeticallyButton);
            event.addListener(sortAlphabeticallyReversedButton);
        }

        private static int getLeftPosWithOffset(int leftPos, int imageWidth, int offset) {
            return leftPos + imageWidth - offset;
        }
    }
}