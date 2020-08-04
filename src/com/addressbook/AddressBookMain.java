package com.addressbook;

import com.addressbook.service.implementation.*;
import java.util.Scanner;
public class AddressBookMain {

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        String continueOrExit;
        do {
            System.out.println("Enter 1 To Add A Person : \nEnter 2 To Edit Person details : " +
                    "\nEnter 3 To Delete Person details: \nEnter 4 To Sort person details by name : \n" +
                    "Enter 5 To Sort Person by city : \nEnter 6 To Sort Person by state : \nEnter 7 To Sort Person by zip : " +
                    "\nEnter 8 To view Person by City AND State : \nEnter 9 To view Person by City OR State : ");
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
                    addressBook.sortByField("name");
                    break;
                case 5:
                    addressBook.sortByField("city");
                    break;
                case 6:
                    addressBook.sortByField("state");
                    break;
                case 7:
                    addressBook.sortByField("zip");
                    break;
                case 8:
                    addressBook.viewByCityAndState();
                    break;
                case 9:
                    addressBook.viewByCityOrState();
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
