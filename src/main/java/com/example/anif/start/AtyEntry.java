package com.example.anif.start;

import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;

/**
 * @author XQF
 * @created 2017/4/13
 */
public class AtyEntry extends AtyBase {
    @Override
    public Fragment createFrag() {
        getSupportActionBar().hide();
        return FragEntry.newInstance();
    }
}
