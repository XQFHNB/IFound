package com.example.anif.seondhand.presenter;

import com.example.anif.beans.BeanSecondHand;
import com.example.anif.seondhand.model.ModelSecond;
import com.example.anif.seondhand.model.ModelSecondImpl;
import com.example.anif.seondhand.model.OnLoadListener;
import com.example.anif.seondhand.view.ViewSecond;

import java.util.List;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class PresenterSecondImpl implements PresenterSecond {

    private ModelSecond mModelSecond;
    private ViewSecond mViewSecond;

    public PresenterSecondImpl(ViewSecond view) {
        mViewSecond = view;
        mModelSecond = new ModelSecondImpl();
    }


    @Override
    public void loadItemList() {
        mModelSecond.loadData(new OnLoadListener() {
            @Override
            public void onSucess(List<BeanSecondHand> mList) {
                mViewSecond.addItems(mList);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
