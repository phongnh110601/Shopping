package com.nhpva.shopping.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhpva.shopping.model.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    public static ProductDatabase getInstance(Application application){
        return Room.databaseBuilder(application.getApplicationContext(), ProductDatabase.class, "product.db")
                .fallbackToDestructiveMigration()
                .build();
    }
}
