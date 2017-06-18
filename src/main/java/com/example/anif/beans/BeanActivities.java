package com.example.anif.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class BeanActivities  implements Serializable,BeanCommon{

    private String mTitle;
    private String mDescription;
    private String mLabel;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    private String mContact;
    private String mImageUrl;


    public Date getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(Date publishTime) {
        mPublishTime = publishTime;
    }

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

    @Override
    public String getCommonType() {
        return null;
    }

    @Override
    public String getCommonTitle() {
        return null;
    }

    @Override
    public String getCommonDescription() {
        return null;
    }
}
