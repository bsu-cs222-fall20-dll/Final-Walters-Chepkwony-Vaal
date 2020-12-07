package edu.bsu.cs222.pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ArrayList;


public class CashierUI{
    private static final Label titleLabel = new Label("Cashier Panel");
    private  static final Label errorLabel = new Label("");
    private static final Label barcodeAndItemsLabel = new Label("Items");
    private static final Label barcodeSearchLabel = new Label("Barcode Search");
    private static final TextField barcodeSearchField = new TextField();
    private static final Button barcodeSearchButton = new Button();
    private static final Label itemNameSearchLabel = new Label("Item Name Search");
    private static final TextField itemNameSearchField = new TextField();
    private static final Button itemNameSearchButton = new Button();
    private static final Label selectedItemLabel = new Label("Selected Item");
    private static final TextField selectedItemInput = new TextField();
    private static final Label priceLabel = new Label("Selected Item Price");
    private static final TextField priceInput = new TextField();
    private static final Button addItemButton = new Button("Add To Cart");
    private static final Button resetButton = new Button("Reset");
    private static final Button checkoutButton = new Button("Checkout");
    private static final Label ReceiptLabel = new Label("Receipt");
    private static final Label subtotalLabel = new Label("Subtotal");
    private static final TextField subtotalInput = new TextField();
    private static final Label taxLabel = new Label("Tax");
    private static final TextField taxInput = new TextField();
    private static final Label totalLabel = new Label("Total");
    private static final TextField totalInput = new TextField();
    private static final Label dateAndTimeLabel = new Label("Date and Time:");
    private static final TextField dateAndTimeInput = new TextField();
    private static final TableView <Item> barcodeAndItems = new TableView<>();
    private static final TableView<Item> receiptItemList = new TableView<>();
    private static final HBox barcodeHBox = new HBox(barcodeSearchField,barcodeSearchButton);
    private static final Image img = new Image("search_icon.png",20,10,true,true);
    public static final ImageView searchView = new ImageView(img);
    public static final ImageView searchView2 = new ImageView(img);
    private static final HBox itemNameSearchHBox = new HBox(itemNameSearchField,itemNameSearchButton);
    private static final VBox codeForTheMiddleLabel = new VBox(
            barcodeSearchLabel,
            barcodeHBox,
            itemNameSearchLabel,
            itemNameSearchHBox,
            selectedItemLabel,
            selectedItemInput,
            priceLabel,
            priceInput,
            addItemButton,
            resetButton
    );
    private static final HBox LittleTitleField = new HBox(
            barcodeAndItemsLabel,
            ReceiptLabel);

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
    private static final HBox anotherButtonField = new HBox(
            checkoutButton
    );
    private static final VBox receiptBottomField = new VBox(
            subtotalField,
            taxField,
            totalField,
            dateAndTimeField,
            anotherButtonField
    );
    private static final HBox codeField = new HBox(
            barcodeAndItems,
            codeForTheMiddleLabel,
            receiptItemList
            );
    private static final TableColumn <Item, Item> barcodeColumn = new TableColumn<>("Barcode");
    private static final TableColumn <Item ,Item> itemColumn = new TableColumn<>("Items");
    private static final TableColumn <Item ,Item> priceColumn = new TableColumn<>("Price");
    private static final TableColumn<Item, Item> receiptNameColumn = new TableColumn<>("Item Name");
    private static final TableColumn<Item, Item> receiptPriceColumn = new TableColumn<>("Price");
    private static final ScrollPane barcodeAndItemsPane = new ScrollPane(barcodeAndItems);
    private static final ScrollPane receiptItemListScrollPane = new ScrollPane(receiptItemList);

