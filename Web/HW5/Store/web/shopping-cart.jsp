<%@ page import="java.math.BigDecimal" %>
<%--
  Created by IntelliJ IDEA.
  User: allen
  Date: 2020/5/30
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.allen.*" %>
<%@ page import="java.util.ArrayList" %>
<%
    BigDecimal total = new BigDecimal(0);
    CartModel cart = (CartModel) session.getAttribute(CartModel.ATTRIBUTE_NAME);
    DataManager dm = (DataManager) application.getAttribute(DataManager.ATTRIBUTE_NAME);
    ArrayList<DataManager.Product> products = dm.getProductsList(cart.getProductIDs());
%>
<%!
    private String liDecorator(DataManager.Product product, int quantityOf) {
        return "<li> <input type ='number' value='" + quantityOf + "' name='" + product.id + "'>"
                + product.name + ", " + product.price + "</li>";
    }
%>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<form action="ShoppingCartServlet" method="post">
    <ul>
        <%
            for (DataManager.Product product : products) {
                int quantityOfItems = cart.getQuantityOf(product.id);
                total = total.add(product.price.multiply(new BigDecimal(quantityOfItems)));
                out.print(liDecorator(product, quantityOfItems));
            }
        %>
    </ul>
    Total: $ <%= total %> <input type="submit" value="Update Cart"/>
</form>
<a href="index.jsp">Continue Shopping</a>
</body>
</html>
