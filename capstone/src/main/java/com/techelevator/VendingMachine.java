package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {
    //Instance Vars...
    private Map<Item, Integer> inventory = new HashMap<>();
    private List<Item> items = new ArrayList<>();
    private double balance;

    //Constructor
    public VendingMachine(File inputFile) throws FileNotFoundException {
        Scanner fileInput = new Scanner(inputFile);
        String line = fileInput.nextLine();
        while (fileInput.hasNextLine()) {
            if (line.contains("Chip")) {
                items.add(new ChipItem(line));
            }
        }
                //For each extract line,
                    //items.add(new Item());
        //    //create an Item from that line, add it to an Item list.
        for (Item item : items) {
            inventory.put(item, 5);
        }
        //    //For each item in items list, create an entry in inventory of item to quantity of item (starts with 5)
        this.balance = 0;
    }


    //Methods
    //.addMoney
    //.purchase
    //.tender

    
}
