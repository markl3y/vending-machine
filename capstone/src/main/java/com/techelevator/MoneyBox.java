package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class MoneyBox {

    //Instance vars.
    private BigDecimal balance = BigDecimal.valueOf(0);
    Logger log = new Logger();

    //Constructor takes no args.
    public MoneyBox() throws IOException {}

    //Get
    public BigDecimal getBalance() {
        return balance;
    }

    //Methods
    public void addMoney(BigDecimal bill) {
        balance = balance.add(bill);
        log.logAddMoney(bill, balance);
    }

    public void makePurchase(Item item) {
        balance = balance.subtract(item.getPrice());
        log.logPurchase(item, balance);
    }

    public int[] makeChange() {
        log.logDispenseChange(balance);

        int[] coins = new int[3];
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;

        while (balance.compareTo(BigDecimal.valueOf(0.25)) >= 0) {
            numQuarters++;
            balance = balance.subtract(BigDecimal.valueOf(0.25));
        }

        while (balance.compareTo(BigDecimal.valueOf(0.10)) >= 0) {
            numDimes++;
            balance = balance.subtract(BigDecimal.valueOf(0.10));
        }

        while (balance.compareTo(BigDecimal.valueOf(0.05)) >= 0) {
            numNickels++;
            balance = balance.subtract(BigDecimal.valueOf(0.05));
        }

        coins[0] = numQuarters;
        coins[1] = numDimes;
        coins[2] = numNickels;

        //Zero out balance.
        balance = BigDecimal.valueOf(0);

        return coins;
    }

    public void logSales(Map<Item, Integer> inventory) {
        log.logSales(inventory);
    }
}
