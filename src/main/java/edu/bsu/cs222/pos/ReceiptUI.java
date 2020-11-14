package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ReceiptUI extends Application{
    private final Label titleLabel = new Label("Company Name");
    private final Label taxLabel = new Label("Tax:");
    private final TextField taxInput = new TextField();
    private final ToggleButton editTaxInput = new ToggleButton("Edit");
    private final HBox taxField = new HBox(
            taxLabel,
            taxInput,
            editTaxInput);
    private final Label balanceLabel = new Label("Balance:");
    private final Label dateAndTimeLabel = new Label("Date and Time:");
    private final Label thankLabel = new Label("Thank you for shopping at");
    private static final TableView<Item> receiptItemList = new TableView<>();
    private final TableColumn<Item, Item> receiptNameColumn = new TableColumn<>("Item Name");
    private final TableColumn<Item, Item> receiptPriceColumn = new TableColumn<>("Price");
    private final ScrollPane receiptItemListScrollPane = new ScrollPane(receiptItemList);

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setTitle("Receipt");
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
        formatDisplay();
        primaryStage.setScene(new Scene(createRoot()));
        primaryStage.show();
    }

    private Pane createRoot () {
        VBox root = new VBox();
        root.getChildren().addAll(
                titleLabel,
                receiptItemListScrollPane,
                taxField,
                balanceLabel,
                dateAndTimeLabel,
                thankLabel
        );
        return root;
    }

    @SuppressWarnings("unchecked")
    private void formatDisplay(){
        titleLabel.setMinWidth(400);
        titleLabel.setMinHeight(50);
        titleLabel.setFont(Font.font("Arial", 25));
        titleLabel.setTranslateY(5);
        titleLabel.setAlignment(Pos.CENTER);
        thankLabel.setFont(Font.font("Arial", 13));
        thankLabel.setTranslateY(5);
        thankLabel.setAlignment(Pos.CENTER);
        receiptNameColumn.setMinWidth(180);
        receiptPriceColumn.setMinWidth(180);
        receiptItemList.setMinWidth(300);
        receiptItemList.setTranslateX(10);
        receiptItemList.getColumns().addAll(receiptNameColumn, receiptPriceColumn);
        taxLabel.setFont(Font.font("Arial", 20));
        taxField.setAlignment(Pos.CENTER_LEFT);
        balanceLabel.setFont(Font.font("Arial", 20));
        balanceLabel.setAlignment(Pos.CENTER_LEFT);
        dateAndTimeLabel.setFont(Font.font("Arial", 20));
        HBox.setMargin(dateAndTimeLabel, new Insets(10, 5, 10, 175));
        receiptItemListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        receiptItemListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        receiptItemListScrollPane.setVvalue(.5);
        receiptItemListScrollPane.setHvalue(.5);
        receiptItemListScrollPane.setDisable(false);
    }

    public static void displayItem(ArrayList<Item> data){
        ObservableList<Item> observableData = FXCollections.observableList(data);
        receiptItemList.setItems(observableData);
        receiptItemList.refresh();
    }

}