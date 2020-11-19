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

public class VerticalProductAdapter extends RecyclerView.Adapter<VerticalProductAdapter.ViewHolder> {
    private List<Product> products;

    public VerticalProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_vertical, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvProductSold.setText(String.valueOf(product.getSold()));
        holder.tvProductPrice.setText(String.valueOf(product.getPrice()));
        holder.tvProductName.setText(product.getName());
        Glide.with(holder.imgProductAvatar).load(product.getAvatar()).placeholder(R.mipmap.ic_launcher).into(holder.imgProductAvatar);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProductPrice, tvProductSold;
        ImageView imgProductAvatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            tvProductSold = itemView.findViewById(R.id.tv_sold_products);
            imgProductAvatar = itemView.findViewById(R.id.img_product_avatar);
        }
    }
}
