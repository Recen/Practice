package com.recen.learn.practice3;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recen.dotui.view.SignSeekBar;
import com.recen.learn.R;
import com.recen.learn.base.BaseFragment;
import com.recen.learn.common.activity.GenericFragmentActivity;
import com.recen.learn.common.constants.BundleKey;
import com.recen.learn.practice2.Practice2Fragment;
public class Practice3Fragment extends BaseFragment {

    public static void go(Activity from, String pageName){
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.BUNDLE_PAGENAME,pageName);
        GenericFragmentActivity.start(from, Practice3Fragment.class,bundle);
    }

    String[] sideLabel = {"50单","100单","200单","500单","1000单"};
    float[] sideValuePercent= {0.05f,0.1f,0.2f,0.5f,1};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_practice3, container, false);
        test3(view);
        return view;
    }

    private void test3(View view) {
        SignSeekBar signSeekBar = (SignSeekBar) view.findViewById(R.id.demo_5_seek_bar_3);
        signSeekBar.getConfigBuilder()
                .min(0)
                .max(1000)
                .progress(2)
                .bottomSidesLabels(sideLabel)
                .sidesSectionValues(sideValuePercent)
                .seekBySection(false)
                .sectionCount(sideLabel.length -1)
                .thumbColor(ContextCompat.getColor(getContext(), R.color.color_gray))
                .sectionTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
                .trackColor(ContextCompat.getColor(getContext(), R.color.color_green))
                .secondTrackColor(ContextCompat.getColor(getContext(), R.color.colorAccent))
                .sectionTextSize(16)
                .thumbRadius(20)
                .thumbRadiusOnDragging(20)
                .setUnit("单")
                .sectionTextPosition(SignSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
    }
}
