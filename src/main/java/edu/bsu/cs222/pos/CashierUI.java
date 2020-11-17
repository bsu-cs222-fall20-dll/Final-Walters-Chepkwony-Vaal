package edu.bsu.cs222.pos;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class CashierUI{
    private static final Label titleLabel = new Label("Cashier Panel");
    private  static final Label errorLabel = new Label("");
    private static final TableView <String> barcodeAndItems = new TableView<>();
    private static final TableColumn <String,String> barcodeColumn = new TableColumn<>("Barcode");
    private static final TableColumn <String,String> itemColumn = new TableColumn<>("Items");
    private static final ScrollPane barcodeAndItemsPane = new ScrollPane(barcodeAndItems);

    public static Stage popUp() {
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
                barcodeAndItemsPane
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
        barcodeAndItemsPane.setMinWidth(300);
        barcodeAndItems.setMaxWidth(300);
        errorLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
        titleLabel.setFont(Font.font("Arial", 20));
        titleLabel.setTranslateY(5);
        errorLabel.setTranslateY(5);
        barcodeAndItems.setTranslateX(5);
        titleLabel.setAlignment(Pos.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        barcodeAndItems.setPlaceholder(new Label("No items"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("Barcode"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("Items"));
        barcodeAndItems.getColumns().clear();
        barcodeAndItems.getColumns().addAll(barcodeColumn, itemColumn);
        barcodeAndItemsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        barcodeAndItemsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        barcodeAndItemsPane.setVvalue(.5);
        barcodeAndItemsPane.setHvalue(.5);
        barcodeAndItemsPane.setDisable(false);


    }


}
