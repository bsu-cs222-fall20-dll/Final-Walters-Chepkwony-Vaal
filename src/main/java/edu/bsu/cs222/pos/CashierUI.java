package edu.bsu.cs222.pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CashierUI{
    private static final Label titleLabel = new Label("Cashier Panel");
    private static final Label errorLabel = new Label("");

    public static Stage popUp () throws SQLException {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Cashier Access");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        primaryStage.setScene(new Scene(createRoot()));
        primaryStage.show();
        return primaryStage;
    }

    private static Pane createRoot() {
        VBox root = new VBox();
        root.getChildren().addAll(
                titleLabel,
                errorLabel);
        return root;
    }
}
