package com.addressbook;

import com.addressbook.service.implementation.AddressBook;
import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        String continueOrExit;
        do {
            System.out.println("To Add a Person,Enter 1 : \nTo Edit person details,Enter 2 : " +
                    "\nTo Delete person details,Enter 3 : \nTo Sort person details by name,Enter 4 : \n" +
                    "To Sort Person by city,Enter 5 : \nTo Sort Person by state,Enter 6 : \nTo Sort Person by zip,Enter 7 : " +
                    "\nTo Person by City and State,Enter 8");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    addressBook.addPerson();
                    break;
                case 2:
                    addressBook.editPerson();
                    break;
                case 3:
                    addressBook.deletePerson();
                    break;
                case 4:
                    addressBook.sortByName();
                    break;
                case 5:
                    addressBook.sortByCity();
                    break;
                case 6:
                    addressBook.sortByState();
                    break;
                case 7:
                    addressBook.sortByZip();
                    break;
                case 8:
                    addressBook.viewByCityAndState();
                    break;
                default:
                    System.out.println("You have entered invalid option");
                    break;
            }
            System.out.println("Do you want to continue?Enter y/n :");
            continueOrExit = sc.next();
        }
        while (continueOrExit.equals("y"));
    }
}
