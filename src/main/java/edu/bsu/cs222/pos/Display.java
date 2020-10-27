package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class Display {

    //I had to nest this because my computer is dumb. I'll make it the main class when I'm done.
    public static class DisplayView extends Application
        {
            private final Label titleLabel = new Label("Admin Panel");
            private final Label companyNameLabel = new Label("Company Name:"+"  ");
            private final TextField companyNameInput = new TextField("Company");
            private final ToggleButton editCompanyName = new ToggleButton("Edit");
            private final HBox companyField = new HBox(
                    companyNameLabel,
                    companyNameInput,
                    editCompanyName);
            private final Button addItem = new Button("+");
            private final Label itemsListLabel = new Label("Items");
            private final HBox itemsListHeader = new HBox(
                    itemsListLabel,
                    addItem);
            private final ScrollPane itemsList = new ScrollPane();
            private final Pane itemsListPane = new Pane();

            @Override
            public void start (Stage primaryStage){
                primaryStage.setWidth(1000);
                primaryStage.setHeight(600);
                formatDisplay();
                toggleEditOpacity();
                displayItems();
                primaryStage.setScene(new Scene(createRoot()));
                primaryStage.show();
            }

            private Pane createRoot () {
                VBox root = new VBox();
                root.getChildren().addAll(
                        titleLabel,
                        companyField,
                        itemsListHeader,
                        itemsList);
                return root;
            }

            private void formatDisplay(){
                titleLabel.setMinWidth(1000);
                companyNameInput.setMinWidth(400);
                titleLabel.setFont(Font.font("Arial", 16));
                companyNameLabel.setFont(Font.font("Arial", 15));
                companyNameInput.setFont(Font.font("Arial", 20));
                itemsListLabel.setFont(Font.font("Arial", 15));
                companyField.setMargin(companyNameLabel, new Insets(20, 20, 20, 140));
                companyField.setMargin(companyNameInput, new Insets(20, 20, 20, 20));
                titleLabel.setAlignment(Pos.CENTER);
                companyField.setAlignment(Pos.CENTER_LEFT);
                itemsListLabel.setAlignment(Pos.CENTER_LEFT);
                addItem.setAlignment(Pos.CENTER_RIGHT);
                itemsListHeader.setMinWidth(900);
                itemsList.setMinHeight(500);
                itemsList.setMinWidth(900);
            }

            private void toggleEditOpacity(){
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

            private void displayItems(){
                Item exampleItem = new Item("Example", new BigDecimal(2.0));
                HBox itemBox = itemToHBox(exampleItem);
                itemsListPane.getChildren().addAll(itemBox);
            }

            private HBox itemToHBox(Item item){
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
        }

    public static void main(String[] args) {
        Application.launch(DisplayView.class);
    }
}