package edu.bsu.cs222.pos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SingleItemUI extends Application{
    public static Label titleLabel = new Label("Add Item");
    private final Label nameLabel = new Label("Name:");
    public static TextField nameInput = new TextField("");
    private final HBox nameField = new HBox(
            nameLabel,
            nameInput);
    private final Label priceLabel = new Label("Price:");
    public static TextField priceInput = new TextField("");
    private final HBox priceField = new HBox(
            priceLabel,
            priceInput);
    private final Button doneButton = new Button("Done");

        @Override
        public void start (Stage primaryStage){
            primaryStage.setWidth(300);
            primaryStage.setHeight(300);
            formatDisplay();
            primaryStage.setScene(new Scene(createRoot()));
            primaryStage.show();
        }

        private Pane createRoot () {
            VBox root = new VBox();
            root.getChildren().addAll(
                    titleLabel,
                    nameField,
                    priceField,
                    doneButton);
            return root;
        }

        private void formatDisplay(){
            titleLabel.setMinWidth(300);
            nameField.setMinWidth(300);
            priceField.setMinWidth(300);
            titleLabel.setFont(Font.font("Arial", 16));
            HBox.setMargin(nameLabel, new Insets(20, 20, 10, 20));
            HBox.setMargin(nameInput, new Insets(20, 20, 10, 20));
            HBox.setMargin(priceLabel, new Insets(20, 20, 40, 20));
            HBox.setMargin(priceInput, new Insets(20, 20, 40, 20));
            titleLabel.setAlignment(Pos.CENTER);
            nameField.setAlignment(Pos.CENTER);
            priceField.setAlignment(Pos.CENTER);
            doneButton.setTranslateX(125);
        }

        public static void changeTitleLabel(String title){
            titleLabel.setText(title);
        }

        public static void setDefaultItemValues(String name, String price){
            nameInput.setText(name);
            priceInput.setText(price);
        }
}