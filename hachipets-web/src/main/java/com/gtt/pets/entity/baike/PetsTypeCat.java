package com.gtt.pets.entity.baike;

import com.gtt.pets.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午8:14
 * To change this template use File | Settings | File Templates.
 */
public class PetsTypeCat extends BaseEntity {

    /**
     * 主类型ID
     */
    private int typeId;

    /**
     * 体型
     */
    private String bodyType;

    /**
     * 体重
     */
    private String weight;

    /**
     * CFA记录ID
     */
    private int cfa;

    /**
     * 粘人程度
     */
    private int featureStick;
    /**
     * 初养适应度
     */
    private int featureFeed;
    /**
     * 犬叫程度
     */
    private int featureBark;
    /**
     * 掉毛程度
     */
    private int featureFallHair;
    /**
     * 体味程度
     */
    private int featureOdor;
    /**
     * 美容程度
     */
    private int featureBeauty;
    /**
     * 对孩子友善
     */
    private int featureChildFriendly;
    /**
     * 对生人友善
     */
    private int featureStrangerFriendly;
    /**
     * 对动物友善
     */
    private int featureAnimalFriendly;
    /**
     * 运动量
     */
    private int featureSport;
    /**
     * 可训练性
     */
    private int featureTrained;
    /**
     * 口水程度
     */
    private int featureDrool;
    /**
     * 耐寒程度
     */
    private int featureCold;
    /**
     * 耐热程度
     */
    private int featureHot;
    /**
     * 城市适应度
     */
    private int featureCity;

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

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getCfa() {
        return cfa;
    }

    public void setCfa(int cfa) {
        this.cfa = cfa;
    }

    public int getFeatureStick() {
        return featureStick;
    }

    public void setFeatureStick(int featureStick) {
        this.featureStick = featureStick;
    }

    public int getFeatureFeed() {
        return featureFeed;
    }

    public void setFeatureFeed(int featureFeed) {
        this.featureFeed = featureFeed;
    }

    public int getFeatureBark() {
        return featureBark;
    }

    public void setFeatureBark(int featureBark) {
        this.featureBark = featureBark;
    }

    public int getFeatureFallHair() {
        return featureFallHair;
    }

    public void setFeatureFallHair(int featureFallHair) {
        this.featureFallHair = featureFallHair;
    }

    public int getFeatureOdor() {
        return featureOdor;
    }

    public void setFeatureOdor(int featureOdor) {
        this.featureOdor = featureOdor;
    }

    public int getFeatureBeauty() {
        return featureBeauty;
    }

    public void setFeatureBeauty(int featureBeauty) {
        this.featureBeauty = featureBeauty;
    }

    public int getFeatureChildFriendly() {
        return featureChildFriendly;
    }

    public void setFeatureChildFriendly(int featureChildFriendly) {
        this.featureChildFriendly = featureChildFriendly;
    }

    public int getFeatureStrangerFriendly() {
        return featureStrangerFriendly;
    }

    public void setFeatureStrangerFriendly(int featureStrangerFriendly) {
        this.featureStrangerFriendly = featureStrangerFriendly;
    }

    public int getFeatureAnimalFriendly() {
        return featureAnimalFriendly;
    }

    public void setFeatureAnimalFriendly(int featureAnimalFriendly) {
        this.featureAnimalFriendly = featureAnimalFriendly;
    }

    public int getFeatureSport() {
        return featureSport;
    }

    public void setFeatureSport(int featureSport) {
        this.featureSport = featureSport;
    }

    public int getFeatureTrained() {
        return featureTrained;
    }

    public void setFeatureTrained(int featureTrained) {
        this.featureTrained = featureTrained;
    }

    public int getFeatureDrool() {
        return featureDrool;
    }

    public void setFeatureDrool(int featureDrool) {
        this.featureDrool = featureDrool;
    }

    public int getFeatureCold() {
        return featureCold;
    }

    public void setFeatureCold(int featureCold) {
        this.featureCold = featureCold;
    }

    public int getFeatureHot() {
        return featureHot;
    }

    public void setFeatureHot(int featureHot) {
        this.featureHot = featureHot;
    }

    public int getFeatureCity() {
        return featureCity;
    }

    public void setFeatureCity(int featureCity) {
        this.featureCity = featureCity;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
