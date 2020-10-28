package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    //TODO: how should we init the company?
    public static Company company = new Company("");

    public static void addItemsToDisplay(){
        HashMap<String, Item> inventoryList = company.getAvailableInventoryList();
//        Item exampleItem = new Item("Example", new BigDecimal("2.0"));
//        HashMap<String, Item> inventoryList = new HashMap<>();
//        inventoryList.put("10",exampleItem);
        ArrayList<Item> inventoryArrayList = new ArrayList<>(inventoryList.values());
        AdminPanelUI.displayItems(inventoryArrayList);
    }

    public static void editCompanyName(TextField companyNameInput, ToggleButton editCompanyName){
        companyNameInput.setEditable(false);
        companyNameInput.setOnMouseClicked(event -> companyNameInput.setEditable(true));
        companyNameInput.setOnAction(event -> {
            companyNameInput.setEditable(false);
            //TODO: Set company name + error handling
            //Company.setCompanyName(companyNameInput.getText());
        });
        companyNameInput.editableProperty().bindBidirectional(editCompanyName.selectedProperty());
        editCompanyName.setOnAction(event -> {
            if (editCompanyName.isSelected()) {
                companyNameInput.requestFocus();
            }
        });
        companyNameInput.styleProperty().bind(
                Bindings.when(
                        companyNameInput.editableProperty())
                        .then((String) null)
                        .otherwise("-fx-background-color: transparent;")
        );
        companyNameInput.alignmentProperty().bind(
                Bindings.when(
                        companyNameInput.editableProperty())
                        .then(Pos.CENTER_LEFT)
                        .otherwise(Pos.CENTER)
        );
    }

    public static void editRow(TableView<Item> itemList, Button addItem) throws IOException {
        itemList.setRowFactory( tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    itemList.setDisable(true);
                    addItem.setDisable(true);
                    Item rowData = row.getItem();
                    Stage stage = SingleItemUI.popUp();
                    String name = rowData.getName();
                    String price = rowData.getPrice().toString();
                    SingleItemUI.changeTitleLabel("Edit Item");
                    SingleItemUI.setDefaultItemValues(name, price);
                    stage.getScene().getWindow().setOnCloseRequest(closedEvent -> {
                        itemList.setDisable(false);
                        addItem.setDisable(false);
                    });
                }
            });
            return row ;
        });
    }

    public static void addItem(Button addItem, TableView<Item> itemList) throws IOException{
        addItem.setOnMouseClicked(event -> {
            addItem.setDisable(true);
            itemList.setDisable(true);
            Stage stage = SingleItemUI.popUp();
            SingleItemUI.changeTitleLabel("Add Item");
            SingleItemUI.setDefaultItemValues("", "");
            stage.getScene().getWindow().setOnCloseRequest(closedEvent -> {
                addItem.setDisable(false);
                itemList.setDisable(false);
            });
        });
    }

    public static void doneEdit(Button doneButton, Stage stage, Label titleLabel, TextField nameInput, TextField priceInput){
        doneButton.setOnMouseClicked(event -> {
            //TODO: Add/Update items + error handling
            BarcodeGenerator bcg = new BarcodeGenerator(company.getAvailableInventoryList());
            Item item = new Item(nameInput.getText(),BigDecimal.valueOf(Float.parseFloat(priceInput.getText())));
            company.addItem(bcg.makeNewCode(),item);
//            Item item = company.searchByItemName(nameInput.getText());
            if (titleLabel.getText().equals("Add Item")){
                System.out.println("add");
            }
            else if(titleLabel.getText().equals("Edit Item")){
                System.out.println("edit");
            }
            else{
                System.out.println("error");
            }
            stage.fireEvent(
                    new WindowEvent(
                            stage,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    )
            );
            addItemsToDisplay();
        });
    }

}
