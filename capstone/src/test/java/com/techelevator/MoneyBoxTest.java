package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class MoneyBoxTest {

    @Test
    public void test_add_money() throws IOException {
        MoneyBox test = new MoneyBox();
        test.addMoney(BigDecimal.valueOf(4));
        BigDecimal actual = test.getBalance();
        Assert.assertEquals(actual, BigDecimal.valueOf(4));
    }

    @Test
    public void test_make_change() throws IOException {
        MoneyBox test = new MoneyBox();
        test.addMoney(BigDecimal.valueOf(4.05));

        int[] expected = {16, 0, 1};
        int[] testChange = test.makeChange();

        String expectedString = "";
        String actualString = "";

        for (int i = 0; i < expected.length; i++) {
            expectedString += expected[i];
            actualString += testChange[i];
        }

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void test_make_purchase() throws IOException {
        MoneyBox test = new MoneyBox();
        Item testItem = new Item("A1", "Potato Crisps", BigDecimal.valueOf(3.05), "Chips");
        test.addMoney(BigDecimal.valueOf(10));
        test.makePurchase(testItem);
        BigDecimal actual = test.getBalance();

        Assert.assertEquals(BigDecimal.valueOf(6.95), actual);
    }
}
