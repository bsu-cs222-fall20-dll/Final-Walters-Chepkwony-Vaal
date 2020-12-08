package edu.bsu.cs222.pos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class ReceiptUI{
    private static final Label titleLabel = new Label("Company Name");
    private static final Label taxLabel = new Label("Tax:");
    private static final TextField taxInput = new TextField();
    private static final ToggleButton editTaxInput = new ToggleButton("Edit");
    private static final HBox taxField = new HBox(
            taxLabel,
            taxInput,
            editTaxInput);
    private static final Label balanceLabel = new Label("Balance:");
    private static final Label dateAndTimeLabel = new Label("Date and Time:");
    private static final Label thankLabel = new Label("Thank you for shopping at");
    private static final TableView<Item> receiptItemList = new TableView<>();
    private static final TableColumn<Item, Item> receiptNameColumn = new TableColumn<>("Item Name");
    private static final TableColumn<Item, Item> receiptPriceColumn = new TableColumn<>("Price");
    private static final ScrollPane receiptItemListScrollPane = new ScrollPane(receiptItemList);

    public static Stage popUp () {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Receipt");
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
        formatDisplay();
        primaryStage.setScene(new Scene(createRoot()));
        primaryStage.show();
        return primaryStage;
    }

    private static Pane createRoot () {
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


    private static void formatDisplay(){
        titleLabel.setMinWidth(400);
        titleLabel.setMinHeight(50);
        titleLabel.setTranslateY(5);
        thankLabel.setTranslateY(5);
        receiptNameColumn.setMinWidth(180);
        receiptPriceColumn.setMinWidth(180);
        receiptItemList.setMinWidth(300);
        receiptItemList.setTranslateX(10);
        titleLabel.setAlignment(Pos.CENTER);
        taxField.setAlignment(Pos.CENTER_LEFT);
        balanceLabel.setAlignment(Pos.CENTER_LEFT);
        thankLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(Font.font("Arial", 25));
        thankLabel.setFont(Font.font("Arial", 13));
        taxLabel.setFont(Font.font("Arial", 20));
        balanceLabel.setFont(Font.font("Arial", 20));
        dateAndTimeLabel.setFont(Font.font("Arial", 20));
        HBox.setMargin(dateAndTimeLabel, new Insets(10, 5, 10, 175));
        receiptItemList.getColumns().addAll(receiptNameColumn, receiptPriceColumn);
        receiptItemListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        receiptItemListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        receiptItemListScrollPane.setVvalue(.5);
        receiptItemListScrollPane.setHvalue(.5);
        receiptItemListScrollPane.setDisable(false);
    }

}