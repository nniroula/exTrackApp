package com.noviceModDev.extrackapp;

/* Modal class for data storage */
public class HouseHoldItem {
    private int id;
    private String purchaseDate;
    private String itemDescription;
    private String costAmount;

    /* Constructor */
    public HouseHoldItem(String purchaseDate, String itemDescription, String costAmount){
        this.id = 0;
        this.purchaseDate = purchaseDate;
        this.itemDescription = itemDescription;
        this.costAmount = costAmount;
    }

    /* getters and setters */
    public int getId () {
        return id;
    }
    public void setId (int id) {
        this.id = id;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getItemDescription() {
        return itemDescription;
    }
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCostAmount() {
        return costAmount;
    }
    public void setCostAmount(String costAmount) {
        this.costAmount = costAmount;
    }
}
