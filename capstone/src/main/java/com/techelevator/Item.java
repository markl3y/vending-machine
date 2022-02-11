package com.techelevator;

import java.math.BigDecimal;

public class Item {

    //Instance Vars
    private BigDecimal price;
    private String itemSlot;
    private String itemType;
    private String itemName;


    //Constructor
    public Item(String itemSlot, String itemName, BigDecimal price, String itemType) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.price = price;
        this.itemType = itemType;
    }

    //Gets (no sets)
    public String getItemSlot() {
        return itemSlot;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

}
