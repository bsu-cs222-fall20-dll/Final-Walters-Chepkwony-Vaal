package edu.bsu.cs222.pos;

import javafx.application.Application;
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

public class NavigationUI extends Application{
    //should force user to enter a company name before being able to press any buttons
    //TODO add a create new company button
    private final Label titleLabel = new Label("Welcome to POS System!");
    private final Label companyNameLabel = new Label("Company Name:");
    private final TextField companyNameInput = new TextField();
    private final HBox companyField = new HBox(
            companyNameLabel,
            companyNameInput);
    private final Button adminButton = new Button("Administrative Access");
    private final Button cashierButton = new Button("Cashier Access");
    private final HBox options = new HBox(adminButton, cashierButton);

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        formatDisplay();
        primaryStage.setScene(new Scene(createRoot()));
        primaryStage.show();
    }

    private Pane createRoot () {
        VBox root = new VBox();
        root.getChildren().addAll(
                titleLabel,
                companyField,
                options);
        return root;
    }

    private void formatDisplay(){
        titleLabel.setMinWidth(500);
        titleLabel.setMinHeight(75);
        companyField.setMinWidth(500);
        companyField.setMinHeight(75);
        options.setMinWidth(500);
        options.setMinWidth(75);
        titleLabel.setFont(Font.font("Arial", 16));
        HBox.setMargin(companyNameLabel, new Insets(0, 5, 20, 20));
        HBox.setMargin(companyNameInput, new Insets(0, 20, 20, 5));
        HBox.setMargin(adminButton, new Insets(20, 20, 20, 20));
        HBox.setMargin(cashierButton, new Insets(20, 20, 20, 20));
        //titleLabel.setTranslateY(5);
        titleLabel.setAlignment(Pos.CENTER);
        options.setAlignment(Pos.CENTER);
        companyField.setAlignment(Pos.CENTER);
    }
}