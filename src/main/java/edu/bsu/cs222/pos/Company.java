package edu.bsu.cs222.pos;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
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
            createTableIfNotExists("ITEMS", Item.createTable);
            createTableIfNotExists("DISCOUNTS", Discount.createTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private void createTableIfNotExists(String tableName, String creationSQL) throws SQLException {
        //MySQL has a great thing called "CREATE IF NOT EXISTS"
        //but Derby doesn't. So we have to write it ourselves.
        Statement statement = db.createStatement();
        DatabaseMetaData dbMd = db.getMetaData();
        ResultSet rs = dbMd.getTables(null, null, tableName, null);
        if (!rs.next()) {
            statement.execute(creationSQL);
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
                                resultSet.getBigDecimal("Price"),
                                resultSet.getString("ID")
                        ));
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public Item getItemByID(String id)  {
        Item result = null;
        try {

           PreparedStatement statement = db.prepareStatement("SELECT * from Items where ID = ?");
            statement.setString(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                result = new Item(resultSet.getString("Name"), resultSet.getBigDecimal("Price"), id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public Item getItemByName(String itemName)  {
        Item result = null;
        try {

            PreparedStatement statement = db.prepareStatement("SELECT * from Items where NAME = ?");
            statement.setString(1, itemName);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                result = new Item(resultSet.getString("Name"), resultSet.getBigDecimal("Price"), resultSet.getString("ID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public String generateBarcode() {
        String newCodeInProgress = "";
        do {
            for (int i = 0; i < 12; i++) {
                int digit = (int) ((Math.random() * (10)) + 0);
                newCodeInProgress = newCodeInProgress.concat(String.valueOf(digit));
            }
        } while (getItemByID(newCodeInProgress) !=null);
        return newCodeInProgress;
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
    public void addDiscount(String couponCode, Discount discount)  {
        try {
            PreparedStatement statement = db.prepareStatement("INSERT INTO DISCOUNTS (ID, Amount, Name, isPercentage) values (?, ?, ?, ?)");
            statement.setString(1, couponCode);
            statement.setBigDecimal(2, discount.amount);
            statement.setString(3, discount.name);
            statement.setBoolean(4, discount.isPercentage);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updateDiscountName(String couponCode, String newName) {
        try {
            PreparedStatement statement = db.prepareStatement("UPDATE DISCOUNTS SET NAME = ? WHERE ID = ?");
            statement.setString(1, newName);
            statement.setString(2, couponCode);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void updateDiscountAmount(String couponCode, BigDecimal newAmount, boolean isPercentage)  {
        try {
            PreparedStatement statement = db.prepareStatement("UPDATE DISCOUNTS SET AMOUNT = ?, ISPERCENTAGE = ? WHERE ID = ?");
            statement.setBigDecimal(1, newAmount);
            statement.setBoolean(2, isPercentage);
            statement.setString(3, couponCode);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Discount getDiscountByID(String id)  {
        Discount result = null;
        try {

            PreparedStatement statement = db.prepareStatement("SELECT * from DISCOUNTS where ID = ?");
            statement.setString(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                result = new Discount(id,
                        resultSet.getString("Name"),
                        resultSet.getBigDecimal("Amount"),
                        resultSet.getBoolean("isPercentage"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public HashMap<String, Discount> getActiveCoupons() {
        HashMap<String, Discount> result= new HashMap<>();
        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Discounts");
            while(resultSet.next()){
                result.put(resultSet.getString("ID"),
                        new Discount(
                                resultSet.getString("ID"),
                                resultSet.getString("Name"),
                                resultSet.getBigDecimal("Amount"),
                                resultSet.getBoolean("isPercentage")
                        ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public String generateCouponCode() {
        String newCodeInProgress = "";
        do {
            for (int i = 0; i < 12; i++) {
                int digit = (int) ((Math.random() * (10)) + 0);
                newCodeInProgress = newCodeInProgress.concat(String.valueOf(digit));
            }
        } while (getDiscountByID(newCodeInProgress) !=null);
        return newCodeInProgress;
    }

    public String getCompanyName() {
        return companyName;
    }
}
