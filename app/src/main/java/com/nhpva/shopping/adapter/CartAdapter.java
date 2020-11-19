package com.nhpva.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhpva.shopping.R;
import com.nhpva.shopping.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<Product> products;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductSupplier.setText(product.getSupplier());
        holder.tvProductNumber.setText(String.valueOf(product.getNumber()));
        Glide.with(holder.imgProductAvatar).load(product.getAvatar()).placeholder(R.mipmap.ic_launcher).into(holder.imgProductAvatar);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProductSupplier, tvProductPrice, tvProductNumber;
        ImageView imgProductAvatar, imgAdd, imgSub, imgRemoveProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductSupplier = itemView.findViewById(R.id.tv_product_supplier);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            tvProductNumber = itemView.findViewById(R.id.tv_number_of_product);
            imgProductAvatar = itemView.findViewById(R.id.img_product_avatar);
            imgAdd = itemView.findViewById(R.id.img_add);
            imgSub = itemView.findViewById(R.id.img_sub);
            imgRemoveProduct = itemView.findViewById(R.id.img_remove_product);
        }
    }
}
