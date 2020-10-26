package edu.bsu.cs222.pos;

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.HashMap;

public class Display {

    //I had to nest this because my computer is dumb. I'll make it the main class when I'm done.
    public static class DisplayView extends Application
        {
            private Label titleLabel = new Label("Admin Panel");
            private Label companyNameLabel = new Label("Company Name:");
            private TextField companyNameInput = new TextField("Company");
            private ToggleButton editCompanyName = new ToggleButton("Edit");
            private Button addItem = new Button("+");
            private Label itemsListLabel = new Label("Items");
            private HBox itemList = new HBox();

            @Override
            public void start (Stage primaryStage){
                primaryStage.setWidth(1000);
                primaryStage.setHeight(800);
                titleLabel.setMinWidth(1000);
                companyNameInput.setMinWidth(400);
                titleLabel.setFont(Font.font("Arial", 16));
                companyNameLabel.setFont(Font.font("Arial", 15));
                companyNameInput.setFont(Font.font("Arial", 20));
                titleLabel.setAlignment(Pos.CENTER);
                companyNameLabel.setAlignment(Pos.CENTER);
                companyNameInput.setAlignment(Pos.CENTER);
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
                Item exampleItem = new Item("Example", new BigDecimal(2.0));
                HBox itemBox = ItemToHBox(exampleItem);
                itemList.getChildren().addAll(itemBox);
                primaryStage.setScene(new Scene(createRoot()));
                primaryStage.show();
            }

            private HBox ItemToHBox(Item item){
                Label itemNameLabel = new Label("Name:");
                Label itemName = new Label(item.getName());
                Label itemPriceLabel = new Label("Price:");
                Label itemPrice = new Label(item.getPrice().toString());
                return new HBox(
                        itemNameLabel,
                        itemName,
                        itemPriceLabel,
                        itemPrice);
            }

            private Pane createRoot () {
                VBox root = new VBox();
                HBox companyField = new HBox(
                        companyNameLabel,
                        companyNameInput,
                        editCompanyName);
                companyField.setAlignment(Pos.CENTER);
                HBox items = new HBox(
                        itemsListLabel,
                        addItem
                );
                items.setAlignment(Pos.CENTER);
                items.setMinWidth(900);
                root.getChildren().addAll(
                        titleLabel,
                        companyField,
                        items);
                return root;
            }
        }

    public static void main(String[] args) {
        Application.launch(DisplayView.class);
    }
}