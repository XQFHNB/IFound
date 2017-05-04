package com.example.anif.beans;

import com.example.anif.utils.Constants;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class BeanGroup implements Serializable, BeanCommon {

    private String mTitle;
    private String mDescription;
    private String mLabel;
    private String mContact;

    private Date mPublishTime;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        mContact = contact;
    }

    public Date getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(Date publishTime) {
        mPublishTime = publishTime;
    }

    @Override
    public String getCommonType() {
        return Constants.BEAN_KEY_GROUP_TABLE;
    }

    @Override
    public String getCommonTitle() {
        return getTitle();
    }

    @Override
    public String getCommonDescription() {
        return getDescription();
    }

    @Override
    public String toString() {
        return getTitle() + "   " + getCommonType();
    }
}
