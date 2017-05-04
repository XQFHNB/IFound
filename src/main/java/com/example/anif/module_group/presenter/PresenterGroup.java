package com.example.anif.module_group.presenter;


import com.example.anif.module_group.model.OnLoadListener;
import com.example.anif.module_group.model.OnSaveListener;

/**
 * Created by XQF on 2017/5/3.
 */
public interface PresenterGroup {
    void loadItemlist();

    void loadItemlist(String label);

    void saveData(OnSaveListener listener);
}
