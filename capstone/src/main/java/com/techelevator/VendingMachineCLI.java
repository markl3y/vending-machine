package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		//VendingMachine vendingMachine = new VendingMachine(new File("vendingmachine.csv"));
		while (true) {
			int balance = 0;

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				//File will likely change to reflex new inventory amount
				//TODO 1 - Simply this block, it is repeated
				File startingFile = new File("vendingmachine.csv");
				try {
					Scanner readItems = new Scanner(startingFile);
					while (readItems.hasNextLine()) {
						String line = readItems.nextLine();
						System.out.println(line);
					}
				} catch (FileNotFoundException e) {
					System.out.println("File could not be found");
				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					System.out.println("Current Money Provided: $" + ""/*balance*/);
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						//Feed bills to the machine.
						//TODO 2 create method, this is lengthy
						System.out.println("Please select the amount of money to feed: ");
						System.out.println("$1");
						System.out.println("$2");
						System.out.println("$5");
						System.out.println("$10");
						Scanner readAmount = new Scanner(System.in);
						String getAmount = readAmount.nextLine();
						if (getAmount.equals("$1")) {
							balance += 1;
						}
						else if (getAmount.equals("$2")) {
							balance += 2;
						}
						else if (getAmount.equals("$5")) {
							balance += 5;
						}
						else if (getAmount.equals("$10")) {
							balance += 10;
						}

					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

						System.out.println("Please select an item slot");
						//TODO 1
						File startingFile = new File("vendingmachine.csv");
						try {
							Scanner readItems = new Scanner(startingFile);
							while (readItems.hasNextLine()) {
								String line = readItems.nextLine();
								System.out.println(line);
							}
						} catch (FileNotFoundException e) {
							System.out.println("File could not be found");
						}

						//TODO 4 Do not allow dispense if money is not fed
						if (balance == 0) {
							System.out.println("Please feed money before continuing");
						}


						//TODO 3 Choose and dispense item
						Scanner slotInput = new Scanner(System.in);
						String slotChoice = slotInput.nextLine();
						switch (slotChoice) {
							case A1:
								System.out.println("You chose"/* + item  */);
								//dispense item with sound
								break;
							case A2:
								System.out.println("You chose"/* + item  */);
								//dispense item with sound
								break;
							case A3:
								System.out.println("You chose"/* + item  */);
								//dispense item with sound
								break;
						}
						//print list of choices and associated product codes.
						//accept input
							//if code does not exist, tell user it dne and break;
							//if code exists but product is sold out, tell user its sold out and break;
							//If a valid product is selected, it is dispensed to the customer.
								//Dispensing an item prints the item name, cost, and the money remaining.
								//Dispensing also returns a message based on product.
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						//If balance == 0, and change is dispensed, break;
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
