package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Company {
    private String companyName;
    private HashMap<Integer,Item> inventoryList = new HashMap<>();

    public void addItem(int barcodeNumber, Item item){
        inventoryList.put(barcodeNumber,item);
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public HashMap<Integer, Item> getAvailableInventoryList() {
        return inventoryList;
    }

}
