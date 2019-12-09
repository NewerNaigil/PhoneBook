package com.bulavin.PhoneBook.model;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private List<PhoneBookRecord> phoneBook = new ArrayList<>();


    public User(String userName){
        this.userName = userName;
    }

    public User(String userName, List<PhoneBookRecord> phoneBook) {
        this.userName = userName;
        this.phoneBook = phoneBook;
    }





    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PhoneBookRecord> getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(List<PhoneBookRecord> phoneBook) {
        this.phoneBook = phoneBook;
    }
}
