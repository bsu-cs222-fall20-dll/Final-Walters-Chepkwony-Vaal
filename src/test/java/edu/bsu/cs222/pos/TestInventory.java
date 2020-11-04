package edu.bsu.cs222.pos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

public class TestInventory {
    @Test
    public void testEmptyCompany(){
        String companyName = "SampleCompany";
        Company company = new Company(companyName, true);
        HashMap<String, Item> availableInventoryList = null;
        try {
            availableInventoryList = company.getAvailableInventoryList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assertions.assertTrue(availableInventoryList.isEmpty());
    }
    @Test
    public void testAddInventory(){
        Company company = new Company("SampleCompany", true);
        Item item = new Item("soup", new BigDecimal("3.85"));
        try {
            company.addItem("12345678901",item);
            HashMap<String, Item> InventoryList = company.getAvailableInventoryList();
            Item dbItem = InventoryList.get("12345678901");
            Assertions.assertEquals(dbItem.toString(),item.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testUpdatedItemName(){
        Company company = new Company("SampleCompany");
        Item sampleItem = new Item("Sloup",BigDecimal.valueOf(5.55));
        try {
            company.addItem("12322", sampleItem);
            company.updateItemName("12322", "soup");
            Item sampleItemFromCompany = company.getAvailableInventoryList().get("12322");
            Assertions.assertEquals("soup",sampleItemFromCompany.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }/*

    @Test
    public void testUniqueBarCodeGenerator(){
        Company company = new Company("SampleCompany");
        BarcodeGenerator barcodeGenerator = new BarcodeGenerator(company.getAvailableInventoryList());
        Item testItem = new Item("sample", BigDecimal.valueOf(44.44));
        company.addItem("12345678901",testItem);
        String uniqueBarcode = barcodeGenerator.makeNewCode();
        company.addItem(uniqueBarcode, testItem );
        Assertions.assertTrue(barcodeGenerator.getUsedCodes().contains(uniqueBarcode));
    }
    @Test
    public void testUpdatedItemCost(){
        Company company = new Company("SampleCompany");
        Item sampleItem = new Item("soup",BigDecimal.valueOf(5.25));
        company.addItem("12322",sampleItem);
        sampleItem.setPrice(BigDecimal.valueOf(5.55));
        Assertions.assertEquals(BigDecimal.valueOf(5.55),sampleItem.getPrice());
    }

    @Test
    public void testItemSearch(){
        Company company = new Company("SampleCompany");
        Item sampleItem = new Item("soup",BigDecimal.valueOf(5.25));
        company.addItem("12345678901",sampleItem);
        Item searchedItem = company.searchByItemName("soup");
        Assertions.assertEquals(sampleItem,searchedItem);
    }
    @Test
    public void testDeleteInventory(){
        Company company = new Company("SampleCompany");
        Item item = new Item("soup", BigDecimal.valueOf(3.85));
        HashMap<String, Item> InventoryList = company.getAvailableInventoryList();
        company.addItem("12345678901",item);
        company.removeItem("12345678901");
        Assertions.assertNull(InventoryList.get("12345678901"));
    }*/
}
