package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ReceiptUI extends Application{
    private final Label titleLabel = new Label("Company Name");
    private final Label itemLabel = new Label("Item Name");
    private final Label priceLabel = new Label("Price");
    private final Label taxLabel = new Label("Tax:");
    private final Label balanceLabel = new Label("Balance:");
    private final Label dateAndTimeLabel = new Label("Date and Time:");
    private final HBox receiptItems = new HBox();
    private final HBox receiptTotal = new HBox();

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
                receiptItems,
                receiptItemListScrollPane,
                taxLabel,
                balanceLabel,
                dateAndTimeLabel

        );
        return root;
    }

    private void formatDisplay(){
        titleLabel.setMinWidth(400);
        titleLabel.setMinHeight(50);
        receiptItems.setMinWidth(400);
        receiptItems.setMinHeight(400);
        receiptItems.setMinWidth(400);
        receiptItems.setMinHeight(150);
        titleLabel.setFont(Font.font("Arial", 25));
        titleLabel.setAlignment(Pos.CENTER);
    }

    public static void displayItem(){
    }

}