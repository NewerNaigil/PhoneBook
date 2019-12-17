package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.exceptions.NoNameUserException;
import com.bulavin.PhoneBook.exceptions.NotFoundRecordException;
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

    public void createUser(String firstName, String lastName, List<PhoneBookRecord> phoneBook) {
        User user = new User(firstName, lastName, phoneBook);
        if (user.getFirstName() == null) {
            throw new NoNameUserException();
        }
        store.put(user.getUserID(), user);
    }

    public void deleteUser(long userId) {
        if (store.get(userId) == null) {
            throw new NotFoundUserException();
        }
        store.remove(userId);
    }

    public User getUserById(long userId) {
        User user = store.get(userId);
        if (user == null) {
            throw new NotFoundUserException();
        }
        return user;
    }


    public Map<Long, User> getAllUser() {
        if (store.size() == 0) {
            throw new NotFoundUserException();
        }
        return store;
    }

    public void pathUser(long userId, String firstName, String lastName) {
        if (store.get(userId) == null) {
            throw new NotFoundUserException();
        }
        store.get(userId).setFirstName(firstName);
        store.get(userId).setLastName(lastName);
    }

    public List<User> searchUser(String searchRequest) {
        List<User> userList = new ArrayList<>();
        for (User user : store.values()) {
            if (user.getFirstName().toUpperCase().contains(searchRequest.toUpperCase())) {
                userList.add(user);
            }
        }
        if (userList.isEmpty())
            throw new NotFoundUserException();
        return userList;
    }


    // Методы для работы с телефонной книгой!

    public void createRecord(long userId, String recordName, String recordNumber) {
        if (store.containsKey(userId)) {
            store.get(userId).getPhoneBook().add(new PhoneBookRecord(recordName, recordNumber));
        } else {
            throw new NotFoundUserException();
        }

    }

    public PhoneBookRecord getRecordById(long userId, long recordId) {
        if (store.containsKey(userId)) {
            for (PhoneBookRecord records : store.get(userId).getPhoneBook()) {
                if (records.getRecordId() == recordId) {
                    return records;
                }
            }
            throw new NotFoundRecordException();
        } else {
            throw new NotFoundUserException();
        }
    }

    // Изменить
    public void deleteRecord(long userId, long recordId) {
        if (store.containsKey(userId)) {
            for (PhoneBookRecord record : store.get(userId).getPhoneBook()) {
                if (record.getRecordId() == recordId) {
                    store.get(userId).getPhoneBook().remove(record);
                    return;
                }
            }
            throw new NotFoundRecordException();
        } else
            throw new NotFoundUserException();
    }

    public void pathRecord(long userId, long recordId, String recordName, String recordNumber) {
        if (store.containsKey(userId)) {
            for (PhoneBookRecord records : store.get(userId).getPhoneBook()) {
                if (records.getRecordId() == recordId) {
                    records.setRecordName(recordName);
                    records.setRecordNumber(recordNumber);
                    return;
                }
            }
            throw new NotFoundRecordException();
        } else
            throw new NotFoundUserException();
    }

    public List<PhoneBookRecord> getAllRecordsUser(long userId){
        if (store.containsKey(userId)) {
            return store.get(userId).getPhoneBook();
        }
        else
            throw new NotFoundUserException();
    }

    public List<PhoneBookRecord> searchRecord(long userId, String searchRequest) {
        List<PhoneBookRecord> recordList = new ArrayList<>();
        if (store.containsKey(userId)) {
            for (PhoneBookRecord record : store.get(userId).getPhoneBook()) {
                if (record.getRecordNumber().equals(searchRequest)) {
                    recordList.add(record);
                }
            }
            if (recordList.isEmpty()) {
                throw new NotFoundRecordException();
            } else
                return recordList;
        } else
            throw new NotFoundUserException();
    }


    public static Map<Long, User> getStore() {
        return store;
    }
}
