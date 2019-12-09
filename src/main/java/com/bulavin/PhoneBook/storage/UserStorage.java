package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class UserStorage {
    private static Map<Long, User> store = new HashMap();
    private static long key = 0;

    // Методы для работы с пользователем

    public void createUser(String firstName, String lastName){
        ++key;
        store.put(key, new User(firstName, lastName));
    }

    public void createUser(String firstName, String lastName, List<PhoneBookRecord> phoneBook){
        ++key;
        store.put(key, new User(firstName, lastName, phoneBook));
    }

    public void deleteUser(long userId){
        store.remove(userId);
    }

    public User getUserById(long userId){
        return store.get(userId);
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        userList.addAll(store.values());
        return userList;
    }

    public void pathUser(long userId, String firstName){
        store.get(userId).setFirstName(firstName);
    }


    // Методы для работы с телефонной книгой

    public void createRecord(long userId, String recordName, String recordNumber){
        store.get(userId).getPhoneBook().add(new PhoneBookRecord(recordName, recordNumber));
    }

    public PhoneBookRecord getRecordById(long userId, Integer recordId){
        return store.get(userId).getPhoneBook().get(recordId);
    }


}
