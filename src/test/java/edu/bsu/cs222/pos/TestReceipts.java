package edu.bsu.cs222.pos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;

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
        Assertions.assertEquals(1,order.getSize());
    }
    @Test
    public void testOrderWithRemoving(){
        Order order = new Order();
        Item item = new Item("Soup",BigDecimal.valueOf(3.67));
        order.addItem(item);
        order.deleteItem(item);
        Assertions.assertEquals(0,order.getSize());
    }
    @Test
    public void testUpdatedItemPrice(){
        Order order = new Order();
        Item item = new Item("Napkin",BigDecimal.valueOf(1.50));
        order.addItem(item);
        item.setPrice(BigDecimal.valueOf(1.50));
        Assertions.assertEquals(BigDecimal.valueOf(1.50),item.getPrice());
    }
    @Test
    public void testTotalOrderPrice(){
        Order order = new Order();
        Item item1 = new Item("Soup",BigDecimal.valueOf(3.67));
        Item item2 = new Item("Napkin",BigDecimal.valueOf(1.50));
        order.addItem(item1);
        order.addItem(item2);
        BigDecimal subtotal = order.getSubtotal();
        BigDecimal presumedTotal = BigDecimal.valueOf(1.50).add(BigDecimal.valueOf(3.67));
        Assertions.assertEquals(presumedTotal,subtotal);
    }
    @Test
    public void testCalculateTax(){

        Order order = new Order();
        Item item1 = new Item("Soup",BigDecimal.valueOf(3.67));
        Item item2 = new Item("Napkin",BigDecimal.valueOf(1.50));
        order.addItem(item1);
        order.addItem(item2);
        BigDecimal presumedTotal = BigDecimal.valueOf(1.50).add(BigDecimal.valueOf(3.67)).multiply(BigDecimal.valueOf(.07));
        Assertions.assertEquals(presumedTotal,order.getTax());
    }
    @Test
    public void testCalculateChangedTax(){
        Order order = new Order();
        Item item1 = new Item("Soup",BigDecimal.valueOf(3.67));
        Item item2 = new Item("Napkin",BigDecimal.valueOf(1.50));
        order.setTaxRate(BigDecimal.valueOf(.09));
        order.addItem(item1);
        order.addItem(item2);
        BigDecimal total =order.getTotalWithTax();
        BigDecimal presumedSubtotal = BigDecimal.valueOf(1.50).add(BigDecimal.valueOf(3.67)).multiply(BigDecimal.valueOf(1.09));
        Assertions.assertEquals(presumedSubtotal,total);
    }

}
