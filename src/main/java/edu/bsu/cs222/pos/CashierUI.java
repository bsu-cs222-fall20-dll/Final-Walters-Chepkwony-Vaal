package edu.bsu.cs222.pos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import javax.swing.text.View;
import java.sql.SQLException;


public class CashierUI{
    private static final Label titleLabel = new Label("Cashier Panel");
    private  static final Label errorLabel = new Label("");
    private static final Label barcodeAndItemsLabel = new Label("Barcode And Items:");
    private static final Label barcodeSearchLabel = new Label("Barcode Search");
    private static final TextField barcodeSearchField = new TextField();
    private static final Button barcodeSearchButton = new Button();
    private static final Label selectedItemLabel = new Label("Selected Items:");
    private static final TextField selectedItemInput = new TextField();
    private static final Label priceLabel = new Label("Price:");
    private static final TextField priceInput = new TextField();
    private static final Label qualityLabel = new Label("Quality:");
    private static final TextField qualityInput = new TextField();
    private static final Label ReceiptLabel = new Label("Receipt:");
    private static final Label subtotalLabel = new Label("Subtotal:");
    private static final TextField subtotalInput = new TextField();
    private static final Label taxLabel = new Label("Tax:");
    private static final TextField taxInput = new TextField();
    private static final Label totalLabel = new Label("Total:");
    private static final TextField totalInput = new TextField();
    private static final TableView <String> barcodeAndItems = new TableView<>();
    private static final TableView<Item> receiptItemList = new TableView<>();
    private static final HBox barcodeHBox = new HBox(barcodeSearchField,barcodeSearchButton);
    private static final Image img = new Image("C://Users//bvaal//IdeaProjects//Final-Walters-Chepkwony-Vaal-Wang//src//main//search");
    public static final ImageView searchView = new ImageView(img);
    private static final VBox codeForTheMiddleLabel = new VBox(
            barcodeSearchLabel,
            barcodeHBox,
            selectedItemLabel,
            selectedItemInput,
            priceLabel,
            priceInput,
            qualityLabel,
            qualityInput
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
    private static final VBox receiptBottomField = new VBox(
            subtotalField,
            taxField,
            totalField
    );
    private static final HBox codeField = new HBox(
            barcodeAndItems,
            codeForTheMiddleLabel,
            receiptItemList
            );
    private static final TableColumn <String,String> barcodeColumn = new TableColumn<>("Barcode");
    private static final TableColumn <String,String> itemColumn = new TableColumn<>("Items");
    private static final TableColumn<Item, Item> receiptNameColumn = new TableColumn<>("Item Name");
    private static final TableColumn<Item, Item> receiptPriceColumn = new TableColumn<>("Price");
    private static final ScrollPane barcodeAndItemsPane = new ScrollPane(barcodeAndItems);
    private static final ScrollPane receiptItemListScrollPane = new ScrollPane(receiptItemList);

    public static Stage popUp() throws SQLException {
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
                codeField,
                receiptBottomField
        );
        return root;
    }

    @SuppressWarnings("unchecked")
    private static void formatDisplay(){
        titleLabel.setMinWidth(1000);
        titleLabel.setMinHeight(10);
        errorLabel.setMinWidth(1000);
        errorLabel.setMinHeight(10);
        errorLabel.setTextFill(Color.RED);
        barcodeColumn.setMinWidth(150);
        itemColumn.setMinWidth(150);
        receiptNameColumn.setMinWidth(150);
        receiptPriceColumn.setMinWidth(150);
        barcodeAndItemsPane.setMinWidth(300);
        barcodeAndItems.setMinWidth(300);
        receiptItemList.setMinWidth(300);
        receiptItemListScrollPane.setMinWidth(300);
        selectedItemLabel.setMinWidth(160);
        barcodeAndItemsLabel.setFont(Font.font("Arial", 15));
        selectedItemLabel.setFont(Font.font("Arial", 15));
        ReceiptLabel.setFont(Font.font("Arial", 15));
        priceLabel.setFont(Font.font("Arial", 15));
        qualityLabel.setFont(Font.font("Arial", 15));
        subtotalLabel.setFont(Font.font("Arial", 15));
        taxLabel.setFont(Font.font("Arial", 15));
        totalLabel.setFont(Font.font("Arial", 15));
        errorLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
        titleLabel.setFont(Font.font("Arial", 25));
        titleLabel.setTranslateY(5);
        errorLabel.setTranslateY(5);
        titleLabel.setAlignment(Pos.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(barcodeAndItemsLabel, new Insets(1, 150, 1, 10));
        HBox.setMargin(ReceiptLabel, new Insets(1, 10, 1, 380));
        HBox.setMargin(barcodeAndItems, new Insets(1, 110, 1, 10));
        HBox.setMargin(receiptItemList, new Insets(1, 5, 1, 85));
        VBox.setMargin(selectedItemLabel, new Insets(3, 5, 3, 1));
        VBox.setMargin(selectedItemInput, new Insets(3, 5, 3, 1));
        VBox.setMargin(priceLabel, new Insets(3, 5, 3, 1));
        VBox.setMargin(priceInput, new Insets(3, 5, 3, 1));
        VBox.setMargin(qualityLabel, new Insets(3, 5, 3, 1));
        VBox.setMargin(qualityInput, new Insets(3, 5, 3, 1));
        HBox.setMargin(subtotalLabel, new Insets(1, 5, 1, 750));
        HBox.setMargin(subtotalInput, new Insets(1, 5, 1, 2));
        HBox.setMargin(taxLabel, new Insets(1, 5, 1, 780));
        HBox.setMargin(taxInput, new Insets(1, 5, 1, 2));
        HBox.setMargin(totalLabel, new Insets(1, 5, 1, 770));
        HBox.setMargin(totalInput, new Insets(1, 5, 1, 2));
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
        barcodeSearchButton.setGraphic(searchView);
    }


}
