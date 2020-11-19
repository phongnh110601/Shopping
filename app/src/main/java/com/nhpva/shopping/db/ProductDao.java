package com.nhpva.shopping.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nhpva.shopping.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLocalProduct(Product product);

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllLocalProducts();

    @Query("DELETE FROM product where id =:id")
    void deleteLocalProduct(int id);
}
