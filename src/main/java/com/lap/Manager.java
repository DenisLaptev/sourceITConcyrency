package com.lap;

import static com.lap.Utils.getNameOfTheMaxTransactionSumPerson;
import static com.lap.Utils.round;

/**
 * Created by Lenovo on 04.04.2017.
 */
public class Manager extends Thread {

    private static final long TIME = 2 * 1000;

    double usd;
    double eur;
    double uah;

    public Manager(Basket instance) {
        this.usd = instance.getUsd();
        this.eur = instance.getEur();
        this.uah = instance.getUah();
    }

    @Override
    public void run() {

        while (Basket.getInstance().isActive()) {
            System.out.println("Manager: " + calcIncome() + " uah");
            System.out.println("Manager: max tramsaction - " + getNameOfTheMaxTransactionSumPerson());

            try {
                Thread.sleep(TIME);
            } catch (InterruptedException e) {
                //DO NOTHING
            }
        }

        System.out.println("Manager: total " + calcIncome() + " uah");
        System.out.println("Manager: max tramsaction - " + getNameOfTheMaxTransactionSumPerson());
    }

    private double calcIncome() {

        double rentUAH = Basket.getInstance().getUah() - uah;
        rentUAH += (Basket.getInstance().getUsd() - usd) * Basket.SELL_USD
                + (Basket.getInstance().getEur() - eur) * Basket.SELL_EUR;
        return round(rentUAH, 2);

    }
}

