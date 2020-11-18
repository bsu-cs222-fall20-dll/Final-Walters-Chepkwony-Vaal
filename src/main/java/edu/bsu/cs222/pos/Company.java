package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;

public class Company {
    private final String companyName;

    private Connection db;


    public Company(String companyName)  {
        this(companyName, false);
    }

    public Company(String companyName, boolean inMemory) {
        this.companyName = companyName;
        StringBuilder dbName = new StringBuilder();
        if (inMemory){
            dbName.append("memory:");
        }
        for (int i = 0; i < companyName.length(); i++) {
            char c = companyName.charAt(i);
            if (Character.isLetterOrDigit(c)){
                dbName.append(Character.toLowerCase(c));
            }
        }
        try {
            db = DriverManager.getConnection("jdbc:derby:"+dbName+";create=true");
            Statement statement = db.createStatement();
            DatabaseMetaData dbMd = db.getMetaData();
            ResultSet rs = dbMd.getTables(null, null, "ITEMS", null);
            if (!rs.next()) {
                statement.execute(Item.createTable);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
//    mostly needed for tests, but the tests all work without this method
//    public void close(){
//        try {
//            db.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
    public void emptyDatabase(){
        try {
            Statement statement = db.createStatement();
            statement.executeUpdate("DROP TABLE ITEMS");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addItem(String barcodeNumber, Item item)  {
        item.setBarcode(barcodeNumber);
        try {
            PreparedStatement statement = db.prepareStatement("INSERT INTO Items (ID, Price, Name) values (?, ?, ?)");
            statement.setString(1, barcodeNumber);
            statement.setBigDecimal(2, item.price);
            statement.setString(3, item.name);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void updateItemName(String barcodeNumber, String newName) {

        try {
           PreparedStatement statement = db.prepareStatement("UPDATE ITEMS SET Name = ? WHERE ID = ?");
            statement.setString(1, newName);
            statement.setString(2, barcodeNumber);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateItemCost(String barcodeNumber, BigDecimal newPrice)  {

        try {
            PreparedStatement statement = db.prepareStatement("UPDATE ITEMS SET Price = ? WHERE ID = ?");
            statement.setBigDecimal(1, newPrice);
            statement.setString(2, barcodeNumber);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public HashMap<String, Item> getAvailableInventoryList() {
        HashMap<String, Item> result= new HashMap<>();
        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Items");
            while(resultSet.next()){
                result.put(resultSet.getString("ID"),
                        new Item(
                                resultSet.getString("Name"),
                                resultSet.getBigDecimal("Price")
                        ));
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public Item getItem(String id)  {
        Item result = null;
        try {

           PreparedStatement statement = db.prepareStatement("SELECT * from Items where ID = ?");
            statement.setString(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                result = new Item(resultSet.getString("Name"), resultSet.getBigDecimal("Price"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        return result;
    }

    public void removeItem(String id)  {

        try {
            PreparedStatement statement = db.prepareStatement("DELETE from ITEMS WHERE ID = ?");
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("remove failed");
        }

    }

    public String getCompanyName() {
        return companyName;
    }
}
