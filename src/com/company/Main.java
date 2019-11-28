package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        String str = "aaa";
        XmlSerializer xmlSerializer = new XmlSerializer();
        Person person = new Person();
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add(2);
        Serializer serializer = new XmlSerializer();
        System.out.println(serializer.serialize(person));
        Map map = Serializer.getAllFieldsByMap(person);
        int a = 123;
        System.out.println(serializer.serialize(a));
    }

    static class Person {
        private final String firstName;
        private final Address address;
        private final List<String> phoneNumbers;

        Person() {
            firstName = "Bob";
            address = new Address();
            phoneNumbers = new ArrayList<>();
            phoneNumbers.add("8988");
            phoneNumbers.add("8999");

        }
    }

    static class Address {
        private final String city;
        private final String postalCode;

        Address() {
            city = "Moscow";
            postalCode = "12345";
        }
    }
}
