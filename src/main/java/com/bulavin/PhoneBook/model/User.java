package com.bulavin.PhoneBook.model;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private List<PhoneBookRecord> records = new ArrayList<>();


    public User(String userName){
        this.userName = userName;
    }

    public User(String userName, List<PhoneBookRecord> records) {
        this.userName = userName;
        this.records = records;
    }





    public String getUserName() {
        return userName;
    }

    public List<PhoneBookRecord> getRecords() {
        return records;
    }

    public void setRecords(List<PhoneBookRecord> records) {
        this.records = records;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }




}
