package com.example.anif.module_seondhand.presenter;

import com.example.anif.beans.BeanSecondHand;
import com.example.anif.module_seondhand.model.ModelSecond;
import com.example.anif.module_seondhand.model.ModelSecondImpl;
import com.example.anif.module_seondhand.model.OnLoadListener;
import com.example.anif.module_seondhand.model.OnSaveListener;
import com.example.anif.module_seondhand.view.ViewPublishSecond;
import com.example.anif.module_seondhand.view.ViewSecond;
import com.example.anif.utils.UtilLog;

import java.util.Arrays;
import java.util.List;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class PresenterSecondImpl implements PresenterSecond {

    private ModelSecond mModelSecond;
    private ViewSecond mViewSecond;
    private ViewPublishSecond mViewPublishSecond;


    public PresenterSecondImpl(ViewSecond view) {
        mViewSecond = view;
        mModelSecond = new ModelSecondImpl();
    }

    public PresenterSecondImpl(ViewPublishSecond view) {
        mViewPublishSecond = view;
        mModelSecond = new ModelSecondImpl();
    }


    /**
     * 拉取数据
     */
    @Override
    public void loadItemList() {
        mModelSecond.loadData(new OnLoadListener() {
            @Override
            public void onSucess(List<BeanSecondHand> mList) {
                UtilLog.d("test", "测试一下3 " + Arrays.toString(mList.toArray()));
                mViewSecond.addItems(mList);
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 上传数据
     */
    @Override
    public void saveItem(OnSaveListener listener) {
        mModelSecond.saveData(mViewPublishSecond.saveData(), listener);
    }
}
