package edu.bsu.cs222.pos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.HashMap;

public class TestReceipts {
    @Test
    public void testEmptyOrder() {
        Order order = new Order();
        HashMap<String, BigDecimal> itemList = order.getItemList();
        Assertions.assertNull(itemList);
    }

    public void testOrderWithAddition(){
        Order order = new Order();
        Item item = new Item("sponge", 5.25);
        order.addItem(item);
        Assertions.assertEquals(1,order.getsize());
    }





}
