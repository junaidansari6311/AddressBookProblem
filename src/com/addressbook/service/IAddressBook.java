package com.addressbook.service;

import java.io.IOException;

public interface IAddressBook {
    void addPerson();
    void editPerson();
    void deletePerson();
    void sortByName();
    void sortByCity();
    void sortByState();
    void sortByZip();
    void viewByCityAndState();
    void viewByCityOrState();
}
