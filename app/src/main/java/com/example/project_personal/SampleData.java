package com.example.project_personal;

public class SampleData {
    private int itemImage;
    private String itemName;
    private String itemPrize;

    public SampleData(int itemImage, String itemName, String itemPrize){
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemPrize = itemPrize;
    }

    public int getItemImage()
    {
        return this.itemImage;
    }

    public String getItemName()
    {
        return this.itemName;
    }

    public String getItemPrize()
    {
        return this.itemPrize;
    }
}