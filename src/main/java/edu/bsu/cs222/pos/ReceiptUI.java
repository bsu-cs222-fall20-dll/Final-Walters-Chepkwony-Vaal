package edu.bsu.cs222.pos;

import javafx.application.Application;
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

public class ReceiptUI extends Application{
    private final Label titleLabel = new Label("Company Name");
    private final HBox receiptItems = new HBox();
    private final HBox receiptTotal = new HBox();

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
                receiptItems
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