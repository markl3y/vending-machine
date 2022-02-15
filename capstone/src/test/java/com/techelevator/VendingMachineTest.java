package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineTest {

    @Test
    public void test_get_item() throws IOException {
        VendingMachine test = new VendingMachine(new File("vendingmachine.csv"));
        Item expected = new Item("A1", "Potato Crisps", new BigDecimal("3.05"), "Chips");
        String expectedString = expected.getItemName();

        Item actual = test.getItem("A1");
        String actualString = actual.getItemName();

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void test_get_item_quantity() throws IOException {
        VendingMachine test = new VendingMachine(new File("vendingmachine.csv"));
        Item testItem = test.getItem("A1");

        test.dispenseItem(testItem);

        int actual = test.getItemQuantity(testItem);

        Assert.assertEquals(4, actual);
    }


    @Test
    public void test_dispense_item() throws IOException {
        VendingMachine test = new VendingMachine(new File("vendingmachine.csv"));
        test.insertBill(new BigDecimal("3.05"));
        Item testItem = test.getItem("A1");
        String expected = "\nNow dispensing: Potato Crisps.\n\nYou have been charged $3.05, and have $0.00 remaining.\n\nCrunch Crunch, Yum!";
        String testDispense = test.dispenseItem(testItem);
        Assert.assertEquals(expected, testDispense);
    }
}
