package com.example.anif.beans;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.example.anif.utils.Constants;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class BeanSecondHand implements Serializable, BeanCommon {

    /**
     * 发布时间，标签代表字符串，标题，描述,价格，照片信息
     */
    private Date mPublishTime;
    private String mLabel;
    private String mTitle;
    private String mGoodDescription;
    private String mGoodPrice;
    private String mImageUrl;
    private String mContact;


    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        mContact = contact;
    }

    public MyUser getMyUser() {
        return mMyUser;
    }

    public void setMyUser(MyUser myUser) {
        mMyUser = myUser;
    }

    private MyUser mMyUser;

    private AVFile mAVFile;

    public AVFile getAVFile() {
        return mAVFile;
    }

    public void setAVFile(AVFile AVFile) {
        mAVFile = AVFile;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getGoodDescription() {
        return mGoodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        mGoodDescription = goodDescription;
    }

    public String getGoodPrice() {
        return mGoodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        mGoodPrice = goodPrice;
    }

    public Date getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(Date publishTime) {
        mPublishTime = publishTime;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public String getCommonType() {
        return Constants.BEAN_KEY_SECONDHAND_TABLE;
    }

    @Override
    public String getCommonTitle() {
        return getTitle();
    }

    @Override
    public String getCommonDescription() {
        return getGoodDescription();
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescriptionShort() {
        return mGoodDescription;
    }

    public void setDescriptionShort(String descriptionShort) {
        mGoodDescription = descriptionShort;
    }

    @Override
    public String toString() {
        return getTitle() + "   " + getCommonType();
    }
}
