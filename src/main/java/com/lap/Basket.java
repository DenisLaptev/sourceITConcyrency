package com.lap;

import static com.lap.Utils.*;

/**
 * Created by Lenovo on 04.04.2017.
 */
public class Basket {
    //корзина (Basket) продаёт USD по-дороже, а покупает USD по-дешевле.
    public static final double SELL_USD = 27.2d;
    private static final double BUY_USD = 27d;

    public static final double SELL_EUR = 29.2d;
    private static final double BUY_EUR = 29d;

    //начальное количество USD и UAH в корзине (Basket).
    private double usd = 10_000;
    private double eur = 8_000;
    private double uah = 500_000;


    private boolean active = true;
    //число операций, после которого программа завершает работу.
    private int totalOperation;

    //pattern singleton
    private static Basket instance;

    public static synchronized Basket getInstance() {
        if (instance == null) {
            instance = new Basket();
        }
        return instance;
    }

    private Basket() {
    }
//pattern singleton


    //человек покупает USD у корзины.
    public synchronized boolean buyUSD(String personName, double uah) {

        //Если active=false или совершили 30 операций, то возвращаем false,
        //и метод run() класса Person завершает работу.
        if (!active || totalOperation > 30) {
            active = false;
            return false;
        }

        double tempUsd = round(uah / SELL_USD, 2);

        System.out.println("uah - " + uah + "(" + getTotal() + ")");
        System.out.println(personName +
                " has bought " + tempUsd + " USD. Transaction sum= " + uah + " UAH");
        if (maxTransactionSum < uah) {
            maxTransactionSum = uah;
            nameOfTheMaxTransactionSumPerson = personName;
        }

        if (tempUsd < usd) {
            this.uah += uah;
            usd -= tempUsd;
            totalOperation++;
            return true;
            //Если долларов не хватает, корзина становится неактивной.
        } else {
            active = false;
            return false;
        }

    }


    //человек покупает EUR у корзины.
    public synchronized boolean buyEUR(String personName, double uah) {

        //Если active=false или совершили 30 операций, то возвращаем false,
        //и метод run() класса Person завершает работу.
        if (!active || totalOperation > 30) {
            active = false;
            return false;
        }

        double tempEur = round(uah / SELL_EUR, 2);

        System.out.println("uah - " + uah + "(" + getTotal() + ")");
        System.out.println(personName +
                " has bought " + tempEur + " EUR. Transaction sum= " + uah + " UAH");
        if (maxTransactionSum < uah) {
            maxTransactionSum = uah;
            nameOfTheMaxTransactionSumPerson = personName;
        }

        if (tempEur < eur) {
            this.uah += uah;
            eur -= tempEur;
            totalOperation++;
            return true;
            //Если евро не хватает, корзина становится неактивной.
        } else {
            active = false;
            return false;
        }

    }


    //человек продаёт USD корзине.
    public synchronized boolean sellUSD(String personName, double usd) {

        if (!active || totalOperation > 30) {
            active = false;
            return false;
        }

        double tempUah = round(usd * BUY_USD, 2);

        System.out.println("usd - " + usd + "(" + getTotal() + ")");
        System.out.println(personName +
                " has sold " + usd + " USD. Transaction sum= " + tempUah + " UAH");

        if (maxTransactionSum < tempUah) {
            maxTransactionSum = tempUah;
            nameOfTheMaxTransactionSumPerson = personName;
        }

        if (tempUah < uah) {
            uah -= tempUah;
            this.usd += usd;
            totalOperation++;
            return true;
        } else {
            active = false;
            return false;
        }

    }

    //человек продаёт EUR корзине.
    public synchronized boolean sellEUR(String personName, double eur) {

        if (!active || totalOperation > 30) {
            active = false;
            return false;
        }

        double tempUah = round(eur * BUY_EUR, 2);

        System.out.println("eur - " + eur + "(" + getTotal() + ")");
        System.out.println(personName +
                " has sold " + eur + " EUR. Transaction sum= " + tempUah + " UAH");

        if (maxTransactionSum < tempUah) {
            maxTransactionSum = tempUah;
            nameOfTheMaxTransactionSumPerson = personName;
        }

        if (tempUah < uah) {
            uah -= tempUah;
            this.eur += eur;
            totalOperation++;
            return true;
        } else {
            active = false;
            return false;
        }

    }


    //Методы возвращают количество USD, EUR и UAH в корзине (Basket).
    public double getUsd() {
        return usd;
    }

    public double getEur() {
        return eur;
    }

    public double getUah() {
        return uah;
    }

    public boolean isActive() {
        return active;
    }

    //Метод возвращает число выполненных операций (totalOperation).
    //Когда totalOperation=30, программа завершает работу.
    public int getTotal() {
        return totalOperation;
    }
}

