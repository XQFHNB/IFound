package com.example.anif.main;

import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;

/**
 * @author XQF
 * @created 2017/4/17
 */
public class AtyEditLogin extends AtyBase {
    @Override
    public Fragment createFrag() {
        return new FragEditLogin();
    }

  
}
