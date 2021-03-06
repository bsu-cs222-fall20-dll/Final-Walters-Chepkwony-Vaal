package edu.bsu.cs222.pos;
import java.math.BigDecimal;

public class Item {
    public  BigDecimal price;
    public String name;
    private final String barcode;

    public Item(String itemName, BigDecimal itemPrice, String barcode){
        this.name = itemName;
        this.price = itemPrice;
        this.barcode = barcode;
    }

    public Item(String itemName, BigDecimal itemPrice) {
        this.name = itemName;
        this.price = itemPrice;
        this.barcode = "";
    }

    public  BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getBarcode() {
        return barcode;
    }


    public static String createTable = "CREATE TABLE ITEMS (ID varchar(40) NOT NULL,Price decimal(18,2) NOT NULL,Name varchar(32672) NOT NULL,Image varchar(32672),PRIMARY KEY (ID))";

}
