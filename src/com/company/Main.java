package com.company;

import java.util.ArrayList;
import java.util.List;


public class Main {
    static class CallBack implements Runnable {


    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person();
        Serializer serializer = new JsonSerializer();
        System.out.println(serializer.serialize(person));
        serializer = new XmlSerializer();
        System.out.println("\n \n");
        System.out.println(serializer.serialize(person));
    }

    static class Person {
        private final String firstName;
        private final Address address;
        private final List<String> phoneNumbers;
        private final Integer[] money;

        Person() {
            firstName = "Bob";
            address = new Address();
            phoneNumbers = new ArrayList<>();
            phoneNumbers.add("8988");
            phoneNumbers.add("8999");
            money = new Integer[]{1, 2, 3};

        }
    }

    static class Address {
        private final String city;
        private final String postalCode;

        Address() {
            city = "Moscow";
            postalCode = "12345";

        @Override
        public void run() {
            System.out.println("This is callback");

        }
    }

    //Тестирование
    public static void main(String[] args) throws InterruptedException {
        CallBack callBack = new CallBack();
        Execute[] tasks = new Execute[]{new Execute("1"), new Execute("2"), new Execute("3")
                , new Execute("4"), new Execute("5"), new Execute("error")};
        ExecutionManager executionManager = new ExecutionManagerClass();
        Context context = executionManager.execute(tasks);
        context.onFinish(callBack);
        Thread thread = Thread.currentThread();
        thread.sleep(1000);
        context.interrupt();
        context.awaitTermination();
        System.out.println(context.getCompletedTaskCount());
        System.out.println(context.getFailedTaskCount());
        ExecutionStatistics statistic = context.getStatistics();

    }

}
