package edu.bsu.cs222.pos;

import javafx.application.Application;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Application.launch(AdminPanelUI.class);
        Controller.addItemsToDisplay();
    }
}
