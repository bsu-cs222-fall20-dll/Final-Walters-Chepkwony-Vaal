package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String dateAndTime;
    private ArrayList<Item> itemList = new ArrayList<>();
    private BigDecimal subtotal;

    public Order(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateAndTime = dtf.format(now);
    }

    public void addItem(Item item) {
        itemList.add(item);
        calculateSubtotal();
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

    public String getDateAndTime(){
        return dateAndTime;
    }
    public void calculateSubtotal(){
        subtotal = BigDecimal.valueOf(0);
        for(Item item: itemList){
            subtotal = subtotal.add(item.getPrice());
        }
    }


    public BigDecimal getSubtotal() {
        return subtotal;
    }


}
