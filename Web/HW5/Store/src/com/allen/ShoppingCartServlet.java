package com.allen;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "ShoppingCartServlet",urlPatterns = "/SoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    List<String> nameList = new ArrayList();
    List<Integer> quantityList = new ArrayList();
    List<String> priceList = new ArrayList();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartModel cart = (CartModel) session.getAttribute(CartModel.ATTRIBUTE_NAME);

        String id =  request.getParameter("productID");

        if (id != null) {
            cart.addProduct(id, 1);

        } else {
            Enumeration<String> params = request.getParameterNames();
            CartModel newCart = new CartModel();
            while(params.hasMoreElements()){
                id = params.nextElement();
                newCart.addProduct(id, request.getParameter(id), cart.getQuantityOf(id));
            }
            cart = newCart;
        }

        session.setAttribute(CartModel.ATTRIBUTE_NAME, cart);

        RequestDispatcher rd = request.getRequestDispatcher("shopping-cart.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
