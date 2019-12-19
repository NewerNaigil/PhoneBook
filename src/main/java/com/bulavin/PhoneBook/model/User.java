package com.bulavin.PhoneBook.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private long userID;
    private static long idCounter;
    private String firstName;
    private String lastName;
    private List<PhoneBookRecord> phoneBook = new ArrayList<>();

    public User(){}

    public User(String firstName, String lastName, List<PhoneBookRecord> phoneBook) {
        idCounter++;
        this.userID = idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneBook = phoneBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                phoneBook.equals(user.phoneBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, firstName, lastName, phoneBook);
    }

    public long getUserID() {
        return userID;
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

    public static void setIdCounter(long idCounter) {
        User.idCounter = idCounter;
    }
}
