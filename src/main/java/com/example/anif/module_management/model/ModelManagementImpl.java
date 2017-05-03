package com.example.anif.module_management.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.example.anif.beans.BeanCommon;
import com.example.anif.beans.BeanSecondHand;
import com.example.anif.beans.MyUser;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XQF
 * @created 2017/5/2
 */
public class ModelManagementImpl implements ModelManagement {

    @Override
    public void loadData(final OnLoadListener listener) {
        AVQuery<AVObject> avQuery = new AVQuery<>(Constants.BEAN_KEY_SECONDHAND_TABLE);
        avQuery.orderByDescending(Constants.AVOBJECT_KEY_CREATEDAT);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    List<BeanCommon> result = new ArrayList<>();
                    for (AVObject av : list) {
                        BeanSecondHand bean = new BeanSecondHand();
                        bean.setTitle((String) av.get(Constants.BEAN_KEY_SECONDHAND_TITLE));
                        bean.setGoodDescription((String) av.get(Constants.BEAN_KEY_SECONDHAND_DESCRIPTION));
                        bean.setLabel((String) av.get(Constants.BEAN_KEY_SECONDHAND_LABEL));
                        bean.setContact((String) av.get(Constants.BEAN_KEY_SECONDHAND_CONTACT));
                        bean.setPublishTime(av.getCreatedAt());
                        AVFile avFile = av.getAVFile(Constants.BEAN_KEY_SECONDHAND_IMAGE);
                        if (avFile != null) {
                            bean.setImageUrl(avFile.getUrl());
                        }
                        result.add(bean);
                        UtilLog.d("test1", "为什么？" + bean.toString());
                    }
                    listener.onSucess(result);
                }
            }
        });
    }

    private List<BeanCommon> queryTableSale() {
        return null;
    }

    private List<BeanCommon> queryTableGroup() {
        return null;
    }

    private List<BeanCommon> queryTableActivities() {
        return null;
    }

    private void queryTableSecondhand() {
//        final List<BeanCommon> mList = new ArrayList<>();
        AVQuery<AVObject> avQuery = new AVQuery<>(Constants.BEAN_KEY_SECONDHAND_TABLE);
        avQuery.orderByDescending(Constants.AVOBJECT_KEY_CREATEDAT);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    for (AVObject av : list) {
                        BeanSecondHand bean = new BeanSecondHand();
                        bean.setTitle((String) av.get(Constants.BEAN_KEY_SECONDHAND_TITLE));
                        bean.setGoodDescription((String) av.get(Constants.BEAN_KEY_SECONDHAND_DESCRIPTION));
                        bean.setLabel((String) av.get(Constants.BEAN_KEY_SECONDHAND_LABEL));
                        bean.setContact((String) av.get(Constants.BEAN_KEY_SECONDHAND_CONTACT));
                        bean.setPublishTime(av.getCreatedAt());
                        AVFile avFile = av.getAVFile(Constants.BEAN_KEY_SECONDHAND_IMAGE);
                        if (avFile != null) {
                            bean.setImageUrl(avFile.getUrl());
                        }
//                        result.add(bean);
                        UtilLog.d("test1", bean.toString());
                    }
                }
            }
        });
    }

    @Override
    public void deleteData(OnDeleteListener listener) {

    }
}
