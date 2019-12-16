package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.exceptions.NoNameUserException;
import com.bulavin.PhoneBook.exceptions.NotFoundUserException;
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


    // Методы для работы с пользователем!

//    public void createUser(String firstName, String lastName){
//        User user = new User(firstName, lastName);
//        if (user.getFirstName() == null){
//            throw new NoNameUserException();
//        }
//        store.put(user.getUserID(), user);
//    }

    public void createUser(String firstName, String lastName, List<PhoneBookRecord> phoneBook){
        User user = new User(firstName, lastName, phoneBook);
        if (user.getFirstName() == null){
            throw new NoNameUserException();
        }
            store.put(user.getUserID(), user);
    }

    public void deleteUser(long userId){
        if (store.get(userId) == null) {
            throw new NotFoundUserException();
        }
        store.remove(userId);
    }

    public User getUserById(long userId) {
        User user = store.get(userId);
        if (user == null){
            throw new NotFoundUserException();
        }
        return user;
    }


    public Map<Long, User> getAllUser(){
        if (store.size() == 0){
            throw new NotFoundUserException();
        }
        return store;
    }

    public void pathUser(long userId, String firstName, String lastName){
        if (store.get(userId) == null){
            throw new NotFoundUserException();
        }
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

//
//    // Методы для работы с телефонной книгой!
//
//    public String createRecord(long userId, String recordName, String recordNumber){
//        if(store.containsKey(userId)){
//            store.get(userId).getPhoneBook().add(new PhoneBookRecord(recordName, recordNumber));
//            return "Телефонная запись добавлена";
//        }
//        else {
//            return "Пользователь с ID: "+userId+" не существует.";
//        }
//
//    }
//
//    public PhoneBookRecord getRecordById(long userId, long recordId){
//        for (PhoneBookRecord records : store.get(userId).getPhoneBook()){
//            if (records.getRecordId() == recordId) {
//                return records;
//            }
//        }
//        return null;
//    }
//
//    public void deleteRecord(long userId, long recordId){
//        store.get(userId).getPhoneBook().removeIf(records -> records.getRecordId() == recordId);
//    }
//
//    public String pathRecord(long userId, long recordId, String recordName, String recordNumber){
//        if (store.containsKey(userId)){
//            for (PhoneBookRecord records : store.get(userId).getPhoneBook()) {
//                if (records.getRecordId() == recordId) {
//                    records.setRecordName(recordName);
//                    records.setRecordNumber(recordNumber);
//                    return "Телефонная запись обновлена";
//                }
//            }
//            return "Телефонная записи с ID: "+recordId+" не существует.";
//        }
//        else
//            return "Пользователь с ID: "+userId+" не существует.";
//    }
//
//    public List<PhoneBookRecord> getAllRecordsUser(long userId){
//        return store.get(userId).getPhoneBook();
//    }
//
//    public List<PhoneBookRecord> searchRecord(long userId, String searchRequest){
//        List<PhoneBookRecord> recordList = new ArrayList<>();
//        for (PhoneBookRecord record : store.get(userId).getPhoneBook()){
//            if (record.getRecordNumber().equals(searchRequest)){
//                recordList.add(record);
//            }
//        }
//        return recordList;
//    }


    public static Map<Long, User> getStore() {
        return store;
    }
}
