package com.addressbook.service;

import com.addressbook.model.Person;

import java.util.ArrayList;

public interface IJdbcOperations {
    public void writeData(ArrayList<Person> personList);
    public void readData();
}
