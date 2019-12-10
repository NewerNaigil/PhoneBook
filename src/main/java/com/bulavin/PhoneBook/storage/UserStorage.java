package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import org.springframework.stereotype.Component;

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

    public Map<Long, User> getAllUser(){
        return store;
    }

    public void pathUser(long userId, String firstName){
        store.get(userId).setFirstName(firstName);
    }


    // Методы для работы с телефонной книгой

    public void createRecord(long userId, String recordName, String recordNumber){
        store.get(userId).getPhoneBook().add(new PhoneBookRecord(recordName, recordNumber));
    }

    public PhoneBookRecord getRecordById(long userId, long recordId){
        PhoneBookRecord val = null;
        for (PhoneBookRecord records : store.get(userId).getPhoneBook()){
            if (records.getRecordId() == recordId) {
                val = records;
                break;
            }
        }
        return val;
    }

    public void deleteRecord(long userId, long recordId){
        PhoneBookRecord val = null;
        for (PhoneBookRecord records : store.get(userId).getPhoneBook()){
            if (records.getRecordId() == recordId) {
                store.get(userId).getPhoneBook().remove(records);
                break;
            }
        }
    }




}
