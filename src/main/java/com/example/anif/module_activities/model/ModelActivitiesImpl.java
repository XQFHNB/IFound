package com.example.anif.module_activities.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVSaveOption;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.example.anif.beans.BeanActivities;
import com.example.anif.beans.BeanGroup;
import com.example.anif.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XQF
 * @created 2017/6/17
 */
public class ModelActivitiesImpl implements IModelActivities {
    @Override
    public void loadItemList(final OnLoadListener listener) {
        AVQuery<AVObject> avQuery = new AVQuery<>(Constants.BEAN_KEY_ACTIVITY_TABLE);
        avQuery.orderByDescending(Constants.AVOBJECT_KEY_CREATEDAT);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    ArrayList<BeanActivities> mList = new ArrayList<>();
                    for (AVObject av : list) {
                        BeanActivities bean = new BeanActivities();
                        bean.setTitle((String) av.get(Constants.BEAN_KEY_GROUP_TITLE));
                        bean.setDescription((String) av.get(Constants.BEAN_KEY_GROUP_DESCRIPTION));
                        bean.setLabel((String) av.get(Constants.BEAN_KEY_GROUP_LABEL));
                        bean.setContact((String) av.get(Constants.BEAN_KEY_GROUP_CONTACT));
                        bean.setPublishTime(av.getCreatedAt());
                        mList.add(bean);
                    }
                    listener.onSucess(mList);
                }
            }
        });
    }

    @Override
    public void loadItemList(String label, final OnLoadListener listener) {
        AVQuery<AVObject> avQuery = new AVQuery<>(Constants.BEAN_KEY_ACTIVITY_TABLE);
        avQuery.orderByDescending(Constants.AVOBJECT_KEY_CREATEDAT);
        avQuery.whereContains(Constants.BEAN_KEY_ACTIVITY_LABEL, label);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    ArrayList<BeanActivities> mList = new ArrayList<>();
                    for (AVObject av : list) {
                        BeanActivities bean = new BeanActivities();
                        bean.setTitle((String) av.get(Constants.BEAN_KEY_GROUP_TITLE));
                        bean.setDescription((String) av.get(Constants.BEAN_KEY_GROUP_DESCRIPTION));
                        bean.setLabel((String) av.get(Constants.BEAN_KEY_GROUP_LABEL));
                        AVFile avFile = av.getAVFile(Constants.BEAN_KEY_ACTIVITY_IMAGE);
                        if (null != avFile) {
                            bean.setImageUrl(avFile.getUrl());
                        }
                        bean.setPublishTime(av.getCreatedAt());
                        mList.add(bean);
                    }
                    listener.onSucess(mList);
                }
            }
        });
    }

    @Override
    public void saveData(AVObject avObject, final OnSaveListener listener) {
        avObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                listener.onSucess(e);
            }
        });
    }
}
