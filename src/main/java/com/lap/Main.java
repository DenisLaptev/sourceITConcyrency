package com.lap;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Lenovo on 04.04.2017.
 */
public class Main {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Person1", SECONDS.toMillis(1)));
        persons.add(new Person("Person2", SECONDS.toMillis(1)));
        persons.add(new Person("Person3", SECONDS.toMillis(2)));
        persons.add(new Person("Person4", SECONDS.toMillis(2)));
        persons.add(new Person("Person5", SECONDS.toMillis(2)));


        Thread manager = new Manager(Basket.getInstance());
        manager.start();


        for (Person person : persons) {
            person.start();
        }

    }
}

