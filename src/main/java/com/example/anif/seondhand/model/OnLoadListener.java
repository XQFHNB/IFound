package com.example.anif.seondhand.model;

import com.example.anif.beans.BeanSecondHand;

import java.util.List;

/**
 * Created by XQF on 2017/4/19.
 */
public interface OnLoadListener {
    public void onSucess(List<BeanSecondHand> mList);

    public void onFail();
}
