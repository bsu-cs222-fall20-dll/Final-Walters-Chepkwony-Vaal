package edu.bsu.cs222.pos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CashierUI{
    private static final Label titleLabel = new Label("Cashier Panel");
    private  static final Label errorLabel = new Label("");
    private static final Label barcodeAndItemsLabel = new Label("Barcode And Items:");
    private static final Label selectedItemLabel = new Label("Selected Items:");
    private static final TextField selectedItemInput = new TextField();
    private static final Label priceLabel = new Label("Price:");
    private static final TextField priceInput = new TextField();
    private static final Label qualityLabel = new Label("Quality:");
    private static final TextField qualityInput = new TextField();
    private static final Label ReceiptLabel = new Label("Receipt:");
    private static final TableView <String> barcodeAndItems = new TableView<>();
    private static final TableView<Item> receiptItemList = new TableView<>();
    private static final Label codeForTheMiddleLabel = new Label();
    private static final HBox LittleTitleField = new HBox(
            barcodeAndItemsLabel,
            selectedItemLabel,
            ReceiptLabel);
    private static final HBox codeField = new HBox(
            barcodeAndItems,
            receiptItemList,
            codeForTheMiddleLabel);
    /*private static final HBox priceAndQualityLabelField = new HBox(
            priceLabel,
            qualityLabel
            );
    private static final HBox priceAndQualityInputField = new HBox(
            priceInput,
            qualityInput
    );*/
    private static final TableColumn <String,String> barcodeColumn = new TableColumn<>("Barcode");
    private static final TableColumn <String,String> itemColumn = new TableColumn<>("Items");
    private static final TableColumn<Item, Item> receiptNameColumn = new TableColumn<>("Item Name");
    private static final TableColumn<Item, Item> receiptPriceColumn = new TableColumn<>("Price");
    private static final ScrollPane barcodeAndItemsPane = new ScrollPane(barcodeAndItems);
    private static final ScrollPane receiptItemListScrollPane = new ScrollPane(receiptItemList);

    public static Stage popUp () throws SQLException {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Cashier Access");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
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
                LittleTitleField,
                codeField
        );
        return root;
    }

    private static void formatDisplay(){
        titleLabel.setMinWidth(1000);
        titleLabel.setMinHeight(10);
        errorLabel.setMinWidth(1000);
        errorLabel.setMinHeight(10);
        errorLabel.setTextFill(Color.RED);
        barcodeColumn.setMinWidth(150);
        itemColumn.setMinWidth(150);
        receiptNameColumn.setMinWidth(120);
        receiptPriceColumn.setMinWidth(120);
        barcodeAndItemsPane.setMinWidth(300);
        barcodeAndItems.setMinWidth(300);
        receiptItemList.setMaxWidth(300);
        receiptItemListScrollPane.setMinWidth(300);
        barcodeAndItemsLabel.setFont(Font.font("Arial", 15));
        selectedItemLabel.setFont(Font.font("Arial", 15));
        ReceiptLabel.setFont(Font.font("Arial", 15));
        errorLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
        titleLabel.setFont(Font.font("Arial", 25));
        titleLabel.setTranslateY(5);
        errorLabel.setTranslateY(5);
        titleLabel.setAlignment(Pos.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(barcodeAndItemsLabel, new Insets(1, 150, 1, 10));
        HBox.setMargin(selectedItemLabel, new Insets(2, 100, 1, 50));
        HBox.setMargin(ReceiptLabel, new Insets(1, 10, 1, 200));
        HBox.setMargin(barcodeAndItems, new Insets(1, 150, 1, 10));
        //HBox.setMargin(selectedItemLabel, new Insets(12, 100, 5, 50));
        HBox.setMargin(receiptItemList, new Insets(1, 5, 1, 280));
        HBox.setMargin(priceLabel, new Insets(30, 10, 10, 700));
        HBox.setMargin(qualityLabel, new Insets(30, 10, 10, 700));
        HBox.setMargin(priceInput, new Insets(50, 10, 10, 700));
        HBox.setMargin(qualityInput, new Insets(50, 10, 10, 700));
        barcodeAndItems.setPlaceholder(new Label("No items and barcode"));
        receiptItemList.setPlaceholder(new Label("No items"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("Barcode"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("Items"));
        receiptNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        receiptPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        barcodeAndItems.getColumns().clear();
        receiptItemList.getColumns().clear();
        barcodeAndItems.getColumns().addAll(barcodeColumn, itemColumn);
        receiptItemList.getColumns().addAll(receiptNameColumn, receiptPriceColumn);
        barcodeAndItemsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        barcodeAndItemsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        barcodeAndItemsPane.setVvalue(.5);
        barcodeAndItemsPane.setHvalue(.5);
        barcodeAndItemsPane.setDisable(false);
        receiptItemListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        receiptItemListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        receiptItemListScrollPane.setVvalue(.5);
        receiptItemListScrollPane.setHvalue(.5);
        receiptItemListScrollPane.setDisable(false);


    }


}
