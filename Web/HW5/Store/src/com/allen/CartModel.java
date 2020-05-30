package com.allen;

import java.util.HashMap;
import java.util.Set;

public class CartModel {

    public static final String ATTRIBUTE_NAME = "Shopping Cart";
    private HashMap<String,Integer> cart = new HashMap<String, Integer>();

    public CartModel(){

    }

    public void addProduct(String id, int quantity) {
        if (cart.containsKey(id))
            cart.put(id, cart.get(id) + quantity);
        else cart.put(id, quantity);
    }

    public void addProduct(String id, String stringQuantity,int defaultQuantity) {
        int quantity;

        try{
            quantity = Integer.parseInt(stringQuantity);
        }catch (Exception e){
            quantity = defaultQuantity;
        }

        if(quantity != 0 ) addProduct(id,quantity);


    }

    public int getQuantityOf(String id) {
        return cart.get(id);
    }
    public Set<String> getProductIDs(){
        return cart.keySet();
    }
    public boolean contains(String id){
        return cart.containsKey(id);
    }
}
