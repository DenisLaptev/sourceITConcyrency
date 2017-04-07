package com.lap;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lenovo on 04.04.2017.
 */
public class Person extends Thread {
    //Каждый человек (отдельный поток)
    //раз в определенное количество времени
    //(для каждого потока оно разное, задать константой)
    //будет покупать или продавать случайное количество валюты.
    String name;
    long time;

    public Person(String name, long time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public void run() {
        boolean result;
        do {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                //DO NOTHING
            }
            //Случайным образом решаем, что делает человек:
            //USD или EUR
            //покупает валюту или продаёт валюту.
            if (new Random().nextBoolean()) {
                //USD
                if (new Random().nextBoolean()) {
                    double uah = ThreadLocalRandom.current().nextInt(1000, 100_000);
                    //корзина (Basket) это синглтон.
                    result = Basket.getInstance().buyUSD(name, uah);
                } else {
                    double usd = ThreadLocalRandom.current().nextInt(100, 5_000);
                    result = Basket.getInstance().sellUSD(name, usd);
                }
            } else {
                //EUR
                if (new Random().nextBoolean()) {
                    double uah = ThreadLocalRandom.current().nextInt(1000, 100_000);
                    //корзина (Basket) это синглтон.
                    result = Basket.getInstance().buyEUR(name, uah);
                } else {
                    double eur = ThreadLocalRandom.current().nextInt(100, 5_000);
                    result = Basket.getInstance().sellEUR(name, eur);
                }
            }


            //Если active=false или совершили 30 операций, то возвращаем false,
            //и метод run() класса Person завершает работу.
        } while (result);
    }
}
