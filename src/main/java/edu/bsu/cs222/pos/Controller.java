package edu.bsu.cs222.pos;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Controller {
    public static Company company;

    public static void enterCompanyName(TextInputDialog opening, Button admin, Button cashier){
        admin.setDisable(true);
        cashier.setDisable(true);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                Optional<String> result = opening.showAndWait();
                result.ifPresent(name -> company = new Company(name) );
                admin.setDisable(false);
                cashier.setDisable(false);
            }
        });

    }

    public static void toAdmin(Button adminButton, Button cashierButton){
        adminButton.setOnMouseClicked(event -> {
            adminButton.setDisable(true);
            cashierButton.setDisable(true);
            Stage adminPanel = null;
            try {
                adminPanel = AdminPanelUI.popUp();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
            Stage adminPanel = null;
            try {
                adminPanel = AdminPanelUI.popUp();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            adminPanel.getScene().getWindow().setOnCloseRequest(closedEvent -> {
                cashierButton.setDisable(false);
                adminButton.setDisable(false);
            });
        });
    }

    public static void addItemsToDisplay() throws SQLException {
        HashMap<String, Item> inventoryList = company.getAvailableInventoryList();
        ArrayList<Item> inventoryArrayList = new ArrayList<>(inventoryList.values());
        AdminPanelUI.displayItems(inventoryArrayList);
    }

    public static void editCompanyName(TextField companyNameInput, ToggleButton editCompanyName){
        companyNameInput.setEditable(false);
        companyNameInput.setText(company.getCompanyName());
        companyNameInput.setOnMouseClicked(event -> companyNameInput.setEditable(true));
        companyNameInput.setOnAction(event -> {
            companyNameInput.setEditable(false);
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

    public static void doneEdit(Button doneButton, Stage stage, TextField nameInput, TextField priceInput, Item item){
        doneButton.setOnMouseClicked(event -> {
            item.setName(nameInput.getText());
            item.setPrice(BigDecimal.valueOf(Float.parseFloat(priceInput.getText())));
            stage.fireEvent(
                    new WindowEvent(
                            stage,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    )
            );
            try {
                addItemsToDisplay();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
    }
    public static void doneAddItem(Button doneButton, Stage stage, Label titleLabel, TextField nameInput, TextField priceInput){
        doneButton.setOnMouseClicked(event -> {
            BarcodeGenerator bcg = null;
            bcg = new BarcodeGenerator(company);
            Item item = new Item(nameInput.getText(),BigDecimal.valueOf(Float.parseFloat(priceInput.getText())));
            company.addItem(bcg.makeNewCode(), item);
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
            try {
                addItemsToDisplay();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

}
