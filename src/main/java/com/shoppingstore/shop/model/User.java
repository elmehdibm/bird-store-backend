package com.shoppingstore.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ShopUser")
@Data
public class User {
    @Id
    @GeneratedValue
    Long id;

    String name;

    String email;

    String password;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    Location location;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    @JsonIgnore
    List<Shop> shop;
}
