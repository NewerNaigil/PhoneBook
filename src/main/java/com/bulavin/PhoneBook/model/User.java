package com.bulavin.PhoneBook.model;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private List<PhoneBookRecord> records;

    public User(String userName, List<PhoneBookRecord> records) {
        this.userName = userName;
        this.records = new ArrayList<>(records);
    }


}
