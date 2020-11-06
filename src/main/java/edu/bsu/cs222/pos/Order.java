package edu.bsu.cs222.pos;

import java.util.ArrayList;

public class Order {
    public String dateAndTime;
    private ArrayList<Item> itemList = new ArrayList<>();

    public Order(String DateAndTime){
        this.dateAndTime = DateAndTime;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public int getSize() {
        return itemList.size();
    }

    public ArrayList<Item> getItemList() {
        return null;
    }

    public void deleteItem(Item item) {
        itemList.remove(item);
    }

    public String getDataAndTime(){
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

}