    public static Stage popUp() throws SQLException {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Cashier Access");
        primaryStage.setWidth(1010);
        primaryStage.setHeight(690);
        CashierController.addSellableItemsToDisplay();
        CashierController.itemSearchByBarcode(barcodeSearchField, barcodeSearchButton, selectedItemInput, priceInput);
        CashierController.itemSearchByName(itemNameSearchField,itemNameSearchButton,selectedItemInput,priceInput);
        CashierController.addItemToCart(addItemButton, subtotalInput, taxInput, totalInput,dateAndTimeInput);
        CashierController.deleteSelectedItem(receiptItemList, subtotalInput, taxInput, totalInput,dateAndTimeInput);
        CashierController.reset(resetButton,receiptItemList,
                barcodeSearchField,selectedItemInput,priceInput,
                subtotalInput,taxInput,totalInput,dateAndTimeInput);
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
                codeField,
                receiptBottomField
        );
        return root;
    }

    private static void formatDisplay(){
        titleLabel.setMinWidth(1000);
        titleLabel.setMinHeight(30);
        errorLabel.setMinWidth(1000);
        errorLabel.setMinHeight(10);
        errorLabel.setTextFill(Color.RED);
        barcodeColumn.setMinWidth(116);
        itemColumn.setMinWidth(116);
        priceColumn.setMinWidth(116);
        receiptNameColumn.setMinWidth(175);
        receiptPriceColumn.setMinWidth(175);
        barcodeAndItemsPane.setMinWidth(350);
        barcodeAndItems.setMinWidth(350);
        receiptItemList.setMinWidth(350);
        receiptItemListScrollPane.setMinWidth(350);
        selectedItemLabel.setMinWidth(160);
        barcodeAndItemsLabel.setFont(Font.font("Arial", 15));
        selectedItemLabel.setFont(Font.font("Arial", 15));
        ReceiptLabel.setFont(Font.font("Arial", 15));
        priceLabel.setFont(Font.font("Arial", 15));
        addItemButton.setFont(Font.font("Arial", 15));
        resetButton.setFont(Font.font("Arial",15));
        checkoutButton.setFont(Font.font("Arial",15));
        subtotalLabel.setFont(Font.font("Arial", 15));
        taxLabel.setFont(Font.font("Arial", 15));
        totalLabel.setFont(Font.font("Arial", 15));
        dateAndTimeLabel.setFont(Font.font("Arial", 15));
        errorLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
        titleLabel.setFont(Font.font("Arial", 25));
        titleLabel.setTranslateY(5);
        errorLabel.setTranslateY(5);
        titleLabel.setAlignment(Pos.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(barcodeAndItemsLabel, new Insets(1, 150, 1, 10));
        HBox.setMargin(ReceiptLabel, new Insets(1, 10, 1, 435));
        HBox.setMargin(barcodeAndItems, new Insets(1, 40, 1, 10));
        HBox.setMargin(receiptItemList, new Insets(1, 5, 1, 35));
        VBox.setMargin(selectedItemLabel, new Insets(3, 5, 3, 1));
        VBox.setMargin(selectedItemInput, new Insets(3, 5, 3, 1));
        VBox.setMargin(priceLabel, new Insets(3, 5, 3, 1));
        VBox.setMargin(priceInput, new Insets(3, 5, 3, 1));
        VBox.setMargin(addItemButton, new Insets(20, 5, 3, 42.5));
        VBox.setMargin(resetButton, new Insets(15, 5, 3, 60.5));
        HBox.setMargin(subtotalLabel, new Insets(1, 5, 1, 750));
        HBox.setMargin(subtotalInput, new Insets(1, 5, 1, 2));
        HBox.setMargin(taxLabel, new Insets(1, 5, 1, 780));
        HBox.setMargin(taxInput, new Insets(1, 5, 1, 2));
        HBox.setMargin(totalLabel, new Insets(1, 5, 1, 770));
        HBox.setMargin(totalInput, new Insets(1, 5, 1, 2));
        HBox.setMargin(dateAndTimeLabel, new Insets(1, 5, 1, 702));
        HBox.setMargin(dateAndTimeInput, new Insets(1, 5, 1, 2));
        HBox.setMargin(checkoutButton, new Insets(10, 5, 1, 892));
        barcodeAndItems.setPlaceholder(new Label("No items"));
        receiptItemList.setPlaceholder(new Label("No items"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        receiptNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        receiptPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        barcodeAndItems.getColumns().clear();
        receiptItemList.getColumns().clear();
        barcodeAndItems.getColumns().addAll(barcodeColumn, itemColumn, priceColumn);
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
        selectedItemInput.setDisable(true);
        priceInput.setDisable(true);
        subtotalInput.setDisable(true);
        taxInput.setDisable(true);
        totalInput.setDisable(true);
        dateAndTimeInput.setDisable(true);
        selectedItemInput.setStyle("-fx-opacity: .75;");
        priceInput.setStyle("-fx-opacity: .75;");
        subtotalInput.setStyle("-fx-opacity: .75;");
        taxInput.setStyle("-fx-opacity: .75;");
        totalInput.setStyle("-fx-opacity: .75;");
        dateAndTimeInput.setStyle("-fx-opacity: .75;");
        barcodeSearchButton.setGraphic(searchView);
        barcodeSearchButton.setMaxHeight(20);
        barcodeSearchButton.setMaxWidth(20);
        itemNameSearchButton.setGraphic(searchView2);
        itemNameSearchButton.setMaxHeight(20);
        itemNameSearchButton.setMaxWidth(20);
    }

    public static void displaySellableItems(ArrayList<Item> data){
        ObservableList<Item> observableData = FXCollections.observableList(data);
        barcodeAndItems.setItems(observableData);
        barcodeAndItems.refresh();
    }

    public static void displaySelectedItems(ArrayList<Item> data){
        ObservableList<Item> observableData = FXCollections.observableList(data);
        receiptItemList.setItems(observableData);
        receiptItemList.refresh();
    }

}
