package com.nhpva.shopping.network;

import com.nhpva.shopping.model.Product;
import com.nhpva.shopping.util.Const;

public class ProductModule {
    private static ProductService INSTANCE;
    private ProductModule(){

    }

    public static ProductService getInstance(){
        if (INSTANCE == null){
            INSTANCE = ProductClient.getInstance(Const.BASE_URL).create(ProductService.class);
        }
        return INSTANCE;
    }
}
