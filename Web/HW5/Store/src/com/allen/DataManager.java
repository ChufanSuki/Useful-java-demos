package com.allen;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class DataManager {
    public static final String ATTRIBUTE_NAME = "Data Manager";

    public class Product {
        public String id;
        public String name;
        public String image;
        public BigDecimal price;
    }

    public DataManager() {

    }

    public Product getProductInfo(String id) throws SQLException {
        Connection con = MyDBInfo.getConnection();
        Product product = null;
        String query = "SELECT * FROM products WHERE productid = ?;";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            product = new Product();
            product.id = resultSet.getString(1);
            product.name = resultSet.getString(2);
            product.image = resultSet.getString(3);
            product.price = (BigDecimal)resultSet.getObject(4);
        }
        return product;
    }
     public ArrayList<Product> getProductsList(Set<String> idSet) {
         ArrayList<Product> result = new ArrayList<>();
         Connection con = MyDBInfo.getConnection();
         try {
             String query = "SELECT * FROM products";
             if(idSet!=null) {
                 query+=" WHERE productid IN (";
                 StringBuilder ids = new StringBuilder();
                 for(String id:idSet){
                     ids.append("'").append(id).append("',");
                 }

                 query+= ids.substring(0, ids.length() - 1) + ")";
             }

             query += ";";
             PreparedStatement statement = con.prepareStatement(query);

             ResultSet rs = statement.executeQuery();

             while (rs.next()){
                 Product product = new Product();

                 product.id = (String) rs.getObject(1);
                 product.name = (String) rs.getObject(2);
                 product.image = (String) rs.getObject(3);
                 product.price = (BigDecimal) rs.getObject(4);

                 result.add(product);
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
         return result;

     }
}
