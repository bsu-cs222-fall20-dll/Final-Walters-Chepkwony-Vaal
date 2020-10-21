package edu.bsu.cs222.pos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class TestInventory {
    @Test
    public void testNewEmptyCompany(){
        String companyName = "sampleName";
        Company company = new Company(companyName);
        ArrayList<Item> availableInventoryList = company.getAvailableInventoryList();
        Assertions.assertNull(availableInventoryList);
    }





}
