package com.addressbook.service.implementation;

import com.addressbook.exception.AddressBookException;
import com.addressbook.model.Person;
import com.addressbook.service.IAddressBook;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.IntStream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddressBook implements IAddressBook {

    Scanner sc  = new Scanner(System.in);
    public static ArrayList<Person> personList = new ArrayList<>();
    Person person = new Person();
    Gson gson = new Gson();

    public void writeToJsonFile(){
        try {
            Writer writer = new FileWriter("person.json");
            new Gson().toJson(personList, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void readFromJsonFile(){
        try {
            ArrayList<Person> person1 = gson.fromJson(new FileReader("person.json"),new TypeToken<ArrayList<Person>>() {}.getType());
            System.out.println(person1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook", "root", "admin");
            String insertsql="Insert into person (first_name ,last_name ,address ,city ,state ,zip ,phone_number) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(insertsql);
            Iterator<Person> it=personList.iterator();
            while(it.hasNext()){
                Person person1=(Person) it.next();
                ps.setString(1, person.getFirstName());
                ps.setString(2, person.getLastName());
                ps.setString(3, person.getAddress());
                ps.setString(4, person.getCity());
                ps.setString(5, person.getState());
                ps.setInt(6,person.getZip());
                ps.setLong(7,person.getPhoneNumber());

                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readData(){
        ArrayList<Person> personArrayList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/addressbook";
            Connection connection = DriverManager.getConnection(url, "root", "admin");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person");
            while (resultSet.next()) {
                Person person = new Person();
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setAddress(resultSet.getString("address"));
                person.setCity(resultSet.getString("city"));
                person.setState(resultSet.getString("state"));
                person.setZip(resultSet.getInt("zip"));
                person.setPhoneNumber(resultSet.getLong("phone_number"));
                personArrayList.add(person);
            }

            for (Person person : personArrayList) {
                System.out.print("FirstName: "+person.getFirstName()+", ");
                System.out.print("LastName: "+person.getLastName()+", ");
                System.out.print("Address: "+person.getAddress()+", ");
                System.out.print("City: "+person.getCity()+", ");
                System.out.print("State: "+person.getState()+", ");
                System.out.print("Zip: "+person.getZip()+", ");
                System.out.print("PhoneNumber: "+person.getPhoneNumber());
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addPerson() {
        System.out.println("Enter first name : ");
        String fName = sc.next();

        int index =
                IntStream.range(0, personList.size())
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
        writeToJsonFile();
        writeData();
        System.out.println("Person details are being added...");
    }

    @Override
    public void editPerson() {
        if(personList.size() == 0){
            throw new AddressBookException("No records found");
        }
        readFromJsonFile();
        readData();
        System.out.println("Enter person name to edit :");
        String name = sc.next();
        int index = IntStream.range(0, personList.size())
                .filter(i -> name.equals(personList.get(i).getFirstName()))
                .findFirst().orElseThrow(() -> new AddressBookException("Person not found - " + name));
        if(index==0){
            System.out.println("Enter address : ");
            String address = sc.next();
            personList.get(index).setAddress(address);

            System.out.println("Enter city : ");
            String city = sc.next();
            personList.get(index).setCity(city);

            System.out.println("Enter state : ");
            String state = sc.next();
            personList.get(index).setState(state);

            System.out.println("Enter zip : ");
            int zip = sc.nextInt();
            personList.get(index).setZip(zip);

            System.out.println("Enter phone number : ");
            long phoneNo = sc.nextLong();
            personList.get(index).setPhoneNumber(phoneNo);
            personList.add(person);
            writeToJsonFile();
        }
    }

    @Override
    public void deletePerson() {
        if(personList.size() == 0){
            throw new AddressBookException("No records found");
        }
        readFromJsonFile();
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
    public void viewByCityAndState() {
        System.out.println("Enter city:");
        String viewCity = sc.next();
        System.out.println("Enter state:");
        String viewState = sc.next();
        int index = IntStream.range(0, personList.size())
                .filter(i -> viewCity.equals(personList.get(i).getCity()) && (viewState.equals(personList.get(i).getState())))
                .findFirst().orElseThrow(() -> new AddressBookException("No record found "));
        displayPersonDetails(index);
    }

    @Override
    public void viewByCityOrState() {
        System.out.println("Enter city OR state : ");
        String viewCityOrState = sc.next();
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
