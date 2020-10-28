package edu.bsu.cs222.pos;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.math.BigDecimal;

public class Controller {

    public static void editCompanyName(){
    }

    public static void addItemsToDisplay(){
        //TODO: loop through items to display
        Item exampleItem = new Item("Example", new BigDecimal("2.0"));
        Display.displayItems(exampleItem);
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
}
