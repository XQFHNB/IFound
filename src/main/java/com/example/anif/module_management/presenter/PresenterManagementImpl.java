package com.example.anif.module_management.presenter;

import com.avos.avoscloud.AVObject;
import com.example.anif.beans.BeanCommon;
import com.example.anif.module_management.model.ModelManagement;
import com.example.anif.module_management.model.ModelManagementImpl;
import com.example.anif.module_management.model.OnDeleteListener;
import com.example.anif.module_management.model.OnLoadListener;
import com.example.anif.module_management.view.ViewManagement;
import com.example.anif.utils.UtilLog;

import java.util.Arrays;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/2
 */
public class PresenterManagementImpl implements PresenterManagement {
    private ModelManagement mModelManagement;
    private ViewManagement mViewManagement;

    public PresenterManagementImpl(ViewManagement viewManagement) {
        mModelManagement = new ModelManagementImpl();
        mViewManagement = viewManagement;
    }

    @Override
    public void loadData() {
        mModelManagement.loadData(new OnLoadListener() {
            @Override
            public void onSucess(List<BeanCommon> list, List<AVObject> avlist) {
                mViewManagement.addItem(list, avlist);
            }
        });
    }

    @Override
    public void deleteItem(AVObject avObject, OnDeleteListener listener) {
        mModelManagement.deleteData(avObject, listener);
    }
}
