package com.example.retro;

import com.google.gson.annotations.SerializedName;

public class Post {

    private String category;
private String product_name;
    private Integer id;
private String product_brand,shop_name,street_name;
    private String shop_details;


    private String pin_code;

    public Post(String category, String product_name, String product_brand,String shop_name,String street_name,String shop_details,String pin_code) {
        this.category = category;
        this.product_brand = product_brand;
        this.product_name = product_name;
        this.shop_details=shop_details;
        this.shop_name=shop_name;
        this.street_name=street_name;
        this.pin_code=pin_code;
       // this.id=id;
    }

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