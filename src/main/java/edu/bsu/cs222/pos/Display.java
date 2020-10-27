package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
            private final TextField companyNameInput = new TextField("Enter Your Company Name Here");
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
            private final TableView itemList = new TableView<>();
            private final TableColumn nameColumn = new TableColumn("Item Name");
            private final TableColumn priceColumn = new TableColumn("Price");
            private final ScrollPane itemListScrollPane = new ScrollPane(itemList);

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
                        itemListScrollPane);
                return root;
            }

            private void formatDisplay(){
                titleLabel.setMinWidth(1000);
                companyNameInput.setMinWidth(400);
                itemsListHeader.setMinWidth(900);
                nameColumn.setMinWidth(450);
                priceColumn.setMinWidth(450);
                itemList.setMinWidth(900);
                itemListScrollPane.setPrefSize(900, 500);
                itemListScrollPane.setFitToHeight(true);
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

                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                itemList.getColumns().addAll(nameColumn, priceColumn);
                itemListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                itemListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                itemListScrollPane.setVvalue(.5);
                itemListScrollPane.setHvalue(.5);
                itemListScrollPane.setDisable(false);
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
                itemList.getItems().add(exampleItem);
            }
        }

    public static void main(String[] args) {
        Application.launch(DisplayView.class);
    }
}