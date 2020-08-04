package com.addressbook.service;

public interface IAddressBook {
    void addPerson();
    void editPerson();
    void deletePerson();
    void viewByCityAndState();
    void viewByCityOrState();
}
