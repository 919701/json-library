package ru.clevertec;


import ru.clevertec.util.FileHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println(FileHandler.read("src/main/resources/test.json"));
    }
}