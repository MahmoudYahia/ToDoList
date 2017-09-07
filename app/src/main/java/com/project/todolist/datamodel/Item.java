package com.project.todolist.datamodel;


/**
 * Created by mah_y on 8/28/2017.
 */

public class Item {
    private String itemId;
    private String itemOwnerId;
    private String itemTitle;
    private String itemDesc;

    public Item() {
    }

    public Item(String itemId, String itemOwnerId, String itemTitle, String itemDesc) {
        this.itemId = itemId;
        this.itemOwnerId = itemOwnerId;
        this.itemTitle = itemTitle;
        this.itemDesc = itemDesc;
    }

    public Item(String itemOwnerId, String itemTitle, String itemDesc) {
        this.itemOwnerId = itemOwnerId;
        this.itemTitle = itemTitle;
        this.itemDesc = itemDesc;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemOwnerId() {
        return itemOwnerId;
    }

    public void setItemOwnerId(String itemOwnerId) {
        this.itemOwnerId = itemOwnerId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
