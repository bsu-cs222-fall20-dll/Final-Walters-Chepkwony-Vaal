package edu.bsu.cs222.pos;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class CashierController {
    private static final Company company = AdminController.getCompany();
    private static Item selectedItem;
    //will make a button to initialize a new order; won't need to be final
    private static Order itemsInCart = new Order();

    public static void addSellableItemsToDisplay()  {
        HashMap<String, Item> inventoryList = company.getAvailableInventoryList();
        ArrayList<Item> inventoryArrayList = new ArrayList<>(inventoryList.values());
        CashierUI.displaySellableItems(inventoryArrayList);
    }

    public static void itemSearch(TextField barcodeSearch, Button searchButton, TextField itemInput, TextField priceInput){
        searchButton.setOnMouseClicked(event -> {
            String barcode = barcodeSearch.getText();
            selectedItem = company.getItemByID(barcode);
            itemInput.setText(selectedItem.name);
            priceInput.setText(selectedItem.price.toString());
        });
    }

    public static void addItemToCart(Button addItem, TextField subtotal, TextField tax, TextField total){
        addItem.setOnMouseClicked(event -> {
            if(!(selectedItem == null)) {
                itemsInCart.addItem(selectedItem);
                subtotal.setText(itemsInCart.getSubtotal().toString());
                tax.setText(itemsInCart.getTax().toString());
                total.setText(itemsInCart.getTotalWithTax().toString());
                CashierUI.displaySelectedItems(itemsInCart.getItemList());

            }
        });
    }


    public static void makeAnotherOrder(Button anotherOrder) {

    }
}
