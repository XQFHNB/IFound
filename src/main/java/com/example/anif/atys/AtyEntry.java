package com.example.anif.atys;

import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;
import com.example.anif.frags.FragEntry;

/**
 * @author XQF
 * @created 2017/4/13
 */
public class AtyEntry extends AtyBase {
    @Override
    public Fragment createFrag() {
//        getSupportActionBar().hide();
        return FragEntry.newInstance();
    }
}
