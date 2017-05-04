package com.example.anif.utils;

import com.example.anif.R;

import java.util.HashMap;

/**
 * @author XQF
 * @created 2017/4/13
 */
public class Constants {
    public static String[][] ENTRY_TITLESANDSLOGANANDCOLORS =

            {
                    {
                            "Hotels", "All hotels and hostels are sorted by hospitality rating", "#678FB4"
                    },
                    {
                            "Ba0nks", "We carefully verify all banks before add them into the app", "#65B0B4"
                    },
                    {
                            "Stores", "All local stores are categorized for your convenience", "#9B90BC"
                    }
            };

    public static int[] ENTRY_BACKGROUND = {R.drawable.guide1, R.drawable.guide2, R.drawable.avatar};


    public static String[] CONTENT_TITLES = {"二手", "拼组", "活动", "特惠"};
    public static String KEY_TYPE = "type";


    /**
     * 页面选择索引类型
     */

    public static int FRAG_PUBLISH_SECONDHAND = 0;
    public static int FRAG_PUBLISH_GROUP = 1;
    public static int FRAG_PUBLISH_ACTIVITY = 2;
    public static int FRAG_PUBLISH_SALE = 3;


    /**
     * AvObject默认内置的key
     */
    public static String AVOBJECT_KEY_CREATEDAT = "createdAt";


    /**
     * 二手界面涉及的key
     */
    public static String BEAN_KEY_SECONDHAND_TABLE = "secondhand";
    public static String BEAN_KEY_SECONDHAND_TITLE = "title";
    public static String BEAN_KEY_SECONDHAND_DESCRIPTION = "description";
    public static String BEAN_KEY_SECONDHAND_LABEL = "label";
    public static String BEAN_KEY_SECONDHAND_CONTACT = "contact";
    public static String BEAN_KEY_SECONDHAND_IMAGE = "images";
    public static String BEAN_KEY_SECONDHAND_OWNER = "owner";
    public static String BEAN_KEY_SECONDHAND_AVFILE = "secondhandImage.png";


    public static HashMap<String, Integer> LABEL_SECONDHAND_MAP_IMAGE = new HashMap<>();

    static {
        LABEL_SECONDHAND_MAP_IMAGE.put("11", R.drawable.label_second_books);
        LABEL_SECONDHAND_MAP_IMAGE.put("12", R.drawable.label_second_clothing);
        LABEL_SECONDHAND_MAP_IMAGE.put("13", R.drawable.label_second_discount);
        LABEL_SECONDHAND_MAP_IMAGE.put("21", R.drawable.label_second_equbment_electric);
        LABEL_SECONDHAND_MAP_IMAGE.put("22", R.drawable.label_second_life_electric);
        LABEL_SECONDHAND_MAP_IMAGE.put("23", R.drawable.label_second_other);
        LABEL_SECONDHAND_MAP_IMAGE.put("31", R.drawable.label_second_parts);
        LABEL_SECONDHAND_MAP_IMAGE.put("32", R.drawable.label_second_skin);
        LABEL_SECONDHAND_MAP_IMAGE.put("33", R.drawable.label_second_sports);
    }

    public static HashMap<String, String> LABEL_SECONDHAND_MAP_IMAGE_TEXT = new HashMap<>();

    static {
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("11", "书籍资料");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("12", "衣物箱包");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("13", "优惠卡券");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("21", "电子设备");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("22", "生活家电");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("23", "其他");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("31", "配件外设");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("32", "美颜护肤");
        LABEL_SECONDHAND_MAP_IMAGE_TEXT.put("33", "运动器材");
    }


    /**
     * 拼组界面涉及的key
     */
    public static String BEAN_KEY_GROUP_TABLE = "group";
    public static String BEAN_KEY_GROUP_TITLE = "title";
    public static String BEAN_KEY_GROUP_DESCRIPTION = "description";
    public static String BEAN_KEY_GROUP_LABEL = "label";
    public static String BEAN_KEY_GROUP_CONTACT = "contact";
    public static String BEAN_KEY_GROUP_OWNER = "owner";


    public static HashMap<String, String> LABEL_GROUP_MAP_IMAGE_TEXT = new HashMap<>();

    static {
        LABEL_GROUP_MAP_IMAGE_TEXT.put("1", "拼车");
        LABEL_GROUP_MAP_IMAGE_TEXT.put("2", "竞赛");
        LABEL_GROUP_MAP_IMAGE_TEXT.put("3", "凑单");
        LABEL_GROUP_MAP_IMAGE_TEXT.put("4", "旅游");
        LABEL_GROUP_MAP_IMAGE_TEXT.put("5", "其他");
    }

    /**
     * 管理界面涉及的key
     */
    public static HashMap<String, Integer> MAP_TYPE_PROJECT = new HashMap<>();

    static {
        MAP_TYPE_PROJECT.put(Constants.BEAN_KEY_SECONDHAND_TABLE, R.drawable.label_second_books);
    }


}
