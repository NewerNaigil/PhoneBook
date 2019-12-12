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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhoneBookRecord record = (PhoneBookRecord) o;
        return recordId.equals(record.recordId) &&
                recordName.equals(record.recordName) &&
                recordNumber.equals(record.recordNumber);
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

    public static void setIdCounter(Long idCounter) {
        PhoneBookRecord.idCounter = idCounter;
    }
}
