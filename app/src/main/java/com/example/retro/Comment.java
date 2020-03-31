package com.example.retro;


import com.google.gson.annotations.SerializedName;

public class Comment {


    private String category;
    private String product_name;
    private Integer id;
    private String product_brand,shop_name,street_name;
    private String shop_details;


    private String pin_code;
    public  String getAvatar()
    {
        return category;
    }

    public String getUserId() {
        return pin_code;
    }

    public Integer getId() {
        return id;
    }

    public String getName1() {
        return product_name;
    }

    public String getName2() {
        return product_brand;
    }

    public String getName3() {
        return shop_name;
    }

    public String getTitle() {
        return shop_details;
    }

    public String getText() {
        return street_name;
    }
}