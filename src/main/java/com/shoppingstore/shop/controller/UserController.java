package com.shoppingstore.shop.controller;

import com.shoppingstore.shop.model.Location;
import com.shoppingstore.shop.model.Shop;
import com.shoppingstore.shop.model.User;
import com.shoppingstore.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    List<Shop> defaultShops = new ArrayList<Shop>(){{
        Location location1 = new Location();
        Location location2 = new Location();
        Location location3 = new Location();

        location1.setCity("New York");
        location1.setLongitude(-73.9682);
        location1.setLatitude(40.7850);

        location2.setCity("Casablanca");
        location2.setLongitude(-7.5833);
        location2.setLatitude(33.5333);

        location3.setCity("Maldives");
        location3.setLatitude(1.9772);
        location3.setLongitude(73.5361);

        Shop shop1 = new Shop();
        shop1.setName("little birds");
        shop1.setLocation(location1);
        Shop shop2 = new Shop();
        shop2.setName("Tiw Tiw");
        shop2.setLocation(location2);
        Shop shop3 = new Shop();
        shop3.setName("heaven birds");
        shop3.setLocation(location3);

        add(shop1);
        add(shop2);
        add(shop3);
    }};

    // Email regex

    @PostMapping
    public User signUp(@RequestBody User user){
        if(user != null && userRepository.findUserByEmail(user.getEmail()) == null){
            List<Shop> userShops = defaultShops.stream().map(shop -> {
                Location location = shop.getLocation();
                shop.setUser(user);
                location.setShop(shop);
                shop.setLocation(location);
                return shop;
            }).collect(Collectors.toList());
            user.setShop(userShops);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Location location = user.getLocation();
            location.setUser(user);
            user.setLocation(location);
            userRepository.save(user);
        }
        return user;
    }

}
