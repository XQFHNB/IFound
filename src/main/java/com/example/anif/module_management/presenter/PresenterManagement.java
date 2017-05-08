package com.example.anif.module_management.presenter;

import com.avos.avoscloud.AVObject;
import com.example.anif.module_management.model.OnDeleteListener;

/**
 * Created by XQF on 2017/5/2.
 */
public interface PresenterManagement {
    void loadData();

    void deleteItem(AVObject avObject, OnDeleteListener listener);
}
