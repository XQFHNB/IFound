package com.example.anif.seondhand.model;

import com.example.anif.beans.MyUser;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class ModelSecondImpl implements ModelSecond {

    @Override
    public void loadData(OnLoadListener listener) {
        MyUser user = MyUser.getCurrentUser();
    }
}
