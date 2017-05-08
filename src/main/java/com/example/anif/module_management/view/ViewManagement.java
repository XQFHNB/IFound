package com.example.anif.module_management.view;

import com.avos.avoscloud.AVObject;
import com.example.anif.beans.BeanCommon;

import java.util.List;

/**
 * Created by XQF on 2017/5/2.
 */
public interface ViewManagement {
    void addItem(List<BeanCommon> list, List<AVObject> avList);
}
