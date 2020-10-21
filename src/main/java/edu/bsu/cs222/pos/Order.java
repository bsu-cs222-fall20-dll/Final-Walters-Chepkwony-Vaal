package edu.bsu.cs222.pos;
import java.math.BigDecimal;
import java.util.HashMap;

public class Order {
    private HashMap<String, BigDecimal> itemList = new HashMap<>();

    public void addItem(Item item) {
    }

    public int getsize() {
        return 1;
    }

    public HashMap<String, BigDecimal> getItemList() {
        return null;
    }
}
