package com.nemonotfound.nemos.inventory.sorting.interfaces;

public interface GuiPosition {

    default int nemosInventorySorting$getLeftPos() {
        return 0;
    };

    default int nemosInventorySorting$getImageWidth() {
        return 0;
    }
}
