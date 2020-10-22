package edu.bsu.cs222.pos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private ArrayList<Item> itemList = new ArrayList<>();

    public void addItem(Item item) {
        itemList.add(item);
    }

    public int getSize() {
        return itemList.size();
    }

    public ArrayList<Item> getItemList() {
        return null;
    }
}
