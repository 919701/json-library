package ru.clevertec;

import ru.clevertec.model.Car;
import ru.clevertec.util.Json;
import ru.clevertec.util.Json.*;
import ru.clevertec.util.tojson.ObjectToJson;
import ru.clevertec.util.tojson.ObjectToJson.*;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Hello JSON");

        Car car = new Car("Ford", "Mustang", 2009, "Goldenrod");

        System.out.println("-".repeat(60));
        System.out.println(Json.objectToJson(car, 0));
        System.out.println("-".repeat(60));
     /*   System.out.println(objectToJson(company,0));
        System.out.println("-".repeat(60));
        System.out.println(objectToJson(person,0));
       */
        System.out.println("-".repeat(60));

    }
}