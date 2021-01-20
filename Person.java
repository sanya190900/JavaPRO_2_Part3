package com;

public class Person {
    @Save
    private String name;
    @Save
    private String surname;
    @Save
    private int age;
    @Save
    private String country;
    @Save
    private String email;
    @Save
    private int phoneNumber;

    public Person() {
    }

    public Person(String name, String surname, int age, String country, String email, int phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  "name=" + name + "\n" +
                "surname=" + surname + "\n" +
                "age=" + age + "\n" +
                "country=" + country + "\n" +
                "email=" + email + "\n" +
                "phoneNumber=" + phoneNumber;
    }
}
