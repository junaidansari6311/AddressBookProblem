package com.addressbook.utility;

import com.addressbook.model.Person;

import java.util.Scanner;

public class Utility {
    static Scanner scanner = new Scanner(System.in);

    public static int integerInput() {
        return scanner.nextInt();
    }

    public static String stringInput() {
        return scanner.next();
    }

    public static long longInput() {
        return scanner.nextLong();
    }

    public static Person getPersonDetails(Person person){
        System.out.println("Enter address : ");
        String address = Utility.stringInput();
        person.setAddress(address);

        System.out.println("Enter city : ");
        String city = Utility.stringInput();
        person.setCity(city);

        System.out.println("Enter state : ");
        String state = Utility.stringInput();
        person.setState(state);

        System.out.println("Enter zip : ");
        int zip = Utility.integerInput();
        person.setZip(zip);

        System.out.println("Enter phone number : ");
        long phoneNo = Utility.longInput();
        person.setPhoneNumber(phoneNo);

        return person;
    }
}
