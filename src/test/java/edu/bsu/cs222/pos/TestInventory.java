package edu.bsu.cs222.pos;

import com.sun.source.tree.AssertTree;
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
        Assertions.assertEquals(InventoryList.get(12345),item);
    }
    @Test
    public void checkDatabaseConnection(){
        //TODO
    }
    @Test
    public void testUpdatedItemName(){
        Company company = new Company("SampleCompany");
        Item sampleItem = new Item("Sloup",BigDecimal.valueOf(5.55));
        company.addItem(12322, sampleItem);
        company.updateItemName(12322, "soup");
        Item sampleItemFromCompany = company.getAvailableInventoryList().get(12322);
        Assertions.assertEquals("soup",sampleItemFromCompany.getName());
    }

    @Test
    public void testUpdatedItemCost(){
            Company company = new Company("SampleCompany");
            Item sampleItem = new Item("soup",BigDecimal.valueOf(5.25));
            company.addItem(12322,sampleItem);
            sampleItem.setPrice(BigDecimal.valueOf(5.55));
            Assertions.assertEquals(BigDecimal.valueOf(5.55),sampleItem.getPrice());
    }

    @Test
    public void testUpdatedItemImage(){
    //TODO
    }
}
