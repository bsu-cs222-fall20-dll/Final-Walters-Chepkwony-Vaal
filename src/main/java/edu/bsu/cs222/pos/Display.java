package edu.bsu.cs222.pos;

import com.sun.javafx.scene.control.InputField;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Display {

    //I had to nest this because my computer is dumb. I'll make it the main class when I'm done.
    public static class DisplayView extends Application
        {
            private Label titleLabel = new Label("Admin Panel");
            private Label companyName = new Label("Company Name:");
            private TextField companyNameInput = new TextField("Company");
            private ToggleButton edit = new ToggleButton("Edit");
            private Button addItem = new Button("+");


            @Override
            public void start (Stage primaryStage){
                primaryStage.setWidth(900);
                primaryStage.setHeight(400);
                titleLabel.setFont(Font.font("Arial", 20));
                titleLabel.setMaxWidth(900);
                titleLabel.setAlignment(Pos.CENTER);
                companyNameInput.setEditable(false);
                companyNameInput.setOnMouseClicked(event -> companyNameInput.setEditable(true));
                companyNameInput.setOnAction(event -> companyNameInput.setEditable(false));
                companyNameInput.editableProperty().bindBidirectional(edit.selectedProperty());
                edit.setOnAction(event -> {
                    if (edit.isSelected()) {
                        companyNameInput.requestFocus();
                    }
                });
                companyNameInput.styleProperty().bind(
                        Bindings.when(
                                companyNameInput.editableProperty())
                                .then((String) null)
                                .otherwise("-fx-background-color: transparent;")
                );

                primaryStage.setScene(new Scene(createRoot()));
                primaryStage.show();
            }

            private Pane createRoot () {
                VBox root = new VBox();
                HBox content = new HBox(
                        companyName,
                        companyNameInput,
                        edit);
                root.getChildren().addAll(
                        titleLabel,
                        content);
                return root;
            }
        }

    public static void main(String[] args) {
        Application.launch(DisplayView.class);
    }
}