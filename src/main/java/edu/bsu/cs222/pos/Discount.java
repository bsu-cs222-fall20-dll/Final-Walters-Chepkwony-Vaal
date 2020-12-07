package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Discount {
    private String barcode;
    private String name;
    private BigDecimal discount;
    private Boolean percentage;
    public Discount(String barcode, String name, BigDecimal discount, Boolean percentage) {
        this.barcode = barcode;
        this.name = name;
        this.discount = discount;
        this.percentage = percentage;
    }

    public BigDecimal applyDiscount(ArrayList<Item> sampleItemList) {
        BigDecimal result = BigDecimal.valueOf(0.00);
        if (!percentage) {
            /*
               I've intentionally not returned at this stage.
               We might do more validation once we have the subtotal.
             */
            result = discount;
        }
        for (Item item:sampleItemList) {
            if (percentage){
                result = result.add(item.price.multiply(discount));
            }
        }
        return result;
    }

    public static String createTable = "CREATE TABLE DISCOUNTS (ID varchar(40) NOT NULL,Discount decimal(18,2) NOT NULL,Name varchar(32672) NOT NULL,Percentage bool NOT NULL,PRIMARY KEY (ID))";

}
