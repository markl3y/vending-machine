package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Scanner;

public class LoggerTest {

    @Test
    public void test_logger_add_money() {
        Logger test = new Logger();

        test.logAddMoney(new BigDecimal(2),new BigDecimal(0));
        Scanner testRead = new Scanner("log.txt");

        String expected = test.dateTimeFormatter + "FEED MONEY: $0.00 $2.00";

        Assert.assertEquals(expected, testRead);
    }
}
