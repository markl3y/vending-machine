package com.techelevator;

public class ChipItem extends Item {

    //probably move to Item or a list
    private int inventory = 5;
    private String dispenseMessage = "Crunch Crunch, Yum!";

    public ChipItem(String itemSlot, String itemName, double price, String itemType) {
        super(itemSlot, itemName, price, itemType);
    }

    public void purchase() {
        if (this.inventory > 0) {
            this.inventory -= 1;
        }
    }

    //probably make this an override
    public String getDispenseMessage() {
        return dispenseMessage;
    }
}
