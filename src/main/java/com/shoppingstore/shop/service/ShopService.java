package com.shoppingstore.shop.service;

import com.shoppingstore.shop.helper.Distance;
import com.shoppingstore.shop.model.Shop;
import com.shoppingstore.shop.model.ShopStatus;
import com.shoppingstore.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public List<Shop> getSortedShops(){
        return shopRepository.
                findAll().
                stream().
                filter(shop -> shop.getShopStatus() == ShopStatus.IDLE).
                sorted(
                    new Comparator<Shop>() {
                        @Override
                        public int compare(Shop o1, Shop o2) {
                            return
                                    Distance.getDistanceBetweenShops(o1, o2);
                        }
                    }
                ).collect(Collectors.toList());
    }

    public List<Shop> getLikedShops(){
        return shopRepository.
                findAll().
                stream().
                filter(shop -> shop.getShopStatus() == ShopStatus.LIKED).
                collect(Collectors.toList());
    }
}
