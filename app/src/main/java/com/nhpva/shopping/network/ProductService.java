package com.nhpva.shopping.network;

import com.nhpva.shopping.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET
    Call<ProductResponse> getAllRemoteProducts();
}
