package com.nhpva.shopping.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nhpva.shopping.model.Product;
import com.nhpva.shopping.model.ProductResponse;
import com.nhpva.shopping.repository.ProductRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> mLocalProducts = null;
    private MutableLiveData<List<Product>> mRemoteProducts = new MutableLiveData<>();
    private MutableLiveData<List<String>> mRemoteImageEvents = new MutableLiveData<>();

    public ProductViewModel (Application application){
        this.repository = new ProductRepository(application);
        this.mLocalProducts = repository.getAllLocalProducts();
    }

    public LiveData<List<Product>> getLocalProducts(){
        return mLocalProducts;
    }

    public MutableLiveData<List<Product>> getRemoteProducts(){
        return mRemoteProducts;
    }

    public MutableLiveData<List<String>> getRemoteImageEvents(){
        return mRemoteImageEvents;
    }

    public void getRemoteData(){
        repository.getRemoteProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    List<Product> products = response.body().getResults();
                    List<String> imgEvents = response.body().getImgEvents();
                    mRemoteProducts.postValue(products);
                    mRemoteImageEvents.postValue(imgEvents);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }

    public void insertLocalProduct(Product product){
        repository.insertLocalProduct(product);
    }

    public void deleteLocalProduct(int id){
        repository.deleteLocalProduct(id);
    }

    public LiveData<List<Product>> getAllLocalProducts(){
        return repository.getAllLocalProducts();
    }

}
