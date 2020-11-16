package edu.bsu.cs222.pos;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class CashierUI{
    private static final Label titleLabel = new Label("Cashier Panel");
    private  static final Label errorLabel = new Label("");
    private static final Label foodTypeLabel = new Label("Food Types");
    private static final HBox foodTypeHeader = new HBox(
            foodTypeLabel
            );
    private static final TableView <String> foodTypes = new TableView<>();
    private static final TableColumn <String,String> foodTypeColumn = new TableColumn<>("Food Types");
    private static final ScrollPane foodTypeScrollPane = new ScrollPane(foodTypes);

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
                foodTypeHeader,
                foodTypeScrollPane
                );
        return root;
    }

    private static void formatDisplay(){
        titleLabel.setMinWidth(1000);
        titleLabel.setMinHeight(10);
        errorLabel.setMinWidth(1000);
        errorLabel.setMinHeight(10);
        errorLabel.setTextFill(Color.RED);
        foodTypeColumn.setMinWidth(200);
        foodTypeScrollPane.setMinWidth(200);
        foodTypeHeader.setMinWidth(200);
        errorLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
        titleLabel.setFont(Font.font("Arial", 20));
        titleLabel.setTranslateY(5);
        errorLabel.setTranslateY(5);
        titleLabel.setAlignment(Pos.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        foodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Food Types"));
        foodTypeScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        foodTypeScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        foodTypeScrollPane.setVvalue(.5);
        foodTypeScrollPane.setHvalue(.5);
        foodTypeScrollPane.setDisable(false);


    }


}
