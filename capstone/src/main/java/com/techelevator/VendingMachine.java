package com.techelevator;

import java.io.File;
import java.util.*;

public class VendingMachine {
    //Instance Vars...
    private Map<Item, Integer> inventory = new HashMap<>();
    private List<Item> items = new ArrayList<>();
    private double balance;

    //Constructor
    VendingMachine(File inputFile) {
        Scanner fileInput = new Scanner(inputFile)
                while (.hasNextLine())
                //For each extract line,
                    items.add(new Item(sdasdasdasd));
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
