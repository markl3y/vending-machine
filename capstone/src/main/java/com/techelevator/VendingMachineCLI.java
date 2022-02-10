package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private static final String FEED_MENU_OPTION_ONE_DOLLAR = "$1.00";
    private static final String FEED_MENU_OPTION_TWO_DOLLARS = "$2.00";
    private static final String FEED_MENU_OPTION_FIVE_DOLLARS = "$5.00";
    private static final String FEED_MENU_OPTION_TEN_DOLLARS = "$10.00";
    private static final String[] FEED_MENU_OPTIONS = {FEED_MENU_OPTION_ONE_DOLLAR, FEED_MENU_OPTION_TWO_DOLLARS, FEED_MENU_OPTION_FIVE_DOLLARS, FEED_MENU_OPTION_TEN_DOLLARS};

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        Scanner userInput = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine(new File("vendingmachine.csv"));
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                vendingMachine.printCurrent();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                while (true) {
                    System.out.println("\nCurrent Money Provided: $" + vendingMachine.getBalance());
                    choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        choice = (String) menu.getChoiceFromOptions(FEED_MENU_OPTIONS);
                        switch (choice) {
                            case "$1.00":
                                vendingMachine.insertBill(1);
                                break;
                            case "$2.00":
                                vendingMachine.insertBill(2);
                                break;
                            case "$5.00":
                                vendingMachine.insertBill(5);
                                break;
                            case "$10.00":
                                vendingMachine.insertBill(10);
                                break;
                        }
                    } else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        while (true) {
                            //If there's nothing in the machine, ask the user for money and break loop.
                            if (vendingMachine.getBalance() == 0) {
                                System.out.println("\nPlease feed money before continuing.\n");
                                break;
                            }

                            //If it makes it past that, enter a code.
                            vendingMachine.printCurrent();
                            System.out.println("\nPlease enter the selection code (Ex. A1): ");
                            String itemChoice = userInput.nextLine();
                            Item chosenItem = vendingMachine.getItem(itemChoice); //Pass the item code to the find Item method, store it in new item.
                            //If code not found, return to sale menu.
                            if (chosenItem == null) {
                                System.out.println("Code not found.");
                                break;
                            }
                            //If it's sold out, back to sale menu.
                            if (vendingMachine.getItemQuantity(chosenItem) == 0) {
                                System.out.println("Oops! Sold out!");
                                break;
                            }

                            //Otherwise dispense item.
                            else {
                                vendingMachine.dispenseItem(chosenItem);
                            }
                            break;
                        }
                    } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                        //If balance == 0, and change is dispensed, break;
                        if (vendingMachine.getBalance() != 0) {
                            vendingMachine.dispenseChange();
                        }
                        break;
                    }
                }
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.exit(0); //Exit ends program.
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
