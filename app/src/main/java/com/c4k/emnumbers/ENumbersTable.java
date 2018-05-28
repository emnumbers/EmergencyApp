package com.c4k.emnumbers;

public class ENumbersTable {
    private int Id;
    private long PhoneNumber;
    private String Description;

    ENumbersTable(int Id, long eNumber, String description) {
        this.Id = Id;
        PhoneNumber = eNumber;
        Description = description;
    }
    public int getId() {
        return Id;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public String getDescription() {
        return Description;
    }
}