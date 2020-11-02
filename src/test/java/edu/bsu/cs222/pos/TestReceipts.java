package edu.bsu.cs222.pos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TestReceipts {
    @Test
    public void testEmptyOrder() {
        Order order = new Order("11/2/20  11:29am");
        ArrayList<Item> itemList = order.getItemList();
        Assertions.assertNull(itemList);
    }
    @Test
    public void testOrderWithAddition(){
        Order order = new Order("11/1/20  10:30am");
        Item item = new Item("sponge", BigDecimal.valueOf(5.25));
        order.addItem(item);
        Assertions.assertEquals(1,order.getSize());
    }
    @Test
    public void testOrderWithRemoving(){
        Order order = new Order("10/30/20  9:45am");
        Item item = new Item("Soup",BigDecimal.valueOf(3.67));
        order.addItem(item);
        order.deleteItem(item);
        Assertions.assertEquals(0,order.getSize());
    }
    @Test
    public void testUpdatedItemPrice(){
        Order order = new Order("10/31/20  11:42am");
        Item item = new Item("Napkin",BigDecimal.valueOf(1.50));
        order.addItem(item);
        item.setPrice(BigDecimal.valueOf(1.50));
        Assertions.assertEquals(BigDecimal.valueOf(1.50),item.getPrice());
    }
    @Test
    public void testUpdatedTimeAndDate(){
        Order order = new Order("11/2/20  8:56am");
        order.setDateAndTime("11/2/20  8:56am");
        Assertions.assertEquals("11/2/20  8:56am",order.getDataAndTime());
    }
}
