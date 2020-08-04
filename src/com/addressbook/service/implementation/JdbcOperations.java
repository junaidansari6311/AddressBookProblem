package com.addressbook.service.implementation;

import com.addressbook.model.Person;
import com.addressbook.service.IJdbcOperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class JdbcOperations implements IJdbcOperations {
    @Override
    public void writeData(ArrayList<Person> personList){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook", "root", "admin");
            String insertsql="Insert into person (first_name ,last_name ,address ,city ,state ,zip ,phone_number) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(insertsql);
            Iterator<Person> it=personList.iterator();
            while(it.hasNext()){
                Person person1=(Person) it.next();
                ps.setString(1, person1.getFirstName());
                ps.setString(2, person1.getLastName());
                ps.setString(3, person1.getAddress());
                ps.setString(4, person1.getCity());
                ps.setString(5, person1.getState());
                ps.setInt(6,person1.getZip());
                ps.setLong(7,person1.getPhoneNumber());

                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
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
}
