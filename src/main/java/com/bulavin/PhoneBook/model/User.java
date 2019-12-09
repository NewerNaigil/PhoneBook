package com.bulavin.PhoneBook.model;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private List<PhoneBookRecord> phoneBook = new ArrayList<>();

    public User(){}

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, List<PhoneBookRecord> phoneBook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneBook = phoneBook;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PhoneBookRecord> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(List<PhoneBookRecord> phoneBook) {
        this.phoneBook = phoneBook;
    }
}
