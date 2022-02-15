package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineTest {

    @Test
    public void test_dispense_message() throws IOException {
        VendingMachine test = new VendingMachine(new File("vendingmachine.csv"));
        Item testItem = new Item("A1", "Potato Crisps", new BigDecimal(3.05), "Chips");
        String expected = "Crunch Crunch, Yum!";
        String testDispense = test.dispenseItem(testItem);

        Assert.assertEquals(expected, testDispense);

    }

    @Test
    public void get_correct_item_quantity() throws IOException {
        VendingMachine test = new VendingMachine(new File("vendingmachine.csv"));
        Item testItem = new Item("A1", "Potato Crisps", new BigDecimal(3.05), "Chips");
        Map<Item, Integer> testInventory = new HashMap<>();
        testInventory.put(testItem, 5);

        int useMethod = test.getItemQuantity(testItem);
        Assert.assertEquals(testInventory, useMethod);

    }
}
