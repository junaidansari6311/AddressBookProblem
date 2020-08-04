package com.addressbook.service.implementation;

import com.addressbook.exception.AddressBookException;
import com.addressbook.model.Person;
import com.addressbook.service.IAddressBook;

import java.util.*;
import java.util.stream.IntStream;

import com.addressbook.utility.Utility;

public class AddressBook implements IAddressBook {

    public static ArrayList<Person> personList = new ArrayList<>();
    Person person = new Person();
    JdbcOperations operations = new JdbcOperations();
    JsonFileOperations jsonFileOperations = new JsonFileOperations();


    @Override
    public void addPerson() {
        System.out.println("Enter first name : ");
        String fName = Utility.stringInput();
        if(!Utility.inputValidation(fName, Utility.NAME_PATTERN))
            throw new AddressBookException("Please enter valid Last Name");

        int index =
                IntStream.range(0, personList.size())
                .filter(i -> fName.equals(personList.get(i).getFirstName()))
                .findFirst().orElse(-1);
        if(index==0) {
            throw new AddressBookException("Duplicate entry is not allowed");
        }
        person.setFirstName(fName);
        System.out.println("Enter last name :");
        String lName = Utility.stringInput();
        if(!Utility.inputValidation(lName, Utility.NAME_PATTERN))
            throw new AddressBookException("Please enter valid Last Name");
        person.setLastName(lName);

        Person personDetails = Utility.getPersonDetails(person);
        personList.add(personDetails);
        jsonFileOperations.writeToJsonFile(personList);
        operations.writeData(personList);
        System.out.println("Person details are being added...");
    }

    @Override
    public void editPerson() {
        if(personList.size() == 0){
            throw new AddressBookException("No records found");
        }
        jsonFileOperations.readFromJsonFile();
        operations.readData();
        System.out.println("Enter person name to edit :");
        String name = Utility.stringInput();

        int index = IntStream.range(0, personList.size())
                .filter(i -> name.equals(personList.get(i).getFirstName()))
                .findFirst().orElseThrow(() -> new AddressBookException("Person not found - " + name));
        if(index==0){
            Person personDetails = Utility.getPersonDetails(person);
            personList.add(personDetails);
            jsonFileOperations.writeToJsonFile(personList);
            operations.writeData(personList);
        }
    }

    @Override
    public void deletePerson() {
        if(personList.size() == 0){
            throw new AddressBookException("No records found");
        }
        jsonFileOperations.readFromJsonFile();
        System.out.println("Enter person name to delete a record :");
        String name = Utility.stringInput();
        int index = IntStream.range(0, personList.size())
                .filter(i -> name.equals(personList.get(i).getFirstName()))
                .findFirst().orElseThrow(() -> new AddressBookException("Person not found - " + name));
        personList.remove(index);
        System.out.println("Record Deleted...");
    }

    public void sortByField(String field){
        if (personList.size()==0){
            throw new AddressBookException("No records found to sort");
        }
        switch(field){
            case "name" :
                Collections.sort(personList,(person1,person2) -> person1.getFirstName().compareTo(person2.getFirstName()));
                break;
            case "city" :
                Collections.sort(personList,(person1,person2) -> person1.getCity().compareTo(person2.getCity()));
                break;
            case "state" :
                Collections.sort(personList, (person1, person2) -> person1.getState().compareTo(person2.getState()));
                break;
            case "zip" :
                Collections.sort(personList,(person1,person2) -> String.valueOf(person1.getZip()).compareTo(String.valueOf(person2.getZip())));
                break;
            default:
                System.out.println("You have entered invalid field name");
                break;
        }
    }

    @Override
    public void viewByCityAndState() {
        System.out.println("Enter city:");
        String viewCity = Utility.stringInput();
        if(!Utility.inputValidation(viewCity, Utility.NAME_PATTERN))
            throw new AddressBookException("Please enter valid City");

        System.out.println("Enter state:");
        String viewState = Utility.stringInput();
        if(!Utility.inputValidation(viewState, Utility.NAME_PATTERN))
            throw new AddressBookException("Please enter valid State");

        int index = IntStream.range(0, personList.size())
                .filter(i -> viewCity.equals(personList.get(i).getCity()) && (viewState.equals(personList.get(i).getState())))
                .findFirst().orElseThrow(() -> new AddressBookException("No record found "));
        displayPersonDetails(index);
    }

    @Override
    public void viewByCityOrState() {
        System.out.println("Enter city OR state : ");
        String viewCityOrState = Utility.stringInput();
        if(!Utility.inputValidation(viewCityOrState, Utility.NAME_PATTERN))
            throw new AddressBookException("Please enter valid City or State");
        int index = IntStream.range(0, personList.size())
                .filter(i -> viewCityOrState.equals(personList.get(i).getCity()) || (viewCityOrState.equals(personList.get(i).getState())))
                .findFirst().orElseThrow(() -> new AddressBookException("No record found "));
        displayPersonDetails(index);
    }

    public void displayPersonDetails(int index){
        for (Person p:personList) {
            System.out.println(personList.get(index).getFirstName()+" "+personList.get(index).getLastName()+" "+
                    personList.get(index).getAddress()+" "+personList.get(index).getCity()+ " "+
                    personList.get(index).getState()+" "+personList.get(index).getZip()+" "+
                    personList.get(index).getPhoneNumber());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBook that = (AddressBook) o;
        return Objects.equals(person, that.person);
    }
}
