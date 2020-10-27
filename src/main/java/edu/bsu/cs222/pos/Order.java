package edu.bsu.cs222.pos;

import java.util.ArrayList;

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
