package com.example.anif.module_activities.model;

import com.avos.avoscloud.AVObject;

/**
 * Created by XQF on 2017/6/17.
 */
public interface IModelActivities {

    void loadItemList(OnLoadListener listener);

    void loadItemList(String label, OnLoadListener listener);

    void saveData(AVObject avObject, OnSaveListener listener);
}
