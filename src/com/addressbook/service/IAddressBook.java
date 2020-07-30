package com.addressbook.service;

public interface IAddressBook {
    void addPerson();
    void editPerson();
    void deletePerson();
    void sortByName();
    void sortByCity();
    void sortByState();
    void sortByZip();
}
