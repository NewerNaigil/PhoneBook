package com.bulavin.PhoneBook.model;

public class PhoneBookRecord {
    private static Long idCounter = 0L;
    private Long recordId;
    private String recordName;
    private String recordNumber;

    public PhoneBookRecord(String recordName, String recordNumber){
        ++idCounter;
        this.recordId = idCounter;
        this.recordName = recordName;
        this.recordNumber = recordNumber;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }
}
