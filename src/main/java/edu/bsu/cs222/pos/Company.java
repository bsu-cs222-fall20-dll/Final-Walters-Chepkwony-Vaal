package edu.bsu.cs222.pos;

import java.util.ArrayList;

public class Company {
    private String companyName;
    private ArrayList<Item> inventoryList;
    public Company(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<Item> getAvailableInventoryList() {
        return inventoryList;
    }
}
