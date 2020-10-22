package edu.bsu.cs222.pos;

import java.util.ArrayList;
import java.util.HashMap;

public class Company {
    private String companyName;
    private HashMap<Integer,Item> inventoryList = new HashMap<>();
    public Company(String companyName) {
        this.companyName = companyName;
    }

    public HashMap<Integer, Item> getAvailableInventoryList() {
        return inventoryList;
    }
}
