package edu.bsu.cs222.pos;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class TestDiscount {
    @Test
    public void testPercentageDiscount() {
        Discount discount = new Discount("1234","Test Discount", BigDecimal.valueOf(0.05),true);
        ArrayList<Item> sampleItemList = new ArrayList<>();
        sampleItemList.add(new Item("Soup",BigDecimal.valueOf(1.00)));
        Assertions.assertEquals(0,
                discount.applyDiscount(sampleItemList)
                        .compareTo(BigDecimal.valueOf(0.05)));
    }
    @Test
    public void testSumDiscount() {
        Discount discount = new Discount("1234","Test Discount", BigDecimal.valueOf(0.05),true);
        ArrayList<Item> sampleItemList = new ArrayList<>();
        sampleItemList.add(new Item("Soup",BigDecimal.valueOf(3.00)));
        sampleItemList.add(new Item("Croutons",BigDecimal.valueOf(0.25)));
        sampleItemList.add(new Item("Soft Drink",BigDecimal.valueOf(1.75)));
        Assertions.assertEquals(0,
                discount.applyDiscount(sampleItemList)
                        .compareTo(BigDecimal.valueOf(0.25)));
    }
    @Test
    public void testAddDiscount() {
        Company company = new Company("SampleDiscountCompany",true);
        Discount discount = new Discount("123","Sample Discount", BigDecimal.valueOf(1.00), false);
        company.addDiscount("123", discount);
        Assertions.assertNotNull(company.getDiscountByID("123"));
        company.emptyDatabase();
    }
    @Test
    public void testListDiscounts() {
        Company company = new Company("SampleDiscountCompany",true);
        Discount discount = new Discount("123","Sample Discount", BigDecimal.valueOf(1.00), false);
        company.addDiscount("123", discount);
        HashMap<String, Discount> empty = new HashMap<>();
        Assertions.assertNotEquals(empty,company.getActiveCoupons());
        company.emptyDatabase();
    }
    @Test
    public void testUpdateDiscountName() {
        Company company = new Company("SampleDiscountCompany",true);
        Discount discount = new Discount("123","Sample Discount", BigDecimal.valueOf(1.00), false);
        company.addDiscount("123", discount);
        company.updateDiscountName("123", "Renamed Discount");
        Discount updatedDiscount = company.getDiscountByID("123");
        Assertions.assertEquals("Renamed Discount",updatedDiscount.name);
        company.emptyDatabase();
    }
    @Test
    public void testUpdateDiscountAmount() {
        Company company = new Company("SampleDiscountCompany",true);
        Discount discount = new Discount("123","Sample Discount", BigDecimal.valueOf(1.00), false);
        company.addDiscount("123", discount);
        company.updateDiscountAmount("123",BigDecimal.valueOf(0.1),true);
        Discount updatedDiscount = company.getDiscountByID("123");
        Assertions.assertTrue(updatedDiscount.isPercentage);
        Assertions.assertEquals(0,
                updatedDiscount.amount
                        .compareTo(BigDecimal.valueOf(0.1)));
        company.emptyDatabase();
    }
    @Test
    public void testUniqueCouponCodeGenerator(){
        Company company = new Company("SampleDiscountCompany",true);
        Discount discount = new Discount("Sample Discount", BigDecimal.valueOf(1.00), false);
        String couponCode = company.generateCouponCode();
        company.addDiscount(couponCode, discount);
        Assertions.assertNotNull(company.getDiscountByID(couponCode));
        company.emptyDatabase();
    }
}
