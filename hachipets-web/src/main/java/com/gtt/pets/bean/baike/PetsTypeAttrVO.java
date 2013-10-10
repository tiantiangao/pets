package com.gtt.pets.bean.baike;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-10
 * Time: 下午10:12
 * To change this template use File | Settings | File Templates.
 */
public class PetsTypeAttrVO {

    private String name;
    private String value;
    private boolean singleLine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSingleLine() {
        return singleLine;
    }

    public void setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
    }
}
