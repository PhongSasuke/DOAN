package com.example.doan.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.MapsFragment;
import com.example.doan.R;

public class HomeFragment extends Fragment {

    private MapsFragment mMapsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Tạo đối tượng MapsFragment
        mMapsFragment = new MapsFragment();

        // Hiển thị MapsFragment trực tiếp
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.map, mMapsFragment); // Thay thế bằng ID container trong layout
        transaction.commit();

        return view;
    }
}
