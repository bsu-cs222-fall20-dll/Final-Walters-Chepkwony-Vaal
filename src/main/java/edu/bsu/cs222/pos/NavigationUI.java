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

public class NavigationUI extends Application{
    private final Label titleLabel = new Label("POS System");
    private static final Label errorLabel = new Label("");
    private final Button adminButton = new Button("Administrative Access");
    private final Button cashierButton = new Button("Cashier Access");
    private final HBox options = new HBox(adminButton, cashierButton);
    private final TextInputDialog opening = new TextInputDialog("POS System");

    @Override
    public void start (Stage primaryStage) {
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        AdminController.enterCompanyName(opening, adminButton, cashierButton);
        AdminController.toAdmin(adminButton, cashierButton);
        AdminController.toCashier(cashierButton, adminButton);
        formatDisplay();
        primaryStage.setScene(new Scene(createRoot()));
        primaryStage.show();
    }

    private Pane createRoot () {
        VBox root = new VBox();
        root.getChildren().addAll(
                titleLabel,
                errorLabel,
                options);
        return root;
    }

    private void formatDisplay(){
        titleLabel.setMinWidth(500);
        titleLabel.setMinHeight(100);
        options.setMinWidth(500);
        options.setMinWidth(100);
        titleLabel.setFont(Font.font("Arial", 30));
        HBox.setMargin(adminButton, new Insets(20, 20, 20, 20));
        HBox.setMargin(cashierButton, new Insets(20, 20, 20, 20));
        titleLabel.setAlignment(Pos.CENTER);
        options.setAlignment(Pos.CENTER);
        opening.setTitle("POS System");
        opening.setHeaderText("Welcome to the POS System!");
        opening.setContentText("Please enter a company name:");
    }
}