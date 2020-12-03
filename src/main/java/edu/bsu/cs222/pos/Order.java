package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
//Class is not fully implemented into the cashier UI yet
public class Order {
    public Long dateAndTime;
    private ArrayList<Item> itemList = new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal totalWithTax;
    private BigDecimal taxRate = BigDecimal.valueOf(.07);
    private BigDecimal tax;


    public Order(){
        Date date = new Date();
        this.dateAndTime = date.getTime();
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
    }

    public Long getDateAndTime(){
        return dateAndTime;
    }
    public BigDecimal getSubtotal() { return subtotal;
    }

    public BigDecimal getTotalWithTax() {
        return totalWithTax;
    }

//    public void setTotalWithTax(BigDecimal totalWithTax) {
//        this.totalWithTax = totalWithTax;
//    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

//    public BigDecimal getTaxRate() {
//        return taxRate;
//    }

    public BigDecimal getTax() {
        return tax;
    }
}
