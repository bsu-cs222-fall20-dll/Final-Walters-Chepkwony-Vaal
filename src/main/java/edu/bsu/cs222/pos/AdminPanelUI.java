package edu.bsu.cs222.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;

public class AdminPanelUI{
    private static final Label titleLabel = new Label("Admin Panel");
    private static final Label errorLabel = new Label("");
    private static final Label companyNameLabel = new Label("Company Name:");
    private static final TextField companyNameInput = new TextField();
    private static final ToggleButton editCompanyName = new ToggleButton("Edit");
    private static final HBox companyField = new HBox(
            companyNameLabel,
            companyNameInput,
            editCompanyName);
    private static final Button addItem = new Button("Add new item");
    private static final Label itemsListLabel = new Label("Current Items");
    private static final HBox itemsListHeader = new HBox(
            itemsListLabel,
            addItem);
    private static final TableView<Item> itemList = new TableView<>();
    private static final TableColumn<Item, Item> nameColumn = new TableColumn<>("Item Name");
    private static final TableColumn<Item, Item> priceColumn = new TableColumn<>("Price");
    private static final ScrollPane itemListScrollPane = new ScrollPane(itemList);

    public static Stage popUp () {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Administrative Access");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        Controller.addItemsToDisplay();
        companyNameInput.setText("Edit Company Name");
        Controller.editCompanyName(companyNameInput, editCompanyName);
        Controller.editRow(itemList, addItem);
        Controller.addItem(addItem, itemList);
        formatDisplay();
        primaryStage.setScene(new Scene(createRoot()));
        primaryStage.show();
        return primaryStage;
    }

    private static Pane createRoot() {
            VBox root = new VBox();
            root.getChildren().addAll(
                    titleLabel,
                    errorLabel,
                    companyField,
                    itemsListHeader,
                    itemListScrollPane);
            return root;
        }

    @SuppressWarnings("unchecked")
    private static void formatDisplay(){
        titleLabel.setMinWidth(1000);
        titleLabel.setMinHeight(20);
        errorLabel.setMinWidth(1000);
        errorLabel.setMinHeight(10);
        companyNameInput.setMinWidth(400);
        itemsListHeader.setMinWidth(900);
        nameColumn.setMinWidth(450);
        priceColumn.setMinWidth(450);
        itemList.setMinWidth(900);
        itemListScrollPane.setMinWidth(900);
        titleLabel.setFont(Font.font("Arial", 20));
        companyNameLabel.setFont(Font.font("Arial", 15));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
        companyNameInput.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        itemsListLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 15));
        HBox.setMargin(companyNameLabel, new Insets(10, 5, 10, 175));
        HBox.setMargin(companyNameInput, new Insets(10, 20, 10, 0));
        HBox.setMargin(itemsListLabel, new Insets(15, 20, 10, 50));
        HBox.setMargin(addItem, new Insets(10, 20, 10, 680));
        titleLabel.setTranslateY(5);
        errorLabel.setTranslateY(5);
        itemList.setTranslateX(50);
        titleLabel.setAlignment(Pos.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        companyField.setAlignment(Pos.CENTER_LEFT);
        itemList.setPlaceholder(new Label("No items"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemList.getColumns().clear();
        itemList.getColumns().addAll(nameColumn, priceColumn);
        itemListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        itemListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        itemListScrollPane.setVvalue(.5);
        itemListScrollPane.setHvalue(.5);
        itemListScrollPane.setDisable(false);
    }

    public static void displayItems(ArrayList<Item> data){
        ObservableList<Item> observableData = FXCollections.observableList(data);
        itemList.setItems(observableData);
        itemList.refresh();
    }
}