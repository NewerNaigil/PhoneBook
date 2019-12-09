package com.bulavin.PhoneBook.model;

public class PhoneBookRecord {
    private static Long idCounter = 0L;
    private Long id;
    private String name;
    private String number;

    public PhoneBookRecord(String name, String number){
        ++idCounter;
        this.id = idCounter;
        this.name = name;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
