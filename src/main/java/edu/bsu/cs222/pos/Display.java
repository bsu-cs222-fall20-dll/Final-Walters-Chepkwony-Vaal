package edu.bsu.cs222.pos;

import javafx.application.Application;

import javafx.geometry.Pos;
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


            @Override
            public void start (Stage primaryStage){
            primaryStage.setWidth(900);
            primaryStage.setHeight(400);
            titleLabel.setFont(Font.font("Arial", 20));
            titleLabel.setMaxWidth(900);
            titleLabel.setAlignment(Pos.CENTER);

            primaryStage.setScene(new Scene(createRoot()));
            primaryStage.show();
        }

            private Pane createRoot () {
            VBox root = new VBox();
            HBox hbox = new HBox();
            root.getChildren().addAll(
                    titleLabel,
                    hbox);
            return root;
        }
    }

    public static void main(String[] args) {
        Application.launch(DisplayView.class);
    }
}