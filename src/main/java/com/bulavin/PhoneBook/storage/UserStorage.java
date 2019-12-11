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

    public Map<Long, User> getAllUser(){
        return store;
    }

    public void pathUser(long userId, String firstName, String lastName){
        store.get(userId).setFirstName(firstName);
        store.get(userId).setLastName(lastName);
    }

    public List<User> searchUser(String searchRequest){
        List <User> userList = new ArrayList<>();
        for (User user : store.values()){
            if (user.getFirstName().toUpperCase().contains(searchRequest.toUpperCase())){
                userList.add(user);
            }
        }
        return userList;
    }


    // Методы для работы с телефонной книгой

    public void createRecord(long userId, String recordName, String recordNumber){
        store.get(userId).getPhoneBook().add(new PhoneBookRecord(recordName, recordNumber));
    }

    public PhoneBookRecord getRecordById(long userId, long recordId){
        for (PhoneBookRecord records : store.get(userId).getPhoneBook()){
            if (records.getRecordId() == recordId) {
                return records;
            }
        }
        return null;
    }

    public void deleteRecord(long userId, long recordId){
        PhoneBookRecord val = null;
        store.get(userId).getPhoneBook().removeIf(records -> records.getRecordId() == recordId);
    }

    public void pathRecord(long userId, long recordId, String recordName, String recordNumber) {
        for (PhoneBookRecord records : store.get(userId).getPhoneBook()) {
            if (records.getRecordId() == recordId) {
                records.setRecordName(recordName);
                records.setRecordNumber(recordNumber);
                return;
            }
        }
    }

    public List<PhoneBookRecord> getAllRecordsUser(long userId){
        return store.get(userId).getPhoneBook();
    }

    public List<PhoneBookRecord> searchRecord(long userId, String searchRequest){
        List<PhoneBookRecord> recordList = new ArrayList<>();
        for (PhoneBookRecord record : store.get(userId).getPhoneBook()){
            if (record.getRecordNumber().equals(searchRequest)){
                recordList.add(record);
            }
        }
        return recordList;
    }


    public static Map<Long, User> getStore() {
        return store;
    }

    public static void setStore(Map<Long, User> store) {
        UserStorage.store = store;
    }
}
