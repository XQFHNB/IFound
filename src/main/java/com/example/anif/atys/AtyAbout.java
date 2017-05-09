package com.example.anif.atys;

import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;
import com.example.anif.frags.FragAbout;

/**
 * @author XQF
 * @created 2017/5/9
 */
public class AtyAbout extends AtyBase {


    @Override
    public Fragment createFrag() {
        return new FragAbout();
    }


}
