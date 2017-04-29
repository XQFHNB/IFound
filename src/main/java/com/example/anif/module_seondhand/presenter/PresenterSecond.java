package com.example.anif.module_seondhand.presenter;

import com.example.anif.module_seondhand.model.OnSaveListener;

/**
 * Created by XQF on 2017/4/19.
 */
public interface PresenterSecond {
    void loadItemList();

    void saveItem(OnSaveListener listener);
}
