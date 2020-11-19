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

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder> {
    private List<Product> products;

    public HorizontalProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvProductPrice.setText(String.valueOf(product.getPrice()));
        Glide.with(holder.imgProductAvatar).load(product.getAvatar()).placeholder(R.mipmap.ic_launcher).into(holder.imgProductAvatar);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProductAvatar;
        TextView tvProductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductAvatar = itemView.findViewById(R.id.img_product_avatar);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
        }
    }
}
