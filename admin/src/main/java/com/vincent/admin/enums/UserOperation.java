package com.vincent.admin.enums;

import com.vincent.admin.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/4/17 16:32
 */
public enum UserOperation {
    VISIT_PAGE("浏览页面", "visit"),
    VISIT_ABOUT_ME("浏览关于我", "visitAboutMe"),
    VISIT_TIME_LINE("浏览时光轴", "visitTimeLine"),
    SEARCH("搜索", "search"),
    LIKE("点赞", "like"),
    PUBLISH_COMMENT("发表评论", "comment");

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getOperation() {
        return operation;
    }

    public static String getAddOn(UserOperation operation, Map<String, Object> nameAndArgsMap, String eventValue) {
        String addOn = "";
        //HashMap<String,String> map = new HashMap<>();
        switch (operation) {
            case LIKE:
                if (nameAndArgsMap.get("uid") != null){
                    addOn = nameAndArgsMap.get("uid").toString();
                }
                break;
            case SEARCH:
                if (nameAndArgsMap.get("keyword") != null){
                    addOn = nameAndArgsMap.get("keyword").toString();
                }
                break;
            case VISIT_PAGE:
                if (nameAndArgsMap.get("link") != null){
                    addOn = nameAndArgsMap.get("link").toString();
                }
                break;
            case PUBLISH_COMMENT:
                Object commentVO = nameAndArgsMap.get("commentVO");
                Map<String, Object> map = JsonUtil.objectToMap(commentVO);
                if (map.get("content") != null){
                    addOn = map.get("content").toString();
                }
            default:
                break;
        }
        return addOn;
    }
    private String description;
    private String operation;
    UserOperation(String description, String operation) {
        this.description = description;
        this.operation = operation;
    }
}
