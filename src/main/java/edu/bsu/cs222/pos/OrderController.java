package edu.bsu.cs222.pos;

import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderController {
    private static Company company = AdminController.getCompany();

    public static void addItemsToDisplay()  {
        HashMap<String, Item> inventoryList = company.getAvailableInventoryList();
        ArrayList<Item> inventoryArrayList = new ArrayList<>(inventoryList.values());
        AdminPanelUI.displayItems(inventoryArrayList);
    }

    public static void selectCashierItems(TableView<Item> barcodeAndItems, Button addItem){
        barcodeAndItems.setRowFactory( tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    barcodeAndItems.setDisable(true);
                    addItem.setDisable(true);
                    Item rowData = row.getItem();
                    String price = rowData.getPrice().toString();
                        barcodeAndItems.setDisable(false);
                        addItem.setDisable(false);
                }
            });
            return row ;
        });
    }






}
