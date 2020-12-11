package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Discount {
    //CouponCode is not used until the discounts are fully implemented
    private String couponCode;
    public String name;
    public BigDecimal amount;
    public Boolean isPercentage;
    public Discount(String couponCode, String name, BigDecimal amount, Boolean isPercentage) {
        this.couponCode = couponCode;
        this.name = name;
        this.amount = amount;
        this.isPercentage = isPercentage;
    }


    public Discount(String name, BigDecimal amount, Boolean isPercentage) {
        this.name = name;
        this.amount = amount;
        this.isPercentage = isPercentage;
    }

    public BigDecimal applyDiscount(ArrayList<Item> sampleItemList) {
        BigDecimal result = BigDecimal.valueOf(0.00);
        if (!isPercentage) {
            result = amount;
        }
        for (Item item:sampleItemList) {
            if (isPercentage){
                result = result.add(item.price.multiply(amount));
            }
        }
        return result;
    }

    public static String createTable = "CREATE TABLE DISCOUNTS (ID varchar(40) NOT NULL,Amount decimal(18,2) NOT NULL,Name varchar(32672) NOT NULL,isPercentage boolean NOT NULL,PRIMARY KEY (ID))";

}
