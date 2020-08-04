package com.addressbook.service;

import com.addressbook.model.Person;

import java.util.ArrayList;

public interface IJsonFileOperations {
    public void writeToJsonFile(ArrayList<Person> personList);
    public void readFromJsonFile();
}
