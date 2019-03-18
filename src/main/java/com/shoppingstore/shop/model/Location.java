package com.shoppingstore.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue
    Long id;

    Double latitude;

    Double longitude;

    String city;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    @JsonIgnore
    Shop shop;
}
