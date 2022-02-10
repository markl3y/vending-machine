package com.techelevator;

public abstract class Item {

    //Instance Vars
    private double price;
    private String itemCode;
    private String itemType;
    private String itemName;

    //Constructor
    public Item(String itemCode, String itemName, double price, String itemType) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
        this.itemType = itemType;
    }

    //Gets (no sets)
    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }
}
