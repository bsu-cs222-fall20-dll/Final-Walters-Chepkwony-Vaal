package edu.bsu.cs222.pos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class ModifyItemUI {
    public static Label titleLabel = new Label("Add Item");
    private static final Label errorLabel = new Label("");
    private static final Label nameLabel = new Label("Name:");
    public static TextField nameInput = new TextField("");
    private static final HBox nameField = new HBox(
            nameLabel,
            nameInput);
    private static final Label priceLabel = new Label("Price:");
    public static TextField priceInput = new TextField("");
    private static final HBox priceField = new HBox(
            priceLabel,
            priceInput);
    private static final Button editButton = new Button("Edit");
    private static final Button deleteButton = new Button("Delete");

    public static Stage popUp (Item item){
        Stage secondaryStage = new Stage();
        secondaryStage.setWidth(300);
        secondaryStage.setHeight(250);
        secondaryStage.setTitle("Edit Item");
        formatDisplay();
        AdminController.doneEdit(editButton, errorLabel, deleteButton, secondaryStage, nameInput, priceInput,item);
        secondaryStage.setScene(new Scene(createRoot()));
        secondaryStage.show();
        return secondaryStage;
    }

    private static Pane createRoot() {
        VBox root = new VBox();
        root.getChildren().addAll(
                titleLabel,
                errorLabel,
                nameField,
                priceField,
                editButton,
                deleteButton);
        return root;
    }

    public static void formatDisplay(){
        formatTitle(titleLabel, errorLabel, nameField, priceField, nameLabel, nameInput, priceLabel, priceInput, editButton);
        deleteButton.setTranslateX(125);
        deleteButton.setTranslateY(-5);
        editButton.setTranslateY(-10);
    }

    static void formatTitle(Label titleLabel, Label errorLabel, HBox nameField, HBox priceField, Label nameLabel, TextField nameInput, Label priceLabel, TextField priceInput, Button editButton) {
        titleLabel.setMinWidth(300);
        titleLabel.setMinHeight(30);
        errorLabel.setMinWidth(300);
        errorLabel.setMinHeight(10);
        nameField.setMinWidth(300);
        priceField.setMinWidth(300);
        titleLabel.setFont(Font.font("Arial", 16));
        errorLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 11));
        errorLabel.setTextFill(Color.RED);
        HBox.setMargin(nameLabel, new Insets(20, 20, 10, 20));
        HBox.setMargin(nameInput, new Insets(20, 20, 10, 20));
        HBox.setMargin(priceLabel, new Insets(5, 20, 20, 20));
        HBox.setMargin(priceInput, new Insets(5, 20, 20, 20));
        HBox.setMargin(editButton, new Insets(10, 0, 10, 0));
        titleLabel.setTranslateY(10);
        errorLabel.setTranslateY(10);
        titleLabel.setAlignment(Pos.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        nameField.setAlignment(Pos.CENTER);
        priceField.setAlignment(Pos.CENTER);
        editButton.setTranslateX(132);
    }

    public static void changeTitleLabel(String title){
        titleLabel.setText(title);
    }

    public static void setDefaultItemValues(String name, String price){
        nameInput.setText(name);
        priceInput.setText(price);
    }
}