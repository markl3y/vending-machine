package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {

    //Instance Vars...
    private Map<Item, Integer> inventory = new HashMap<>();
    private List<Item> items = new ArrayList<>();
    private MoneyBox moneyBox = new MoneyBox();

    //Constructor
    public VendingMachine(File inputFile) throws IOException {
        try {
            Scanner fileInput = new Scanner(inputFile);
            String line;
            while (fileInput.hasNextLine()) {
                line = fileInput.nextLine();
                String[] lineData = line.split("\\|");
                Item lineItem = new Item(lineData[0], lineData[1], BigDecimal.valueOf(Double.parseDouble(lineData[2])), lineData[3]);
                items.add(lineItem);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Database file not found.");
            System.exit(1);
        }

        for (Item item : items) {
            inventory.put(item, 5);
        }
    }

    //GET
    public BigDecimal getBalance() {
        return moneyBox.getBalance();
    }

    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    public Item getItem(String codeLookup) {
        for (Item item : items) {
            if (item.getItemSlot().equals(codeLookup)) {
                return item;
            }
        }
        return null;
    }

    public int getItemQuantity(Item item) {
        for (Item key : inventory.keySet()) {
            if (item.equals(key)) {
                return inventory.get(key);
            }
        }
        return 0;
    }

    //Methods
    public void printCurrent(){
        System.out.println("\n**********Current Inventory**********\n");
        for (Item item : items) {
            if (inventory.get(item) != 0) {
            System.out.println(item.getItemSlot() + "| " + item.getItemName() + " (" + NumberFormat.getCurrencyInstance().format(item.getPrice()) + ") x " + inventory.get(item));}
            else {
                System.out.println(item.getItemSlot() + "| " + item.getItemName() + " (" + NumberFormat.getCurrencyInstance().format(item.getPrice()) + ") <<< SOLD OUT");
            }
        }
    }

    public void insertBill(BigDecimal billAmount) {
        moneyBox.addMoney(billAmount);
    }

    public void dispenseItem(Item item) {
        if (this.getItemQuantity(item) != 0) {
            moneyBox.makePurchase(item);
            inventory.put(item, inventory.get(item) - 1);
        }
        String flavorMessage = "";
        switch (item.getItemType()) {
            case "Chip":
                flavorMessage = "\nCrunch Crunch, Yum!";
                break;
            case "Candy":
                flavorMessage = "\nMunch Munch, Yum!";
                break;
            case "Drink":
                flavorMessage = "\nGlug Glug, Yum!";
                break;
            case "Gum":
                flavorMessage = "\nChew Chew, Yum!";
                break;
        }

        //item name, cost, and the money remaining, plus the flavor text.
        System.out.println("\nNow dispensing: " + item.getItemName() + ".\n\nYou have been charged " + NumberFormat.getCurrencyInstance().format(item.getPrice()) + ", and have " + NumberFormat.getCurrencyInstance().format(moneyBox.getBalance()) + " remaining.\n" + flavorMessage);
    }

    public void dispenseChange() {
        int[] coins = moneyBox.makeChange();
        System.out.println("\nNow dispensing change: " + coins[0] + " quarters, " + coins[1] + " dimes, and " + coins[2] + " nickels.");
    }

    public void logSales() {
        moneyBox.logSales(inventory);
    }
}
