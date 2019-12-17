package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.exceptions.UserNoNameException;
import com.bulavin.PhoneBook.exceptions.RecordNotFoundException;
import com.bulavin.PhoneBook.exceptions.UserNotFoundException;
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
            throw new UserNoNameException();
        }
        store.put(user.getUserID(), user);
    }

    public void deleteUser(long userId) {
        if (store.get(userId) == null) {
            throw new UserNotFoundException();
        }
        store.remove(userId);
    }

    public User getUserById(long userId) {
        User user = store.get(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }


    public Map<Long, User> getAllUser() {
        return store;
    }

    public void pathUser(long userId, String firstName, String lastName) {
        if (store.get(userId) == null) {
            throw new UserNotFoundException();
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
            throw new UserNotFoundException();
        return userList;
    }


    // Методы для работы с телефонной книгой!

    public void createRecord(long userId, String recordName, String recordNumber) {
        if (store.containsKey(userId)) {
            store.get(userId).getPhoneBook().add(new PhoneBookRecord(recordName, recordNumber));
        } else {
            throw new UserNotFoundException();
        }

    }

    public PhoneBookRecord getRecordById(long userId, long recordId) {
        if (store.containsKey(userId)) {
            for (PhoneBookRecord records : store.get(userId).getPhoneBook()) {
                if (records.getRecordId() == recordId) {
                    return records;
                }
            }
            throw new RecordNotFoundException();
        } else {
            throw new UserNotFoundException();
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
            throw new RecordNotFoundException();
        } else
            throw new UserNotFoundException();
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
            throw new RecordNotFoundException();
        } else
            throw new UserNotFoundException();
    }

    public List<PhoneBookRecord> getAllRecordsUser(long userId){
        if (store.containsKey(userId)) {
            return store.get(userId).getPhoneBook();
        }
        else
            throw new UserNotFoundException();
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
                throw new RecordNotFoundException();
            } else
                return recordList;
        } else
            throw new UserNotFoundException();
    }


    public static Map<Long, User> getStore() {
        return store;
    }
}
