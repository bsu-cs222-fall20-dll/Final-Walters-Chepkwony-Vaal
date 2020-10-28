package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class Display extends Application{
    private final Label titleLabel = new Label("Admin Panel");
        private final Label companyNameLabel = new Label("Company Name:"+"  ");
        private final TextField companyNameInput = new TextField("Enter Your Company Name Here");
        private final ToggleButton editCompanyName = new ToggleButton("Edit");
        private final HBox companyField = new HBox(
                companyNameLabel,
                companyNameInput,
                editCompanyName);
        private final Button addItem = new Button("Add new item");
        private final Label itemsListLabel = new Label("Current Items");
        private final HBox itemsListHeader = new HBox(
                itemsListLabel,
                addItem);
        private static final TableView<Object> itemList = new TableView<>();
        private final TableColumn<Object, Object> nameColumn = new TableColumn<>("Item Name");
        private final TableColumn<Object, Object> priceColumn = new TableColumn<>("Price");
        private final TableColumn<Object, Object> updateButtonColumn = new TableColumn<>("");
        private final ScrollPane itemListScrollPane = new ScrollPane(itemList);

        @Override
        public void start (Stage primaryStage){
            primaryStage.setWidth(1000);
            primaryStage.setHeight(600);
            formatDisplay();
            toggleEditOpacity();
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
            nameColumn.setMinWidth(300);
            priceColumn.setMinWidth(300);
            updateButtonColumn.setMinWidth(300);
            itemList.setMinWidth(900);
            itemListScrollPane.setMinWidth(900);
            titleLabel.setFont(Font.font("Arial", 16));
            companyNameLabel.setFont(Font.font("Arial", 15));
            companyNameInput.setFont(Font.font("Arial", 20));
            itemsListLabel.setFont(Font.font("Arial", 15));
            HBox.setMargin(companyNameLabel, new Insets(10, 20, 10, 140));
            HBox.setMargin(companyNameInput, new Insets(10, 20, 10, 20));
            HBox.setMargin(itemsListLabel, new Insets(15, 20, 10, 50));
            HBox.setMargin(addItem, new Insets(10, 20, 10, 690));
            itemList.setTranslateX(50);
            titleLabel.setAlignment(Pos.CENTER);
            companyField.setAlignment(Pos.CENTER_LEFT);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            itemList.getColumns().addAll(nameColumn, priceColumn, updateButtonColumn);
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

        public static void displayItems(Item item){
            System.out.println(item);
            Item exampleItem = new Item("Example", new BigDecimal("2.0"));
            Button update = new Button("Update");
            itemList.getItems().add(item);
            itemList.refresh();
        }
}