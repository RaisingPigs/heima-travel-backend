package com.pan.es.model.vo;

import com.pan.es.model.entity.Hotel;
import lombok.Data;

@Data
public class HotelVo {
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    /**
     * 酒店星级，1星到5星，1钻到5钻
     */
    private String starName;
    /**
     * 商圈
     */
    private String business;
    private String location;
    private String pic;

    public HotelVo() {
    }

    public HotelVo(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.price = hotel.getPrice();
        this.score = hotel.getScore();
        this.brand = hotel.getBrand();
        this.city = hotel.getCity();
        this.starName = hotel.getStarName();
        this.business = hotel.getBusiness();
        this.location = hotel.getLatitude() + "," + hotel.getLongitude();
        this.pic = hotel.getPic();
    }
}