package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.MainMenuEvent;

public class MainNavigationFragment extends Fragment {

    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_navigation, container, false);

        view.findViewById(R.id.MstartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppMnemoNet.getInstance().getBus().post(new MainMenuEvent());
            }
        });

        relativeLayout = (RelativeLayout) view.findViewById(R.id.galaxyContainer);


//        getNeuronNetworkTheme();

        return view;
    }

//    Experiment with Neuron Network theme
    private void getNeuronNetworkTheme() {
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(28, 28);
        relativeParams.setMargins(240, 240, 0, 0);
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.circle_dark);
        relativeLayout.addView(imageView, relativeParams);
    }

}
