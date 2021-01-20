package com;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alexandr", "Humeniuk", 20, "Ukraine",
                "email@gmail.com", 12345);
        String str = "";
        try{
            str = MySerializer.serialize(person);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("========================================");
        System.out.println("SERIALIZED OBJECT (CONTENT OF FILE WITH SERIALIZED FIELDS)");
        System.out.println("========================================");
        System.out.println(str);
        try{
            Person deserialized = MySerializer.deserialize(str, Person.class);
            System.out.println("========================================");
            System.out.println("DESERIALIZED OBJECT : ");
            System.out.println("========================================");
            System.out.println(deserialized.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
