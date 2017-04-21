package com.example.anif.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anif.R;
import com.example.anif.base.FragBase;

import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class FragActivities extends FragBase {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content_common_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
