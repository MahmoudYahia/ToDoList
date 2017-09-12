package com.project.todolist.datamodel;

/**
 * Created by mah_y on 9/10/2017.
 */

public class ItemKeyVal {
    String itemKey;
    Item item;

    public ItemKeyVal(String itemKey, Item item) {
        this.itemKey = itemKey;
        this.item = item;
    }

    public String getItemKey() {
        return itemKey;
    }

    public Item getItem() {
        return item;
    }
}
