package com.example.anif.atys;

import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;
import com.example.anif.frags.FragMain;

/**
 * @author XQF
 * @created 2017/4/17
 */
public class AtyMain extends AtyBase {

    @Override
    public Fragment createFrag() {
        return new FragMain();
    }
//    @Override
//    public void onBackPressed() {
//        long now = System.currentTimeMillis();
//        if (now - mCurrentTime < timeInterval) {
//            super.onBackPressed();
//        } else {
//            toast(this, "双击退出");
//        }
//        mCurrentTime = now;
//    }
}
