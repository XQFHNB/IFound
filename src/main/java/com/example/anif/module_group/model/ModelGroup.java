package com.example.anif.module_group.model;

import com.avos.avoscloud.AVObject;

/**
 * Created by XQF on 2017/5/3.
 */
public interface ModelGroup {
    void loadData(OnLoadListener listener);

    void loadData(OnLoadListener listener, String label);

    void saveData(AVObject avObject, OnSaveListener listener);
}
