package edu.bsu.cs222.pos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

import java.util.HashMap;

public class TestInventory {
    @Test
    public void testEmptyCompany(){
        String companyName = "EmptyCompany";
        Company company = new Company(companyName, true);
        Assertions.assertTrue(company.getAvailableInventoryList().isEmpty());
        company.emptyDatabase();
    }
    @Test
    public void testAddInventory(){
        Company company = new Company("SampleCompany",true);
        Item item = new Item("soup", new BigDecimal("3.85"));
        company.addItem("12345678901",item);
        HashMap<String, Item> InventoryList = company.getAvailableInventoryList();
        Item dbItem = InventoryList.get("12345678901");
        Assertions.assertEquals(dbItem.getName(),item.getName());
        company.emptyDatabase();
    }
    @Test
    public void testUpdatedItemName(){
        Company company = new Company("SampleCompany",true);
        Item sampleItem = new Item("Sloup",BigDecimal.valueOf(5.55));
        company.addItem("12322", sampleItem);
        //TODO update item in db Owen
        company.updateItemName("12322", "soup");
        Item sampleItemFromCompany = company.getAvailableInventoryList().get("12322");
        Assertions.assertEquals("soup",sampleItemFromCompany.getName());
        company.emptyDatabase();
    }

    @Test
    public void testUniqueBarCodeGenerator(){
        Company company = new Company("SampleCompany",true);
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(company);
        Item testItem = new Item("sample", BigDecimal.valueOf(44.44));
        String uniqueBarcode = barcodeGenerator.makeNewCode();
        company.addItem(uniqueBarcode, testItem );
        Assertions.assertNotNull(company.getItemByID(uniqueBarcode));
        company.emptyDatabase();
    }
    @Test
    public void testUpdatedItemCost(){
        Company company = new Company("SampleCompany",true);
        Item sampleItem = new Item("soup",BigDecimal.valueOf(5.25));
        company.addItem("12322",sampleItem);
        company.updateItemCost("12322",BigDecimal.valueOf(5.55));
        Assertions.assertEquals(BigDecimal.valueOf(5.55),company.getItemByID("12322").getPrice());
        company.emptyDatabase();
    }

    @Test
    public void testItemSearch(){
        Company company = new Company("SampleCompany",true);
        Item sampleItem = new Item("soup",BigDecimal.valueOf(5.25));
        company.addItem("12345678901",sampleItem);
        Item searchedItem = company.getItemByID("12345678901");
        Assertions.assertEquals(sampleItem.getName(),searchedItem.getName());
        company.emptyDatabase();
    }
    @Test
    public void testDeleteInventory(){
        Company company = new Company("SampleCompany",true);
        Item item = new Item("soup", BigDecimal.valueOf(3.85));
        HashMap<String, Item> InventoryList = company.getAvailableInventoryList();
        company.addItem("12345678901",item);
        company.removeItem("12345678901");
        Assertions.assertNull(InventoryList.get("12345678901"));
        company.emptyDatabase();
    }
}
