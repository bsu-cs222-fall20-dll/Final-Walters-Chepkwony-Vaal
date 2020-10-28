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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ModifyItemUI {
    public static Label titleLabel = new Label("Add Item");
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

    public static Stage popUp (Item item){
        Stage secondaryStage = new Stage();
        secondaryStage.setWidth(300);
        secondaryStage.setHeight(250);
        secondaryStage.setTitle("Edit Item");
        formatDisplay();
        Controller.doneEdit(editButton, secondaryStage, titleLabel, nameInput, priceInput,item);
        secondaryStage.setScene(new Scene(createRoot()));
        secondaryStage.show();
        return secondaryStage;
    }

    private static Pane createRoot() {
        VBox root = new VBox();
        root.getChildren().addAll(
                titleLabel,
                nameField,
                priceField,
                editButton);
        return root;
    }

    public static void formatDisplay(){
        titleLabel.setMinWidth(300);
        titleLabel.setMinHeight(40);
        nameField.setMinWidth(300);
        priceField.setMinWidth(300);
        titleLabel.setFont(Font.font("Arial", 16));
        HBox.setMargin(nameLabel, new Insets(20, 20, 10, 20));
        HBox.setMargin(nameInput, new Insets(20, 20, 10, 20));
        HBox.setMargin(priceLabel, new Insets(20, 20, 40, 20));
        HBox.setMargin(priceInput, new Insets(20, 20, 40, 20));
        titleLabel.setTranslateY(10);
        titleLabel.setAlignment(Pos.CENTER);
        nameField.setAlignment(Pos.CENTER);
        priceField.setAlignment(Pos.CENTER);
        editButton.setTranslateX(125);
    }

    public static void changeTitleLabel(String title){
        titleLabel.setText(title);
    }

    public static void setDefaultItemValues(String name, String price){
        nameInput.setText(name);
        priceInput.setText(price);
    }
}