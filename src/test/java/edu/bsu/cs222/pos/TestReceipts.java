package edu.bsu.cs222.pos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class TestReceipts {
    @Test
    public void testEmptyOrder() {
        Order order = new Order();
        ArrayList<Item> itemList = order.getItemList();
        Assertions.assertNull(itemList);
    }
    @Test
    public void testOrderWithAddition(){
        Order order = new Order();
        Item item = new Item("sponge", BigDecimal.valueOf(5.25));
        order.addItem(item);
        Assertions.assertEquals(1,order.getsize());
    }





}
