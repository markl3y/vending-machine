package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoggerTest {

    @Test
    public void test_logger_add_money() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a ");
        String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
        Logger test = new Logger();
        test.logAddMoney(new BigDecimal(2), new BigDecimal(0));
        Scanner testRead = new Scanner("log.txt");
        boolean actual = false;

        while (testRead.hasNextLine()) {
            if (testRead.nextLine().equals(formattedDate + " FEED MONEY: $0.00 $2.00")) {
                actual = true;
            }
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void test_log_purchase() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a ");
        String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
        Logger test = new Logger();
        Item item = new Item("A1", "Potato Crisps", BigDecimal.valueOf(3.05), "Chip");
        test.logPurchase(item, new BigDecimal(5));
        Scanner testRead = new Scanner("log.txt");
        boolean actual = false;
            while (testRead.hasNextLine()) {
                if (testRead.nextLine().equals(formattedDate + " Potato Crisps A1 $8.05 $5.00")) {
                    actual = true;
                }
            }
            Assert.assertTrue(actual);
        }

}
