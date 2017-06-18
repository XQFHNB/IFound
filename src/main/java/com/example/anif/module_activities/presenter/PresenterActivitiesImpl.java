package com.example.anif.module_activities.presenter;

import com.example.anif.beans.BeanActivities;
import com.example.anif.module_activities.model.IModelActivities;
import com.example.anif.module_activities.model.ModelActivitiesImpl;
import com.example.anif.module_activities.model.OnLoadListener;
import com.example.anif.module_activities.view.IViewActivities;
import com.example.anif.module_activities.view.IViewPublishActivities;
import com.example.anif.module_activities.model.OnSaveListener;

import java.util.List;

/**
 * @author XQF
 * @created 2017/6/17
 */
public class PresenterActivitiesImpl implements IPresenterActivities {

    private IModelActivities mIModelActivities;
    private IViewActivities mIViewActivities;
    private IViewPublishActivities mIViewPublishActivities;

    public PresenterActivitiesImpl(IViewActivities iViewActivities) {
        mIModelActivities = new ModelActivitiesImpl();
        mIViewActivities = iViewActivities;
    }

    public PresenterActivitiesImpl(IViewPublishActivities iViewPublishActivities) {
        mIModelActivities = new ModelActivitiesImpl();
        mIViewPublishActivities = iViewPublishActivities;
    }

    @Override
    public void loadItemlist() {
        mIModelActivities.loadItemList(new OnLoadListener() {
            @Override
            public void onSucess(List<BeanActivities> list) {
                mIViewActivities.addItems(list);
            }
        });

    }

    @Override
    public void loadItemlist(String label) {
        mIModelActivities.loadItemList(label, new OnLoadListener() {
            @Override
            public void onSucess(List<BeanActivities> list) {
                mIViewActivities.addItems(list);
            }
        });
    }


    @Override
    public void saveData(OnSaveListener listener) {
        mIModelActivities.saveData(mIViewPublishActivities.saveData(), listener);
    }
}
