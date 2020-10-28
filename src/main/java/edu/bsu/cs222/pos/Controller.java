package edu.bsu.cs222.pos;

import java.math.BigDecimal;

public class Controller {

    public static void editCompanyName(){
    }

    public static void addItemsToDisplay(){
        Item exampleItem = new Item("Example", new BigDecimal("2.0"));
        Display.displayItems(exampleItem);
    }
}
