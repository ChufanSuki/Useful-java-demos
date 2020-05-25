<%--
  Created by IntelliJ IDEA.
  User: allen
  Date: 2020/5/25
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*" %>
<%@ page import="com.allen.MyDBInfo" %>
<html>
  <head>
    <title>Homepage</title>
  </head>
  <body>
  <ul>
    <%
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER,
                MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
        ResultSet rs = stmt.executeQuery("SELECT * FROM products");
        while (rs.next()) {
          String id = rs.getString(1);
          String name = rs.getString("name");
          out.print("<li><a href=\"show-product.jsp?id=" + id + "\">" + name + "</a></li>");
        }

      } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
      }
    %>
  </ul>
  </body>
</html>
