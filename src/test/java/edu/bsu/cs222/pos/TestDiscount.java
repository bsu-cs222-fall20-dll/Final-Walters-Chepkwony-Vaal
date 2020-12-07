package edu.bsu.cs222.pos;

import jdk.jfr.Percentage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TestDiscount {
    @Test
    public void AddDiscount() {
        Discount discount = new Discount("1234","Test Discount", BigDecimal.valueOf(0.05),true);
        ArrayList<Item> sampleItemList = new ArrayList<>();
        sampleItemList.add(new Item("Soup",BigDecimal.valueOf(1.00)));
        Assertions.assertEquals(0,
                discount.applyDiscount(sampleItemList)
                        .compareTo(BigDecimal.valueOf(0.05)));
    }
    @Test
    public void SumDiscount() {
        Discount discount = new Discount("1234","Test Discount", BigDecimal.valueOf(0.05),true);
        ArrayList<Item> sampleItemList = new ArrayList<>();
        sampleItemList.add(new Item("Soup",BigDecimal.valueOf(3.00)));
        sampleItemList.add(new Item("Croutons",BigDecimal.valueOf(0.25)));
        sampleItemList.add(new Item("Soft Drink",BigDecimal.valueOf(1.75)));
        Assertions.assertEquals(0,
                discount.applyDiscount(sampleItemList)
                        .compareTo(BigDecimal.valueOf(0.25)));
    }
}
