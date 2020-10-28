package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.io.IOException;
import java.math.BigDecimal;

public class Controller {

    public static void editCompanyName(){
    }

    public static void addItemsToDisplay(){
        //TODO: loop through items to display
        Item exampleItem = new Item("Example", new BigDecimal("2.0"));
        AdminPanelUI.displayItems(exampleItem);
    }

    public static void toggleEditOpacity(TextField companyNameInput, ToggleButton editCompanyName){
        companyNameInput.setEditable(false);
        companyNameInput.setOnMouseClicked(event -> companyNameInput.setEditable(true));
        companyNameInput.setOnAction(event -> companyNameInput.setEditable(false));
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

    public static void setUpRowEdit(TableView<Item> itemList) throws IOException {
        itemList.setRowFactory( tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Item rowData = row.getItem();
                    Application.launch(SingleItemUI.class);
                    String name = rowData.getName();
                    String price = rowData.getPrice().toString();
                    SingleItemUI.changeTitleLabel("Edit Item");
                    SingleItemUI.setDefaultItemValues(name, price);
                }
            });
            return row ;
        });
    }
}
