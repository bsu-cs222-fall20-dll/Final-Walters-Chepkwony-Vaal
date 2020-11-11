package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;

public class Company {
    private String companyName;
    private HashMap<String,Item> inventoryList = new HashMap<>();
    private Connection db;
    private BarcodeGenerator bcg;

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
        this.bcg = new BarcodeGenerator(this);

    }
    public void addItem(String barcodeNumber, Item item)  {
        PreparedStatement statement = null;
        try {
            statement = db.prepareStatement("INSERT INTO Items (ID, Price, Name) values (?, ?, ?)");
            statement.setString(1, barcodeNumber);
            statement.setBigDecimal(2, item.price);
            statement.setString(3, item.name);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void updateItemName(String barcodeNumber, String newName) {
        PreparedStatement statement = null;
        try {
            statement = db.prepareStatement("UPDATE ITEMS SET Name = ? WHERE ID = ?");
            statement.setString(1, newName);
            statement.setString(2, barcodeNumber);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateItemCost(String barcodeNumber, BigDecimal newPrice) throws SQLException {
        PreparedStatement statement = db.prepareStatement("UPDATE ITEMS SET Price = ? WHERE ID = ?");
        statement.setBigDecimal(1, newPrice);
        statement.setString(2, barcodeNumber);
        statement.execute();
    }

    public HashMap<String, Item> getAvailableInventoryList() {
        HashMap<String, Item> result= new HashMap<String, Item>();
        Statement statement = null;
        try {
            statement = db.createStatement();
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


    public void removeItem(String id) {
        inventoryList.remove(id);
    }

    public Item searchByItemName(String searchItemName) {
        for(Item item : inventoryList.values()){
            if(item.getName().equals(searchItemName)){
                return item;
            }
        }
        return null;
    }
}
