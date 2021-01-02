package com.rotimijohnson.moderndashboardscreen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rotimijohnson.moderndashboardscreen.R;
import com.rotimijohnson.moderndashboardscreen.fragment.listener.imageClicked;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class homeFragment extends Fragment {

   imageClicked imageClick;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.toolbar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               imageClick.click();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        imageClick = (imageClicked)getActivity();
    }
}
