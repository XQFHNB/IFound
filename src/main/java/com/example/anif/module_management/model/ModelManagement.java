package com.example.anif.module_management.model;

/**
 * Created by XQF on 2017/5/2.
 */
public interface ModelManagement {
    void loadData(OnLoadListener listener);

    void deleteData(OnDeleteListener listener);
}
