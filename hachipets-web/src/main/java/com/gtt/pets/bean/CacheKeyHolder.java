package com.gtt.pets.bean;

/**
 * 缓存key定义
 * 
 * @author tiantiangao
 */
public class CacheKeyHolder {

	/**
	 * 全局配置的缓存key
	 */
	public static final String GLOBAL_CONFIG = "oGlobalConfig";
	/**
	 * 宠物电影的缓存key
	 */
	public static final String MOVIE = "oMovie";
	/**
	 * 热门宠物电影的缓存key
	 */
	public static final String MOVIE_HOT_LIST = "oMovieHotList";
	/**
	 * 最新宠物电影的缓存key
	 */
	public static final String MOVIE_NEW_LIST = "oMovieNewList";
	/**
	 * 推荐宠物电影的缓存key
	 */
	public static final String MOVIE_RECOMMEND = "oMovieRecommend";
}
