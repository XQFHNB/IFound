package com.example.anif.module_seondhand.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.example.anif.beans.BeanSecondHand;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class ModelSecondImpl implements ModelSecond {

    @Override
    public void loadData(final OnLoadListener listener) {
        //查询secondhand表,返回表中的所有AVObject,从中取出所有的BeanSecondHand传出去
        AVQuery<AVObject> avQuery = new AVQuery<>(Constants.BEAN_KEY_SECONDHAND_TABLE);
        avQuery.orderByDescending(Constants.AVOBJECT_KEY_CREATEDAT);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                UtilLog.d("test", "测试一下" + Arrays.toString(list.toArray()));

                if (e == null) {
                    ArrayList<BeanSecondHand> mList = new ArrayList<>();
                    for (AVObject av : list) {
                        BeanSecondHand bean = new BeanSecondHand();
                        bean.setTitle((String) av.get(Constants.BEAN_KEY_SECONDHAND_TITLE));
                        bean.setGoodDescription((String) av.get(Constants.BEAN_KEY_SECONDHAND_DESCRIPTION));
                        bean.setLabel((String) av.get(Constants.BEAN_KEY_SECONDHAND_LABEL));
                        bean.setContact((String) av.get(Constants.BEAN_KEY_SECONDHAND_CONTACT));
                        bean.setPublishTime(av.getCreatedAt().toString());
                        AVFile avFile = av.getAVFile(Constants.BEAN_KEY_SECONDHAND_IMAGE);
                        if (avFile != null) {
                            bean.setImageUrl(avFile.getUrl());
                        }
                        UtilLog.d("test", "测试一下1 " + bean);
                        mList.add(bean);
                    }
                    UtilLog.d("test", "测试一下2 " + mList.size());

                    listener.onSucess(mList);
                }
            }
        });
    }

    @Override
    public void saveData(AVObject avObject, final OnSaveListener listener) {
        avObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                listener.onFail(e);
            }
        });
    }
}
