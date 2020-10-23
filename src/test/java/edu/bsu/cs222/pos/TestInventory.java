package edu.bsu.cs222.pos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class TestInventory {
    @Test
    public void testEmptyCompany(){
        String companyName = "sampleName";
        Company company = new Company(companyName);
        HashMap<Integer, Item> availableInventoryList = company.getAvailableInventoryList();
        Assertions.assertTrue(availableInventoryList.isEmpty());
    }
    @Test
    public void testAddInventory(){
        Company company = new Company("AnotherName");
        Item item = new Item("soup", BigDecimal.valueOf(3.85));
        HashMap<Integer, Item> InventoryList = company.getAvailableInventoryList();
        company.addItem(12345,item);
        Assertions.assertFalse(InventoryList.isEmpty());
    }
}
