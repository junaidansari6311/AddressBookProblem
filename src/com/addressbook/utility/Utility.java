package com.addressbook.utility;

import com.addressbook.exception.AddressBookException;
import com.addressbook.model.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Utility {
    public static final String NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}";
    public static final String PHONE_PATTERN = "^[1-9]{1}[0-9]{1,2}[ ][1-9]{1}[0-9]{9}$";
    public static final String ZIP_PATTERN = "^[1-9]{6}$";
    static Scanner scanner = new Scanner(System.in);

    public static int integerInput() {
        return scanner.nextInt();
    }

    public static String stringInput() {
        return scanner.next();
    }

    public static long longInput() {
        return scanner.nextLong();
    }

    public static Person getPersonDetails(Person person){
        System.out.println("Enter address : ");
        String address = Utility.stringInput();
        if(!inputValidation(address, NAME_PATTERN))
            throw new AddressBookException("Please enter valid address");
        person.setAddress(address);

        System.out.println("Enter city : ");
        String city = Utility.stringInput();
        if(!inputValidation(city, NAME_PATTERN))
            throw new AddressBookException("Please enter valid City");
        person.setCity(city);

        System.out.println("Enter state : ");
        String state = Utility.stringInput();
        if(!inputValidation(state, NAME_PATTERN))
            throw new AddressBookException("Please enter valid State");
        person.setState(state);

        System.out.println("Enter zip : ");
        int zip = Utility.integerInput();
        if(!inputValidation(state, ZIP_PATTERN))
            throw new AddressBookException("Please enter valid Zip code");
        person.setZip(zip);

        System.out.println("Enter phone number : ");
        long phoneNo = Utility.longInput();
        if(!inputValidation(state, PHONE_PATTERN))
            throw new AddressBookException("Please enter valid Phone Number");
        person.setPhoneNumber(phoneNo);

        return person;
    }

    public static Connection getConnectionObject(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/addressbook";
            String userName = "root";
            String passWord = "admin";
            connection = DriverManager.getConnection(url,userName,passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static boolean inputValidation(String data,String pattern){
        return data.matches(pattern);
    }
}
