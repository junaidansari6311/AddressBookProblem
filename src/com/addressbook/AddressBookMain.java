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
            System.out.println("To Add a Person,Enter 1 : \nTo Edit person details,Enter 2 : ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    addressBook.addPerson();
                    break;
                case 2:
                    addressBook.editPerson();
                    break;
                default:
                    System.out.println("You have entered invalid option");
                    break;
            }
            System.out.println("Do you want to continue.Enter y/n :");
            continueOrExit = sc.next();
        }
        while (continueOrExit.equals("y"));
    }
}
