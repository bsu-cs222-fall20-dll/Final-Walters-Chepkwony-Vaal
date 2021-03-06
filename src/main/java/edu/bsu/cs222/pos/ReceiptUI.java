package edu.bsu.cs222.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReceiptUI {
    private static final TableView<Item> receiptItemList = new TableView<>();
    private static final TableColumn<Item, Item> receiptNameColumn = new TableColumn<>("Item Name");
    private static final TableColumn<Item, Item> receiptPriceColumn = new TableColumn<>("Price");
    private static final TableColumn<Item, Item> quantityColumn = new TableColumn<>("Quantity");
    private static final Label subtotalLabel = new Label("Subtotal:");
    private static final TextField subtotalInput = new TextField();
    private static final Label taxLabel = new Label("Tax:");
    private static final TextField taxInput = new TextField();
    private static final Label totalLabel = new Label("Total:");
    private static final TextField totalInput = new TextField();
    private static final Label dateAndTimeLabel = new Label("Date and Time:");
    private static final TextField dateAndTimeInput = new TextField();
    private static final Label thankYouLabel = new Label("Thank you for shopping!");
    private static final ScrollPane receiptItemListScrollPane = new ScrollPane(receiptItemList);

    private static final HBox subtotalField = new HBox(
            subtotalLabel,
            subtotalInput
    );
    private static final HBox taxField = new HBox(
            taxLabel,
            taxInput);
    private static final HBox totalField = new HBox(
            totalLabel,
            totalInput);
    private static final HBox dateAndTimeField = new HBox(
            dateAndTimeLabel,
            dateAndTimeInput);

    public static Stage popUp () {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Receipt");
        primaryStage.setWidth(390);
        primaryStage.setHeight(650);
        CashierController.addItemsInCartToDisplay(subtotalInput,taxInput,totalInput,dateAndTimeInput);
        formatDisplay();
        primaryStage.setScene(new Scene(createRoot()));
        primaryStage.show();
        return primaryStage;
    }

    private static Pane createRoot () {
        VBox root = new VBox();
        root.getChildren().addAll(
                receiptItemList,
                subtotalField,
                taxField,
                totalField,
                dateAndTimeField,
                thankYouLabel
        );
        return root;
    }

    @SuppressWarnings("unchecked")
    private static void formatDisplay(){
        thankYouLabel.setMinWidth(400);
        thankYouLabel.setMinHeight(50);
        quantityColumn.setMinWidth(55);
        receiptNameColumn.setMinWidth(170);
        receiptPriceColumn.setMinWidth(135);
        thankYouLabel.setAlignment(Pos.CENTER);
        receiptItemListScrollPane.setMinHeight(390);
        thankYouLabel.setFont(Font.font("Arial", 15));
        HBox.setMargin(subtotalInput, new Insets(1, 5, 1, 35));
        HBox.setMargin(taxInput, new Insets(1, 5, 1, 65));
        HBox.setMargin(totalInput, new Insets(1, 5, 1, 55));
        receiptNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        receiptPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        receiptItemList.getColumns().clear();
        receiptItemList.setPlaceholder(new Label("No items"));
        receiptItemList.getColumns().addAll(receiptNameColumn, receiptPriceColumn,quantityColumn);
        receiptItemListScrollPane.setVvalue(.5);
        receiptItemListScrollPane.setHvalue(.5);
        receiptItemListScrollPane.setDisable(false);
        receiptItemList.setDisable(true);
        subtotalInput.setDisable(true);
        taxInput.setDisable(true);
        totalInput.setDisable(true);
        dateAndTimeInput.setDisable(true);
        subtotalInput.setStyle("-fx-background-color: transparent;");
        taxInput.setStyle("-fx-background-color: transparent;");
        totalInput.setStyle("-fx-background-color: transparent;");
        dateAndTimeInput.setStyle("-fx-background-color: transparent;");
    }

    public static void displayItemsInCart(ArrayList<Item> data){
        ObservableList<Item> observableData = FXCollections.observableList(data);
        receiptItemList.setItems(observableData);
        receiptItemList.refresh();
    }

}

