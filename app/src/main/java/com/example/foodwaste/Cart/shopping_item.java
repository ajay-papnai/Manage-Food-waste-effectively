package com.example.foodwaste.Cart;

public class shopping_item {
    private String grocery;
    private String QTY;

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getGrocery() {
        return grocery;
    }

    public void setGrocery(String grocery) {
        this.grocery = grocery;
    }

    public shopping_item() {
    }

    public shopping_item(String grocery) {
        this.grocery = grocery;
    }
}
