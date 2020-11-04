package edu.bsu.cs222.pos;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Item {
    private final ArrayList<BigDecimal> priceList = new ArrayList<>();

    public  BigDecimal getPrice() {
        return price;
    }
    public  BigDecimal price;
    public String name;

    public Item(String itemName, BigDecimal itemPrice) {
        this.name = itemName;
        this.price = itemPrice;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItemPrice(){
        priceList.add(price);
    }

    public static String createTable = "CREATE TABLE ITEMS ("+
            "ID varchar(40) NOT NULL,"+
            "Price decimal(18,2) NOT NULL,"+
            "Name varchar(32672) NOT NULL,"+
            "Image varchar(32672),"+
            "PRIMARY KEY (ID)" +
            ")";

}
