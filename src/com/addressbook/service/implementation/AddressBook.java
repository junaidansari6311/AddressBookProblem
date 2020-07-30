package com.addressbook.service.implementation;

import com.addressbook.exception.AddressBookException;
import com.addressbook.model.Person;
import com.addressbook.service.IAddressBook;

import java.util.*;
import java.util.stream.IntStream;

public class AddressBook implements IAddressBook {

    Scanner sc  = new Scanner(System.in);
    public static ArrayList<Person> personList = new ArrayList<Person>();
    Person person = new Person();

    @Override
    public void addPerson() {
        System.out.println("Enter first name : ");
        String fName = sc.next();

        int index = IntStream.range(0, personList.size())
                .filter(i -> fName.equals(personList.get(i).getFirstName()))
                .findFirst().orElse(-1);
        if(index==0) {
            throw new AddressBookException("Duplicate entry is not allowed");
        }
        person.setFirstName(fName);
        System.out.println("Enter last name :");
        String lName = sc.next();
        person.setLastName(lName);

        System.out.println("Enter address : ");
        String address = sc.next();
        person.setAddress(address);

        System.out.println("Enter city : ");
        String city = sc.next();
        person.setCity(city);

        System.out.println("Enter state : ");
        String state = sc.next();
        person.setState(state);

        System.out.println("Enter zip : ");
        int zip = sc.nextInt();
        person.setZip(zip);

        System.out.println("Enter phone number : ");
        long phoneNo = sc.nextLong();
        person.setPhoneNumber(phoneNo);
        personList.add(person);
        System.out.println("Person details are being added...");
    }

    @Override
    public void editPerson() {
        if(personList.size() == 0){
            throw new AddressBookException("No records found");
        }
        System.out.println("Enter person name to edit :");
        String name = sc.next();
        int index = IntStream.range(0, personList.size())
                .filter(i -> name.equals(personList.get(i).getFirstName()))
                .findFirst().orElseThrow(() -> new AddressBookException("Person not found - " + name));
        if(index==0){
            System.out.println("Enter address : ");
            String address = sc.next();
            person.setAddress(address);

            System.out.println("Enter city : ");
            String city = sc.next();
            person.setCity(city);

            System.out.println("Enter state : ");
            String state = sc.next();
            person.setState(state);

            System.out.println("Enter zip : ");
            int zip = sc.nextInt();
            person.setZip(zip);

            System.out.println("Enter phone number : ");
            long phoneNo = sc.nextLong();
            person.setPhoneNumber(phoneNo);
            personList.add(person);
        }
    }

    @Override
    public void deletePerson() {
        if(personList.size() == 0){
            throw new AddressBookException("No records found");
        }
        System.out.println("Enter person name to delete a record :");
        String name = sc.next();
        int index = IntStream.range(0, personList.size())
                .filter(i -> name.equals(personList.get(i).getFirstName()))
                .findFirst().orElseThrow(() -> new AddressBookException("Person not found - " + name));
        personList.remove(index);
        System.out.println("Record Deleted...");
    }

    @Override
    public void sortByName() {
        if (personList.size()==0){
            throw new AddressBookException("No records found to sort");
        }
        Collections.sort(personList,(person1,person2) -> person1.getFirstName().compareTo(person2.getFirstName()));
    }

    @Override
    public void sortByCity() {
        if (personList.size()==0){
            throw new AddressBookException("No records found to sort");
        }
        Collections.sort(personList,(person1,person2) -> person1.getCity().compareTo(person2.getCity()));
    }

    @Override
    public void sortByState() {
        if (personList.size() == 0) {
            throw new AddressBookException("No records found to sort");
        }
        Collections.sort(personList, (person1, person2) -> person1.getState().compareTo(person2.getState()));
    }

    @Override
    public void sortByZip() {
        if (personList.size()==0){
            throw new AddressBookException("No records found to sort");
        }
        Collections.sort(personList,(person1,person2) -> String.valueOf(person1.getZip()).compareTo(String.valueOf(person2.getZip())));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook that = (AddressBook) o;
        return Objects.equals(person, that.person);
    }
}
