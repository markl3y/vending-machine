package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    //Instance Vars...
    private Map<Item, Integer> inventory = new HashMap<>();
    private List<Item> items = new ArrayList<>();
    private double balance;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a ");

    //Constructor
    public VendingMachine(File inputFile) {
        try {
            Scanner fileInput = new Scanner(inputFile);
            String line = "";
            while (fileInput.hasNextLine()) {
                line = fileInput.nextLine();
                String[] lineData = line.split("\\|");
                Item lineItem = new Item(lineData[0], lineData[1], Double.parseDouble(lineData[2]), lineData[3]);
                items.add(lineItem);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Database file not found.");
            System.exit(1);
        }

        for (Item item : items) {
            inventory.put(item, 5);
        }
        this.balance = 0;
    }

    //GET
    public double getBalance() {
        return this.balance;
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
            System.out.println(item.getItemSlot() + "| " + item.getItemName() + " ($" + item.getPrice() + ") x " + inventory.get(item));}
            else {
                System.out.println(item.getItemSlot() + "| " + item.getItemName() + " ($" + item.getPrice() + ") <<< SOLD OUT");
            }
        }
    }

    public void insertBill(double billAmount) {
        this.balance += billAmount;

        // Log transaction
        try (PrintWriter log = new PrintWriter(new FileWriter("log.txt", true))) {
            String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
            log.println(formattedDate + "FEED MONEY: $" + (this.getBalance() - billAmount) + " $" + balance);
        } catch (IOException e) {
            System.out.println("Caught an IOException. Message: " + e.getMessage());
        }
    }

    public void dispenseItem(Item item) {
        if (this.getItemQuantity(item) != 0) {
            balance -= item.getPrice();
            inventory.put(item, inventory.get(item) - 1);
        }

        //condition for balance not being enough



        String flavorMessage = "";
        switch (item.getItemType()) {
            case "Chip":
                flavorMessage = "Crunch Crunch, Yum!";
                break;
            case "Candy":
                flavorMessage = "Munch Munch, Yum!";
                break;
            case "Drink":
                flavorMessage = "Glug Glug, Yum!";
                break;
            case "Gum":
                flavorMessage = "Chew Chew, Yum!";
                break;
        }
        //item name, cost, and the money remaining, plus the flavor text.
        System.out.println("\nNow dispensing: " + item.getItemName() + ".\n\nYou have been charged $" + item.getPrice() + ", and have $" + balance + " remaining.\n" + flavorMessage);

        //log
        try (PrintWriter log = new PrintWriter(new FileWriter("log.txt", true))) {
            String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
            log.println(formattedDate + item.getItemName() + " " + item.getItemSlot() + " $" + (balance + item.getPrice()) + " $" + balance);
        } catch (IOException e) {
            System.out.println("Caught an IOException. Message: " + e.getMessage());
        }
    }

    public void dispenseChange() {
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;

        //log
        try (PrintWriter log = new PrintWriter(new FileWriter("log.txt", true))) {
            String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
            log.println(formattedDate + "GIVE CHANGE: $" + balance + " $0.00");
        } catch (IOException e) {
            System.out.println("Caught an IOException. Message: " + e.getMessage());
        }


        while (balance >= 0.25) {
            numQuarters++;
            balance -= 0.25;
        }

        while (balance >= 0.10) {
            numDimes++;
            balance -= 0.1;
        }

        while (balance >= 0.5) {
            numNickels++;
            balance -= 0.05;
        }

        if (balance > 0) {   //**************This totally depends on the vendor... either customer gets nickel or they don't with any remaining balance.
            numNickels++;
        }

        System.out.println("Now dispensing change: " + numQuarters + " quarters, " + numDimes + " dimes, and " + numNickels + " nickels.");
        balance = 0;

    }
}
