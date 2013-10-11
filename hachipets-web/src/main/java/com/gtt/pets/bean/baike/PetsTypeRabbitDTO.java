package com.gtt.pets.bean.baike;

import com.gtt.pets.bean.BaseDTO;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */
public class PetsTypeRabbitDTO extends BaseDTO {

    /**
     * 主类型ID
     */
    private int typeId;

    /**
     * 体重
     */
    private String weight;

    /**
     * 体型
     */
    private String bodyType;

    /**
     * 食性
     */
    private String eatPattern;

    /**
     * 其他信息
     */
    private String other;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

	public String getEatPattern() {
		return eatPattern;
	}

	public void setEatPattern(String eatPattern) {
		this.eatPattern = eatPattern;
	}

	public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
