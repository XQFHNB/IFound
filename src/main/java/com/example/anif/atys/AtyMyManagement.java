package com.example.anif.atys;

import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;
import com.example.anif.module_management.widgets.FragMyManagement;

/**
 * @author XQF
 * @created 2017/5/3
 */
public class AtyMyManagement extends AtyBase {


    @Override
    public Fragment createFrag() {
        return FragMyManagement.newInstance();
    }
}
