package com.lap;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Lenovo on 04.04.2017.
 */
public final class Utils {

    public static double maxTransactionSum = 0;
    public static String nameOfTheMaxTransactionSumPerson = null;

    private Utils() {
    }

    //Метод округляет вещественные числа.
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String getNameOfTheMaxTransactionSumPerson() {
        synchronized (Main.class) {

            return nameOfTheMaxTransactionSumPerson;
        }
    }

}
