package com.shoppingstore.shop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue
    Long id;

    String name;

    String picture;

    @Enumerated(EnumType.STRING)
    ShopStatus shopStatus = ShopStatus.IDLE;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "shop"
    )
    Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    User user;
}
