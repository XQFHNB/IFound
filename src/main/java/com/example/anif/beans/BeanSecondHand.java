package com.example.anif.beans;

import java.io.Serializable;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class BeanSecondHand implements Serializable {

    /**
     * 发布时间，标签代表字符串，标题，描述,价格，照片信息
     */
    private String mPublishTime;
    private String mLabel;
    private String mTitle;
    private String mGoodDescription;
    private String mGoodPrice;
    private String mImageUrl;

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

    public String getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(String publishTime) {
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

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescriptionShort() {
        return mGoodDescription;
    }

    public void setDescriptionShort(String descriptionShort) {
        mGoodDescription = descriptionShort;
    }

}
