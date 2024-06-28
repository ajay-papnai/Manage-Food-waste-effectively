package com.example.foodwaste.Inventory;

import java.util.Objects;

public class Inventory_item {

    private String food_name;
    private String qty;
    private String purchase;
    private String expiry;

    public Inventory_item(String food_name, String qty, String purchase, String expiry) {
        this.food_name = food_name;
        this.qty = qty;
        this.purchase = purchase;
        this.expiry = expiry;
    }

    public Inventory_item() {
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory_item that = (Inventory_item) o;
        return Objects.equals(food_name, that.food_name) &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(purchase, that.purchase) &&
                Objects.equals(expiry, that.expiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food_name, qty, purchase, expiry);
    }
}
