package com.example.anif.module_management.model;

import com.avos.avoscloud.AVObject;

/**
 * Created by XQF on 2017/5/2.
 */
public interface ModelManagement {
    void loadData(OnLoadListener listener);

    void deleteData(AVObject avObject, OnDeleteListener listener);
}
