package com.shoppingstore.shop.controller;


import com.shoppingstore.shop.model.Shop;
import com.shoppingstore.shop.model.ShopStatus;
import com.shoppingstore.shop.repository.ShopRepository;
import com.shoppingstore.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    ShopRepository shopRepository;

    @GetMapping
    public List<Shop> getSortedShops(){
        return shopService.getSortedShops();
    }

    @GetMapping("/liked")
    public List<Shop> getLikedShops(){
        return shopService.getLikedShops();
    }

    @PutMapping("/like")
    public Shop likeShop(@RequestParam("shopId") long id){
        Shop shop = shopRepository.getOne(id);
        shop.setShopStatus(ShopStatus.LIKED);
        shopRepository.save(shop);
        return shop;
    }

    @PutMapping("/dislike")
    public Shop dislikeShop(@RequestParam("shopId") long id) throws InterruptedException{
        Shop shop = shopRepository.getOne(id);
        // delay here time
        TimeUnit.HOURS.sleep(2);
        shop.setShopStatus(ShopStatus.DISLIKED);
        shopRepository.save(shop);
        return shop;
    }
}
