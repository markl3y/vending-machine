package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


public class Logger {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a ");
    DateTimeFormatter fileNameTimeFormatter = DateTimeFormatter.ofPattern("MM_dd_yyyy_hh_mm_ss_a_");

    public Logger() {
    }

    public void logAddMoney(BigDecimal billAmount, BigDecimal balance) {
        try (PrintWriter log = new PrintWriter(new FileWriter("log.txt", true))) {
            String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
            log.println(formattedDate + "FEED MONEY: " + NumberFormat.getCurrencyInstance().format((balance.subtract(billAmount))) + " " + NumberFormat.getCurrencyInstance().format(balance));
        } catch (IOException e) {
            System.out.println("\nLog file could not be created.");
            System.exit(1);
        }
    }

    public void logPurchase(Item item, BigDecimal balance) {
        try (PrintWriter log = new PrintWriter(new FileWriter("log.txt", true))) {
            String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
            log.println(formattedDate + item.getItemName() + " " + item.getItemSlot() + " " + NumberFormat.getCurrencyInstance().format(balance.add(item.getPrice())) + " " + NumberFormat.getCurrencyInstance().format(balance));
        } catch (IOException e) {
            System.out.println("\nLog file could not be created.");
            System.exit(1);
        }
    }

    public void logDispenseChange(BigDecimal balance) {
        try (PrintWriter log = new PrintWriter(new FileWriter("log.txt", true))) {
            String formattedDate = dateTimeFormatter.format(LocalDateTime.now());
            log.println(formattedDate + "GIVE CHANGE: " + NumberFormat.getCurrencyInstance().format(balance) + " $0.00");
        } catch (IOException e) {
            System.out.println("\nLog file could not be created.");
            System.exit(1);
        }
    }

    public void logSales(Map<Item, Integer> inventory) {
        try {
            String formattedDate = fileNameTimeFormatter.format(LocalDateTime.now());
            String fileName = formattedDate + "Sales_Report.txt";
            PrintWriter salesReport = new PrintWriter(new FileWriter(fileName),true);

            BigDecimal totalSales = BigDecimal.valueOf(0);
            for (Item key : inventory.keySet()) {
                int numSold = 5 - inventory.get(key);
                if (numSold != 0) {
                    salesReport.println(key.getItemName() + "|" + numSold);
                }
                totalSales = totalSales.add(key.getPrice().multiply(BigDecimal.valueOf(numSold)));
            }
            salesReport.println("\nTotal Sales: " + NumberFormat.getCurrencyInstance().format(totalSales));

        } catch (IOException e) {
            System.out.println("\nSales log file could not be created.");
            System.exit(1);
        }
    }
}
