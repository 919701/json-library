package ru.clevertec.model;

public record Owner(String firstName,String lastName, int age, Address address, Car[] car) {
}
