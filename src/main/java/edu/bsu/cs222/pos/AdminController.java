package edu.bsu.cs222.pos;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class AdminController {
    private static Company company;

    public static void enterCompanyName(TextInputDialog opening, Button admin, Button cashier){
        admin.setDisable(true);
        cashier.setDisable(true);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                Optional<String> result = opening.showAndWait();
                result.ifPresent(name -> company = new Company(name) );
                if(company != null) {
                    admin.setDisable(false);
                    cashier.setDisable(false);
                }
                else{
                    run();
                }
            }
        });

    }

    public static void toAdmin(Button adminButton, Button cashierButton){
        adminButton.setOnMouseClicked(event -> {
            adminButton.setDisable(true);
            cashierButton.setDisable(true);
            Stage  adminPanel = AdminPanelUI.popUp();
            adminPanel.getScene().getWindow().setOnCloseRequest(closedEvent -> {
                adminButton.setDisable(false);
                cashierButton.setDisable(false);
            });
        });
    }

    public static void toCashier(Button cashierButton, Button adminButton){
        cashierButton.setOnMouseClicked(event -> {
            cashierButton.setDisable(true);
            adminButton.setDisable(true);
            Stage cashierPanel = CashierUI.popUp();
            cashierPanel.getScene().getWindow().setOnCloseRequest(closedEvent -> {
                cashierButton.setDisable(false);
                adminButton.setDisable(false);
            });
        });
    }

    public static void addItemsToDisplay()  {
        HashMap<String, Item> inventoryList = company.getAvailableInventoryList();
        ArrayList<Item> inventoryArrayList = new ArrayList<>(inventoryList.values());
        AdminPanelUI.displayItems(inventoryArrayList);
    }

    public static void editCompanyName(TextField companyNameInput, ToggleButton editCompanyName){
        companyNameInput.setEditable(false);
        companyNameInput.setText(company.getCompanyName());
        companyNameInput.setOnMouseClicked(event -> companyNameInput.setEditable(true));
        companyNameInput.setOnAction(event ->
            companyNameInput.setEditable(false));
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

    public static void editRow(TableView<Item> itemList, Button addItem){
        itemList.setRowFactory( tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    itemList.setDisable(true);
                    addItem.setDisable(true);
                    Item rowData = row.getItem();
                    Stage stage = ModifyItemUI.popUp(rowData);
                    String name = rowData.getName();
                    String price = rowData.getPrice().toString();
                    ModifyItemUI.changeTitleLabel("Edit Item");
                    ModifyItemUI.setDefaultItemValues(name, price);
                    stage.getScene().getWindow().setOnCloseRequest(closedEvent -> {
                        itemList.setDisable(false);
                        addItem.setDisable(false);
                    });
                }
            });
            return row ;
        });
    }




    public static void addItem(Button addItem, TableView<Item> itemList){
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

    public static void doneEdit(Button doneButton, Button deleteButton, Stage stage, TextField nameInput, TextField priceInput, Item item){
        doneButton.setOnMouseClicked(event -> {
            company.updateItemName(item.getBarcode(), nameInput.getText());
            company.updateItemCost(item.getBarcode(), new BigDecimal(priceInput.getText()));
            stage.fireEvent(
                    new WindowEvent(
                            stage,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    )
            );
            addItemsToDisplay();
        });
        deleteButton.setOnMouseClicked(event -> {
            company.removeItem(item.getBarcode());
            stage.fireEvent(
                    new WindowEvent(
                            stage,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    )
            );
            addItemsToDisplay();
        });
    }
    public static void doneAddItem(Button doneButton, Stage stage, Label titleLabel, TextField nameInput, TextField priceInput){
        doneButton.setOnMouseClicked(event -> {
            try{
            Item item = new Item(nameInput.getText(),BigDecimal.valueOf(Float.parseFloat(priceInput.getText())));
            company.addItem(company.generateBarcode(), item);
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
            addItemsToDisplay();}
            catch (Exception e){
                System.out.println("not a number");
            }
        });
    }

    public static Company getCompany() {
        return company;
    }
}
