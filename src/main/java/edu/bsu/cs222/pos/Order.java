package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    public String dateAndTime;
    private final ArrayList<Item> itemList = new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal totalWithTax;
    private BigDecimal taxRate = BigDecimal.valueOf(.07);
    private BigDecimal tax;

    public Order(){
        convertToDateAndTime();
        this.subtotal = BigDecimal.valueOf(0.00);
        this.totalWithTax = BigDecimal.valueOf(0.00);
    }

    public void addItem(Item item) {
        itemList.add(item);
        calculateSubtotal();
        calculateTotalWithTax();
    }

    private void calculateTotalWithTax() {
        calculateSubtotal();
        tax = subtotal.multiply(taxRate);
        totalWithTax =subtotal.add(tax);

    }

    private void calculateSubtotal() {
        subtotal = BigDecimal.valueOf(0.00);
        for(Item item: itemList){
            subtotal = subtotal.add(item.getPrice());
        }
    }

    public int getSize() {
        return itemList.size();
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void deleteItem(Item item) {
        itemList.remove(item);
        calculateSubtotal();
        calculateTotalWithTax();
    }

    public String getDateAndTime(){
        return dateAndTime;
    }

    public void convertToDateAndTime(){
        DateTimeFormatter dateAndTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateAndTime = dateAndTime.format(now);
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTotalWithTax() {
        return totalWithTax;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public int getQuantity(Item targetItem) {
        int quantity = 0;
        for(Item item: itemList){
            if (item == targetItem){
               quantity += 1;
            }
        }
        return quantity;
    }
}
