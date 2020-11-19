package com.nhpva.shopping.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.nhpva.shopping.db.ProductDao;
import com.nhpva.shopping.db.ProductDatabase;
import com.nhpva.shopping.model.Product;
import com.nhpva.shopping.model.ProductResponse;
import com.nhpva.shopping.network.ProductModule;
import com.nhpva.shopping.network.ProductService;

import java.util.List;

import retrofit2.Call;

public class ProductRepository {
    private ProductDao productDao;
    private ProductService productService;

    public ProductRepository(Application application) {
        this.productDao = ProductDatabase.getInstance(application).productDao();
        this.productService = ProductModule.getInstance();
    }

    public Call<ProductResponse> getRemoteProducts(){
        return productService.getAllRemoteProducts();
    }

    public LiveData<List<Product>> getAllLocalProducts(){
        return productDao.getAllLocalProducts();
    }

    public void insertLocalProduct(Product product){
        productDao.insertLocalProduct(product);
    }

    public void deleteLocalProduct(int id){
        productDao.deleteLocalProduct(id);
    }
}
