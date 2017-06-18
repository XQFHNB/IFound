package com.example.anif.module_activities.presenter;


import com.example.anif.module_activities.model.OnSaveListener;

/**
 * Created by XQF on 2017/6/17.
 */
public interface IPresenterActivities {
    void loadItemlist();
    void loadItemlist(String label);

    void saveData(OnSaveListener listener);
}
