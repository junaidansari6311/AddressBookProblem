package com.addressbook.service.implementation;

import com.addressbook.model.Person;
import com.addressbook.service.IJsonFileOperations;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class JsonFileOperations implements IJsonFileOperations {
    Gson gson = new Gson();

    @Override
    public void writeToJsonFile(ArrayList<Person> personList){
        try {
            Writer writer = new FileWriter("person.json");
            gson.toJson(personList, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void readFromJsonFile(){
        try {
            ArrayList<Person> person1 = gson.fromJson(new FileReader("person.json"),new TypeToken<ArrayList<Person>>() {}.getType());
            System.out.println(person1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
