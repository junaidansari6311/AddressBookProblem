package com.addressbook;

import com.addressbook.model.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {

    Scanner sc  = new Scanner(System.in);
    static ArrayList<Person> personList = new ArrayList<Person>();
    Person person = new Person();

    public void addPerson(){
        System.out.println("Enter first name");
        String fName = sc.next();
        person.setFirstName(fName);

        System.out.println("Enter last name");
        String lName = sc.next();
        person.setLastName(lName);

        System.out.println("Enter address");
        String address = sc.next();
        person.setAddress(address);

        System.out.println("Enter city");
        String city = sc.next();
        person.setCity(city);

        System.out.println("Enter state");
        String state = sc.next();
        person.setState(state);

        System.out.println("Enter zip");
        int zip = sc.nextInt();
        person.setZip(zip);

        System.out.println("Enter phone number");
        long phoneNo = sc.nextLong();
        person.setPhoneNumber(phoneNo);
        personList.add(person);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        AddressBookMain addressBookMain = new AddressBookMain();
        addressBookMain.addPerson();
    }
}
