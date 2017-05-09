package com.example.anif.module_management.model;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.example.anif.beans.BeanCommon;
import com.example.anif.beans.BeanGroup;
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
        final List<BeanCommon> result = new ArrayList<>();
        final List<AVObject> resultAvlist = new ArrayList<>();


        // TODO: 2017/5/4 给所有的查询添加用户条件
        /**
         * 查询二手表
         */
        AVQuery<AVObject> avQuery = new AVQuery<>(Constants.BEAN_KEY_SECONDHAND_TABLE);
        avQuery.orderByDescending(Constants.AVOBJECT_KEY_CREATEDAT);
        avQuery.include("owner");
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
                        String ID = av.getAVUser("owner").getObjectId();
                        if (ID.equals(MyUser.getCurrentUser().getObjectId())) {
                            UtilLog.d("testname", "secondhand: " + ID);
                            result.add(bean);
                            resultAvlist.add(av);
                        }
                        UtilLog.d("test1", "为什么？" + bean.toString());
                    }
                    UtilLog.d("testresult", Arrays.toString(result.toArray()));
                    listener.onSucess(result, resultAvlist);
                }
            }
        });

        /**
         * 查询组队表
         */

        AVQuery<AVObject> avQuery1 = new AVQuery<>(Constants.BEAN_KEY_GROUP_TABLE);
        avQuery1.orderByDescending(Constants.AVOBJECT_KEY_CREATEDAT);
        avQuery1.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    for (AVObject av : list) {
                        BeanGroup bean = new BeanGroup();
                        bean.setTitle((String) av.get(Constants.BEAN_KEY_GROUP_TITLE));
                        bean.setDescription((String) av.get(Constants.BEAN_KEY_GROUP_DESCRIPTION));
                        bean.setLabel((String) av.get(Constants.BEAN_KEY_GROUP_LABEL));
                        bean.setContact((String) av.get(Constants.BEAN_KEY_GROUP_CONTACT));
                        bean.setPublishTime(av.getCreatedAt());
                        String ID = av.getAVUser("owner").getObjectId();

                        UtilLog.d("testname", "group: " + ID);
                        if (ID.equals(MyUser.getCurrentUser().getObjectId())) {
                            result.add(bean);
                            resultAvlist.add(av);
                        }
                    }
                    UtilLog.d("testresult", Arrays.toString(result.toArray()));
                }
            }
        });
    }


    @Override
    public void deleteData(AVObject avObject, final OnDeleteListener listener) {
        avObject.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(AVException e) {
                listener.onSucess(e);
            }
        });

    }
}
