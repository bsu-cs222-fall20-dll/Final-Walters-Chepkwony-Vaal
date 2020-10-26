package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Company {
    private String companyName;
    private HashMap<String,Item> inventoryList = new HashMap<>();

    public void addItem(String barcodeNumber, Item item){
        inventoryList.put(barcodeNumber,item);
    }
    private void updateItem(String barcodeNumber, Item item){
        inventoryList.put(barcodeNumber, item);
    }
    public void updateItemName(String barcodeNumber, String newName){
        Item item = inventoryList.get(barcodeNumber);
        item.setName(newName);
        updateItem(barcodeNumber,item);
    }
    public void updatedItemCost(String barcodeNumber, BigDecimal itemPrice){
        Item item = inventoryList.get(barcodeNumber);
        item.setPrice(itemPrice);
        updateItem(barcodeNumber,item);
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public HashMap<String, Item> getAvailableInventoryList() {
        return inventoryList;
    }

    public void removeItem(String id) {
        inventoryList.remove(id);
    }

    public Item searchByItemName(String searchItemName) {
        for(Item item : inventoryList.values()){
            if(item.getName().equals(searchItemName)){
                return item;
            }
        }
        return null;
    }
}
