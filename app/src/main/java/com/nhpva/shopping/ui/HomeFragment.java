package com.nhpva.shopping.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nhpva.shopping.R;
import com.nhpva.shopping.adapter.HorizontalProductAdapter;
import com.nhpva.shopping.adapter.PageIndicatorAdapter;
import com.nhpva.shopping.adapter.VerticalProductAdapter;
import com.nhpva.shopping.model.Product;
import com.nhpva.shopping.viewmodel.ProductViewModel;
import com.nhpva.shopping.viewmodel.ProductViewModelFactory;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static HomeFragment INSTANCE;
    private ProductViewModel viewModel;
    private List<Product> mProducts;
    private List<String> imgEvents;
    private HorizontalProductAdapter horizontalProductAdapter;
    private VerticalProductAdapter verticalProductAdapter;
    private RecyclerView rvSellingProduct;
    private RecyclerView rvFlashSale;
    private RecyclerView rvRecommend;
    private ViewPager vpEvents;
    private DotsIndicator dotsIndicator;
    private PageIndicatorAdapter pageIndicatorAdapter;
    private ImageView imgCart;

    public static HomeFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HomeFragment();
        }
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        imgEvents = new ArrayList<>();
        pageIndicatorAdapter = new PageIndicatorAdapter(imgEvents, getContext());
        vpEvents.setAdapter(pageIndicatorAdapter);
        dotsIndicator.setViewPager(vpEvents);
//        viewModel = new ViewModelProvider(requireActivity(), new ProductViewModelFactory(requireActivity().getApplication())).get(ProductViewModel.class);
//        viewModel.getRemoteImageEvents().observe(getViewLifecycleOwner(), images -> {
//            imgEvents.clear();
//            imgEvents.addAll(images);
//            pageIndicatorAdapter.notifyDataSetChanged();
//        });
//
//        mProducts = new ArrayList<>();
//        horizontalProductAdapter = new HorizontalProductAdapter(mProducts);
//        verticalProductAdapter = new VerticalProductAdapter(mProducts);
//
//        viewModel.getRemoteProducts().observe(getViewLifecycleOwner(), products -> {
//            mProducts.clear();
//            mProducts.addAll(products);
//            verticalProductAdapter.notifyDataSetChanged();
//            horizontalProductAdapter.notifyDataSetChanged();
//        });
//
//        viewModel.getRemoteData();
//
//        rvFlashSale.setAdapter(horizontalProductAdapter);
//        rvSellingProduct.setAdapter(horizontalProductAdapter);
//        rvRecommend.setAdapter(verticalProductAdapter);
//        rvFlashSale.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        rvSellingProduct.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        rvRecommend.setLayoutManager(new GridLayoutManager(getContext(), 2));

        imgCart.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity().getApplication(), CartActivity.class);
            startActivity(intent);
        });
        return view;
    }

    private void initViews(View view) {
        imgCart = view.findViewById(R.id.img_cart);
        dotsIndicator = view.findViewById(R.id.dots_indicator_event);
        vpEvents = view.findViewById(R.id.vp_event);
        rvSellingProduct = view.findViewById(R.id.rv_selling_products);
        rvFlashSale = view.findViewById(R.id.rv_flash_sale);
        rvRecommend = view.findViewById(R.id.rv_recommend);
    }
}