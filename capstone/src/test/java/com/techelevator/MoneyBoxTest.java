package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class MoneyBoxTest {

    @Test
    public void makes_correct_change() throws IOException {
        MoneyBox test = new MoneyBox();
        test.addMoney(BigDecimal.valueOf(4));

        int[] expected = {16 ,0, 0};
        int[] testChange = test.makeChange();

        Assert.assertEquals(expected, testChange);
    }

    @Test
    public void makes_change_of_four_dollars_one_cent() throws IOException {
        MoneyBox test = new MoneyBox();
        test.addMoney(BigDecimal.valueOf(4.01));

        int[] expected = {16, 0, 0};
        int[] testChange = test.makeChange();

        Assert.assertEquals(expected, testChange);
    }
}
