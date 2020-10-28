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

public class SingleItemUI{
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
    private static final Button doneButton = new Button("Done");

    public static Stage popUp (){
        Stage secondaryStage = new Stage();
        secondaryStage.setWidth(300);
        secondaryStage.setHeight(250);
        formatDisplay();
        Controller.doneAddItem(doneButton, secondaryStage, titleLabel, nameInput, priceInput);
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
                doneButton);
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