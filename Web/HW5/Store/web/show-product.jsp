<%--
  Created by IntelliJ IDEA.
  User: allen
  Date: 2020/5/25
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*" %>
<%@ page import="com.allen.MyDBInfo" %>
<%!
    String id = null;
    String name = null;
    String image = null;
    String price = null;
%>
<%
    id = request.getParameter("id");
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER,
                MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
        Statement stmt = con.createStatement();
        stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
        ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE productid = \" " + id + "\"");
        rs.next(); // Don't forget it
        name = rs.getString("name");
        image = rs.getString("imagefile");
        price = rs.getString("price");
    } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
    }
%>
<html>
<head>
    <title><%=name%></title>
</head>
<body>
<h1><%=name%></h1>
<img src=<%="store-images/"+image%> alt="<%=name%>">
<form method="post" action="ShoppingCartServlet">
    <%=price%>
    <input type="hidden" name="productID" value="<%=id%>"/>
    <button type="submit" value="add">Add to cart.</button>
</form>
</body>
</html>
