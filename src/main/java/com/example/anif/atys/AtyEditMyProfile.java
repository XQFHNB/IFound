package com.example.anif.atys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;
import com.example.anif.module_management.widgets.FragMyManagement;
import com.example.anif.module_profile.widgets.FragEditMyProfile;

/**
 * @author XQF
 * @created 2017/6/18
 */
public class AtyEditMyProfile extends AtyBase {


    public static void startAtyEditMyProfile(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    @Override
    public Fragment createFrag() {
        return new FragEditMyProfile();
    }
}
