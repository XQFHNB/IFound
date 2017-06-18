package com.example.anif.atys;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.anif.base.AtyBase;
import com.example.anif.module_activities.widgets.FragPublishActivities;
import com.example.anif.module_group.widgets.FragPublishGroup;
import com.example.anif.module_seondhand.widgets.FragPublishSecondhand;
import com.example.anif.utils.Constants;

/**
 * @author XQF
 * @created 2017/4/21
 */
public class AtyPublish extends AtyBase {
    public int mType;

    public static void start(Context context, Class<?> cls, int type) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    /**
     * 根据不同的类型创建不同的发布界面
     *
     * @return
     */
    @Override
    public Fragment createFrag() {
        mType = getIntent().getIntExtra("type", -1);
        Fragment fragmentPublish = null;
        if (mType == Constants.FRAG_PUBLISH_SECONDHAND) {
            fragmentPublish = new FragPublishSecondhand();
        } else if (mType == Constants.FRAG_PUBLISH_GROUP) {
            fragmentPublish = new FragPublishGroup();
        } else if (mType == Constants.FRAG_PUBLISH_ACTIVITY) {
            fragmentPublish = new FragPublishActivities();
        } else if (mType == Constants.FRAG_PUBLISH_SALE) {

        }
        return fragmentPublish;
    }
}
