package ru.clevertec;

import ru.clevertec.model.Address;
import ru.clevertec.model.Car;
import ru.clevertec.model.Owner;
import ru.clevertec.util.tojson.ObjectToJson;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Hello JSON");

        Car car1 = new Car("Ford", "Mustang", 2009, "Goldenrod");
        Car car2 = new Car("Mazda", "626", 1995, "Orange");
        Car car3 = new Car("Jaguar", "XK Series", 1999, "Goldenrod");

        Address address1 = new Address("Moscow", "Lenina", 18);
        Address address2 = new Address("Minsk", "Mira", 35);
        Address address3 = new Address("Vladivostok", "Komiterna", 85);

        Owner owner = new Owner("Ivan", "Ivanov", 20, address1, car1);

        var element = ObjectToJson.objectToJson(owner, 0);
        System.out.println("-".repeat(60));
        System.out.println(element);
        System.out.println("-".repeat(60));

        System.out.println("-".repeat(60));

        StringReader writer = new StringReader(element);
        System.out.println(writer.toString());

    }
}