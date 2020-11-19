package com.nhpva.shopping.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nhpva.shopping.ui.AccountFragment;
import com.nhpva.shopping.ui.GiftFragment;
import com.nhpva.shopping.ui.HomeFragment;
import com.nhpva.shopping.ui.MessageFragment;
import com.nhpva.shopping.ui.NotificationFragment;

public class MainPagerAdapter  extends FragmentPagerAdapter {
    private String[] titles = new String[]{"Home", "Gift", "Message", "Voucher", "Account"};
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeFragment.getInstance();
            case 1:
                return GiftFragment.getInstance();
            case 2:
                return MessageFragment.getInstance();
            case 3:
                return NotificationFragment.getInstance();
            case 4:
                return AccountFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
