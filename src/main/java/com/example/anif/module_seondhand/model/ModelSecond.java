package com.example.anif.module_seondhand.model;

import com.avos.avoscloud.AVObject;

/**
 * Created by XQF on 2017/4/19.
 */
public interface ModelSecond {
    void loadData(OnLoadListener listener);

    void saveData(AVObject avObject, OnSaveListener listener);
}
