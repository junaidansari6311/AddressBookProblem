package com.addressbook.service;

import com.addressbook.exception.AddressBookException;

public interface IAddressBook {
    void addPerson();
    void editPerson();
    void deletePerson();
}
