package com.shoppingstore.shop.helper;

import com.shoppingstore.shop.model.Shop;

public class Distance {

    public static int getDistanceBetweenShops(Shop shop1, Shop shop2){
        if(
                shop1 != null &&
                shop1.getLocation() != null &&
                shop2 != null &&
                shop2.getLocation() != null

        ){
            double x1 = shop1.getLocation().getLongitude(),y1 = shop1.getLocation().getLatitude();
            double x2 = shop2.getLocation().getLongitude(),y2 = shop2.getLocation().getLatitude();

            return (int) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }
        return 0;
    }

}
