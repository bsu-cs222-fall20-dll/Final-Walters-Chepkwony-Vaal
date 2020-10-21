package edu.bsu.cs222.pos;
import java.math.BigDecimal;

public class Item {
    public static BigDecimal getPrice() {
        return price;
    }

    public static void setPrice(BigDecimal price) {
        Item.price = price;
    }

    public static BigDecimal price;
    public String name;

    public Item(String itemName, BigDecimal itemPrice) {
        this.name = itemName;
        this.price = itemPrice;
    }
}
