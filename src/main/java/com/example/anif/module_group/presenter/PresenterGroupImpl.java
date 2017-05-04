package com.example.anif.module_group.presenter;

import com.example.anif.beans.BeanGroup;
import com.example.anif.module_group.model.ModelGroup;
import com.example.anif.module_group.model.ModelGroupImpl;
import com.example.anif.module_group.model.OnLoadListener;
import com.example.anif.module_group.model.OnSaveListener;
import com.example.anif.module_group.view.ViewGroups;
import com.example.anif.module_group.view.ViewPublishGroups;

import java.util.List;

/**
 * @author XQF
 * @created 2017/5/3
 */
public class PresenterGroupImpl implements PresenterGroup {

    private ModelGroup mModelGroup;
    private ViewGroups mViewGroups;
    private ViewPublishGroups mViewPublishGroups;

    public PresenterGroupImpl(ViewGroups viewGroups) {
        mModelGroup = new ModelGroupImpl();
        mViewGroups = viewGroups;
    }

    public PresenterGroupImpl(ViewPublishGroups viewPublishGroups) {
        mModelGroup = new ModelGroupImpl();
        mViewPublishGroups = viewPublishGroups;
    }

    @Override
    public void loadItemlist() {
        mModelGroup.loadData(new OnLoadListener() {
            @Override
            public void onSucess(List<BeanGroup> list) {
                mViewGroups.addItems(list);
            }
        });
    }

    @Override
    public void saveData(OnSaveListener listener) {
        mModelGroup.saveData(mViewPublishGroups.saveData(), listener);
    }
}
