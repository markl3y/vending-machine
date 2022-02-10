package com.techelevator;

public class Item {

    //Instance Vars
    private double price;
    private String itemSlot;
    private String itemType;
    private String itemName;


    //Constructor
    public Item(String itemSlot, String itemName, double price, String itemType) {
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

    public double getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

}
