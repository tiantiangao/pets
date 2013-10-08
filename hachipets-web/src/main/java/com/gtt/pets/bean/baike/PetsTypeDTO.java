package com.gtt.pets.bean.baike;

import com.gtt.pets.bean.BaseDTO;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午8:14
 * To change this template use File | Settings | File Templates.
 */
public class PetsTypeDTO extends BaseDTO {

    /**
     * 所属根分类ID
     */
    private int rootCategoryId;
    /**
     * 所属分类ID
     */
    private int categoryId;
    /**
     * 名称
     */
    private String name;
    /**
     * 英文名称
     */
    private String enName;
    /**
     * 别名
     */
    private String alias;
    /**
     * 描述
     */
    private String desc;
    /**
     * 产地
     */
    private String origin;
    /**
     * 图片地址
     */
    private String picUril;
    /**
     * 缩略图地址
     */
    private String thumbPicUrl;

    public int getRootCategoryId() {
        return rootCategoryId;
    }

    public void setRootCategoryId(int rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPicUril() {
        return picUril;
    }

    public void setPicUril(String picUril) {
        this.picUril = picUril;
    }

    public String getThumbPicUrl() {
        return thumbPicUrl;
    }

    public void setThumbPicUrl(String thumbPicUrl) {
        this.thumbPicUrl = thumbPicUrl;
    }
}
